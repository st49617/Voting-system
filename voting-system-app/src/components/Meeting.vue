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
                                        item-text="start"
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
                            <v-btn @click="backToMenu()" color="primary">Zpět</v-btn>
                            <v-btn @click="goToSuggestion()" color="primary">Bod programu</v-btn>
                        </v-layout>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>

    import {createNewMeeting, getAllMeetings} from '../api/Api'

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
//                    if (this.meetingId !== undefined) {
                    let meetingId = this.meetingId;
                    this.selectedMeeting = this.meetings.filter(function (meeting) {
                        return (meeting.id === meetingId);
                    })[0];
//                    }
                });
            },
            getSuggestionResultText: function (suggestion) {
                if (suggestion.accepted === null) {
                    return '<span>Hlasování zatím neproběhlo</span>'
                }

                if (suggestion.accepted === true) {
                    return '<span class="primary--text">Přijato</span>'
                }
                return '<span class="error--text">Zamítnuto</span>'
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