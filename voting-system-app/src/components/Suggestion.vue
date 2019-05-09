<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg6 md6 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout align-center justify-space-between>
                            <v-flex>
                                <h3 class="headline mb-0 text-xs-left">Detail návrhu <span
                                        v-if="this.suggestion.id !== undefined">#{{this.suggestion.id}}</span></h3>
                            </v-flex>
                            <v-flex class="text-xs-right">
                                <v-chip color="primary" class="white--text" v-if="this.suggestion.accepted === true">
                                    Přijato
                                </v-chip>
                                <v-chip color="error" class="white--text" v-if="this.suggestion.accepted === false">
                                    Zamítnuto
                                </v-chip>
                            </v-flex>
                        </v-layout>
                    </v-card-title>
                    <v-card-text>
                        <v-layout column>
                            <v-flex>
                                <v-textarea
                                        name="content"
                                        label="Text návrhu"
                                        value=""
                                        v-model="suggestion.content"
                                        :readonly="readOnly()"
                                ></v-textarea>
                            </v-flex>
                            <v-flex>
                                <v-layout>
                                    <v-flex lg6>
                                        <p class="text-xs-left mb-0">Návrh se bude projednávat na zastupitelstvu</p>
                                    </v-flex>
                                    <v-flex lg6 class="mr-3">
                                        <v-select class="mt-0 pt-0"
                                                  v-model="suggestion.meeting"
                                                  :items="this.meetingsItems"
                                                  menu-props="auto"
                                                  hide-details
                                                  label="Zastupitelstvo"
                                                  single-line
                                                  item-text="formatedStart"
                                                  return-object
                                                  :readonly="readOnly()"
                                        ></v-select>
                                    </v-flex>
                                    <v-flex class="pt-1">
                                        <v-icon color="primary" @click="goToMeeting()">arrow_forward</v-icon>
                                    </v-flex>
                                </v-layout>

                            </v-flex>
                            <v-flex v-if="readOnly()">
                                <v-data-table :items="this.votingsList" :headers="headers" hide-actions>
                                    <template v-slot:items="voting">
                                        <td class="text-xs-left" @click="goToUserStatistic(voting.item.user.id)">
                                            {{voting.item.user.firstName}} {{voting.item.user.lastName}}
                                        </td>
                                        <td class="text-xs-left" @click="goToUserStatistic(voting.item.user.id)">
                                            {{voting.item.vote}}
                                        </td>
                                    </template>
                                </v-data-table>
                            </v-flex>
                        </v-layout>
                    </v-card-text>
                    <v-card-actions class="pa-3">
                        <v-layout justify-space-between>
                            <v-btn @click="backToMenu()" color="primary">Menu</v-btn>
                            <v-btn @click="saveSuggestion()" v-if="!readOnly()" color="primary">Uložit</v-btn>
                            <!--<v-btn @click="goToMeeting()" color="primary">Zastupitelstvo</v-btn>-->
                            <!--<v-btn @click="goToUserStatistic()" color="primary">Statistika zastupitele</v-btn>-->
                        </v-layout>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>

    import moment from 'moment';
    import {getAllMeetings, getSuggestion, addSugesstion, getVotingForSuggestion} from '../api/Api'

    export default {
        name: 'Meeting',
        props: ['suggestionId'],
        data() {
            return {
                meetings: [],
                suggestion: {
                    "id": this.suggestionId,
                    "accepted": null
                },
                votings: [],
                headers: [
                    {
                        text: 'Zastupitel',
                        align: 'left',
                        value: 'content'
                    },
                    {
                        text: 'Hlasování',
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
            votingsList: function () {
                return this.votings;
            }
        },
        methods: {
            init: function () {
                getAllMeetings().then(response => {
                    this.meetings = response.data;

                    this.meetings.forEach(meeting => {
                        meeting.formatedStart = this.formatDate(meeting.start);
                    })

                    if (!this.readOnly()) {

                        let now = moment();

                        this.meetings = this.meetings.filter(meeting => {
                            let meetingStart = moment(meeting.start, moment.HTML5_FMT.DATETIME_LOCAL_MS);
                            return meetingStart.isSameOrAfter(now, 'day');
                        })
                    }


                });
                if (this.suggestion.id !== undefined) {
                    getSuggestion(this.suggestion.id).then(response => {
                        this.suggestion = response.data;
                        this.suggestion.meeting.formatedStart = this.formatDate(this.suggestion.meeting.start);
                    });
                    getVotingForSuggestion(this.suggestion.id).then(response => {
                        this.votings = response.data;
                    })
                }
            },
            saveSuggestion() {

                let savedSuggestion = {};

                if (this.suggestion.id !== undefined) {
                    savedSuggestion.id = this.suggestion.id;
                }

                savedSuggestion.accepted = this.suggestion.accepted;
                savedSuggestion.content = this.suggestion.content;
                savedSuggestion.meetingId = this.suggestion.meeting.id;

                addSugesstion(savedSuggestion);

                this.$router.push({name: 'Menu'});
            },
            readOnly: function () {
                return this.votings.length !== 0;
            },
            formatDate: function (date) {
                let momentDate = moment(date, moment.HTML5_FMT.DATETIME_LOCAL_MS);
                return momentDate.format("D.M. YYYY H:mm");

            },
            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            },
            goToMeeting: function () {
                if (this.suggestion.meeting !== undefined) {
                    this.$router.push({
                        name: 'Meeting', params: {
                            meetingId: this.suggestion.meeting.id
                        }
                    });
                }
            },
            goToUserStatistic: function (userId) {
                this.$router.push({
                    name: 'UserStatistic', params: {
                        userId: userId
                    }
                });
            }
        },

        mounted: function () {
            this.init();
        }
    }
</script>