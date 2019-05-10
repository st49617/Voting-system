<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg6 md6 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout align-center justify-start>
                            <v-flex class="headline mb-0 mr-2 text-xs-left">
                                Zastupitelstvo
                            </v-flex>
                            <v-flex lg12 class="text-xs-left">
                                <v-select
                                        v-model="selectedMeeting"
                                        :items="this.meetingsItems"
                                        label="Datum a čas"
                                        item-text="formatedStart"
                                        return-object
                                ></v-select>
                            </v-flex>
                        </v-layout>
                    </v-card-title>
                    <v-card-text>
                        <v-layout column>
                            <v-flex>
                                <v-data-table :items="this.suggestionsList" :headers="headers" hide-actions>
                                    <template v-slot:items="suggestion">
                                        <td @click="goToSuggestion(suggestion.item.id)" class="text-xs-left">
                                            {{ suggestion.item.content }}
                                        </td>
                                        <td @click="goToSuggestion(suggestion.item.id)" class="text-xs-left"
                                            v-html="getSuggestionResultText(suggestion.item)"></td>
                                    </template>
                                </v-data-table>
                            </v-flex>
                        </v-layout>

                    </v-card-text>

                    <v-card-actions class="pa-3">
                        <v-layout justify-space-between>
                            <v-btn @click="backToMenu()" color="primary">Menu</v-btn>
                            <v-btn v-if="this.selectedMeeting !== null" @click="endMeeting()" color="primary">
                                Ukončit zastupitelstvo
                            </v-btn>
                            <v-btn @click="goToSuggestion()" color="primary">Nový návrh</v-btn>
                        </v-layout>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>

    import moment from 'moment';
    import {getAllMeetings, getVotingForSuggestion, addSugesstion} from '../api/Api'

    export default {
        name: 'Meeting',
        props: ['meetingId'],
        data() {
            return {
                meetings: [],
                selectedMeeting: null,
                headers: [
                    {
                        text: 'Návrh',
                        align: 'left',
                        value: 'content'
                    },
                    {
                        text: 'Výsledek',
                        align: 'left',
                        sortable: false,
                        value: 'accepted'
                    },
                ],
            }
        },
        computed: {
            meetingsItems: function () {
                let items = this.meetings;
                return items;
            },
            suggestionsList: function () {
                if (this.selectedMeeting != null && this.selectedMeeting.suggestions.length > 0) {
                    return this.selectedMeeting.suggestions;
                } else {
                    return []
                }
            }
        },
        methods: {
            init: function () {
                getAllMeetings().then(response => {
                    this.meetings = response.data;

                    this.meetings.forEach(meeting => {
                        meeting.formatedStart = this.formatDate(meeting.start);
                    });

                    if (this.meetingId !== undefined) {
                        let meetingId = this.meetingId;
                        this.selectedMeeting = this.meetings.filter(function (meeting) {
                            return (meeting.id === meetingId);
                        })[0];
                    } else {
                        let now = moment();
                        if (this.meetings.length !== 0) {

                            let availableMeetings = this.meetings.filter(meeting => {
                                let meetingStart = moment(meeting.start, moment.HTML5_FMT.DATETIME_LOCAL_MS);
                                return (meetingStart.isAfter(now) || meetingStart.isSame(now, 'day'));
                            });

                            let min = (availableMeetings.length !== 0) ? availableMeetings[0] : this.meetings[0];

                            this.meetings.forEach(meeting => {
                                let meetingStart = moment(meeting.start, moment.HTML5_FMT.DATETIME_LOCAL_MS);
                                if (meetingStart.isBetween(now, min) || meetingStart.isSame(now, 'day')) {
                                    min = meeting;
                                }
                            })
                            this.selectedMeeting = min;
                        }


                    }
                });
            },
            endMeeting: function () {
                let suggestions = this.suggestionsList;
                let selectedMeeting = this.selectedMeeting;
                suggestions.forEach(function (item) {

                    let votings = [];

                    getVotingForSuggestion(item.id).then(response => {
                        votings = response.data;
                        let ano = 0;
                        let ne = 0;
                        let zdrzel = 0;

                        votings.forEach(voting => {
                            if (voting.vote == "ANO") {
                                ano++;
                            }
                            if (voting.vote == "NE") {
                                ne++;
                            }
                            if (voting.vote == "ZDRZEL") {
                                zdrzel++;
                            }
                        });

                        item.accepted = (ano > ne + zdrzel) ? "PRIJATO" : "NEPRIJATO";

                        if (selectedMeeting !== null) {
                            item.meetingId = selectedMeeting.id;
                        }

                        addSugesstion(item);

                    })
                })
            },
            getSuggestionResultText: function (suggestion) {
                if (suggestion.accepted === "NEROZHODNUTO") {
                    return '<span>Hlasování zatím neproběhlo</span>'
                }

                if (suggestion.accepted === "PRIJATO") {
                    return '<span class="primary--text">Přijato</span>'
                }
                return '<span class="error--text">Zamítnuto</span>'
            },
            formatDate: function (date) {
                let momentDate = moment(date, moment.HTML5_FMT.DATETIME_LOCAL_MS);
                return momentDate.format("D.M. YYYY H:mm");

            },
            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            },
            goToSuggestion: function (suggestionId) {
                this.$router.push({
                    name: 'Suggestion',
                    params: {
                        suggestionId: suggestionId,
                    },
                });
            }
        },
        mounted: function () {
            this.init()

        }
    }
</script>