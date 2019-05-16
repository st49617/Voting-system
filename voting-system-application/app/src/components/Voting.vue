<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg6 md6 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout align-center justify-space-between>
                            <h3 class="headline mb-0">Hlasování</h3>
                        </v-layout>
                    </v-card-title>
                    <v-card-text>
                        <v-layout column>
                            <v-flex>
                                <p class="text-xs-left" v-if="!odhlasovano()"
                                   v-text="this.nextSuggestion.content"></p>
                                <v-alert color="primary" v-if="odhlasovano()" :value="true" type="success">
                                    Všechny návrhy byly odhlasovány!
                                </v-alert>
                            </v-flex>
                            <!--<v-flex>-->
                            <!--<v-layout align-start justify-space-around row fill-height>-->
                            <!--</v-layout>-->
                            <!--</v-flex>-->
                        </v-layout>

                    </v-card-text>
                    <v-card-actions>
                        <v-layout justify-center>
                            <v-flex lg4>

                                <v-layout column px-10 v-if="!odhlasovano()">
                                    <v-btn @click="addVote('ANO')" large color="primary" class=" ma-2">Ano</v-btn>
                                    <v-btn @click="addVote('NE')" large color="primary" class=" ma-2">Ne</v-btn>
                                    <v-btn @click="addVote('ZDRZEL')" large color="primary" class=" ma-2">Zdržet se
                                    </v-btn>
                                </v-layout>
                                <v-layout v-if="odhlasovano()">
                                    <v-btn @click="backToMenu()" color="primary">Menu</v-btn>
                                    <v-btn @click="goToMeeting()" color="primary">Zastupitelstvo</v-btn>

                                </v-layout>
                            </v-flex>
                        </v-layout>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>

    import moment from 'moment';
    import {getAllMeetings, addVoting, getVotingForSuggestion, getVotingForUser} from '../api/Api'

    export default {
        name: 'Voting',
        data() {
            return {
                meeting: null,
                votes: [],
                userVotings: []
            }
        },
        computed: {
            nextSuggestion: function () {

                if (this.meeting === null) {
                    return undefined;
                }

                let notVotingSuggestion = this.meeting.suggestions;

                this.userVotings.forEach(function (userVoting) {
                    let suggestionId = userVoting.suggestion.id;
                    notVotingSuggestion = notVotingSuggestion.filter(function (sugestion) {
                        return sugestion.id != suggestionId;
                    });
                    if (notVotingSuggestion.length === 0) {
                        return undefined;
                    }
                });


                this.meeting.suggestions = notVotingSuggestion;
                return this.meeting.suggestions[0];
            },
            loadedUser() {
                let user = this.$store.state.user;
                return user;
            }
        },

        methods: {

            init: function () {
                getAllMeetings().then(response => {
                    let now = moment();
                    let meetingsForThisDay = response.data.filter(function (meeting) {
                        let meetingDate = moment(meeting.start, moment.HTML5_FMT.DATETIME_LOCAL_MS)
                        return meetingDate.isSame(now, 'day');
                    });
                    if (meetingsForThisDay.length !== 0) {
                        this.meeting = meetingsForThisDay[0];
                    }
                });
                getVotingForUser(this.loadedUser.id).then(response => {
                    this.userVotings = response.data;
                })

            },
            addVote: function (vote) {
                let voting = {
                    userId: this.loadedUser.id,
                    suggestionId: this.nextSuggestion.id,
                    vote: vote
                }

                let notVotingSuggestion = this.meeting.suggestions;
                let suggestionId = this.nextSuggestion.id;
                notVotingSuggestion = notVotingSuggestion.filter(function (sugestion) {
                    return sugestion.id != suggestionId;
                });
                this.meeting.suggestions = notVotingSuggestion;


                addVoting(voting)
            },

            odhlasovano: function () {
                return this.nextSuggestion === undefined;
            },

            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            }
            ,
            goToMeeting: function () {


                if (this.meeting !== null) {
                    this.$router.push({
                        name: 'Meeting', params: {
                            meetingId: this.meeting.id
                        }
                    });
                }

//
//                this.$router.push({name: 'Meeting'});
            }
        },
        mounted: function () {
            this.init();
        }
    }
</script>
