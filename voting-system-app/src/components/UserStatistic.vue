<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg12 md12 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout column>
                            <h3 class="headline text-xs-left">Statistika zastupitele <b>{{this.fullName}}</b></h3>
                            <p class="text-xs-left"><b>Email: </b>{{this.userMail}}</p>
                        </v-layout>
                    </v-card-title>
                    <v-card-text>
                        <v-flex>
                            <v-data-table :items="this.votingsList" :headers="headers" hide-actions>
                                <template v-slot:items="voting">
                                    <td class="text-xs-left" @click="goToSuggestion(voting.item.suggestion.id)">
                                        {{voting.item.suggestion.content}}
                                    </td>
                                    <td class="text-xs-left" @click="goToSuggestion(voting.item.suggestion.id)">
                                        {{formatDate(voting.item.suggestion.meeting.start)}}
                                    </td>
                                    <td class="text-xs-left" @click="goToSuggestion(voting.item.suggestion.id)">
                                        {{voting.item.vote}}
                                    </td>
                                    <td class="text-xs-left" @click="goToSuggestion(voting.item.suggestion.id)"
                                        v-html="getSuggestionResultText(voting.item.suggestion)">
                                    </td>
                                </template>
                            </v-data-table>
                        </v-flex>
                    </v-card-text>

                    <v-card-actions>
                        <v-layout justify-space-around>
                            <v-btn @click="backToMenu()" color="primary">Menu</v-btn>
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
    import {getUser, getVotingForUser} from '../api/Api'

    export default {
        name: 'Meeting',
        props: ['userId'],
        data() {
            return {
                user: null,
                votings: [],
                headers: [
                    {
                        text: 'Návrh',
                        align: 'left',
                        value: 'suggestion',
                        sortable: false,
                    },
                    {
                        text: 'Zastupitelstvo',
                        align: 'left',
                        sortable: false,
                        value: 'meeting'
                    },
                    {
                        text: 'Hlasování',
                        align: 'left',
                        sortable: false,
                        value: 'vote'
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
            fullName: function () {
                if (this.user !== null) {
                    return this.user.firstName + " " + this.user.lastName;
                }
                return "";
            },
            userMail: function () {
                if (this.user !== null) {
                    return this.user.email;
                }
                return "";
            },
            votingsList: function () {
                return this.votings;
            }
        },
        methods: {
            init: function () {
                if (this.userId !== undefined) {
                    getUser(this.userId).then(response => {
                        this.user = response.data;
                    });
                    getVotingForUser(this.userId).then(response => {
                        this.votings = response.data;
                    })
                } else {
                    if (this.$store.state.user !== undefined) {
                        this.user = this.$store.state.user;
                        getVotingForUser(this.user.id).then(response => {
                            this.votings = response.data;
                        })
                    } else {
                        this.backToMenu();
                    }
                }
            },
            getSuggestionResultText: function (suggestion) {
                if (suggestion.accepted === 'NEROZHODNUTO') {
                    return '<span>Hlasování zatím neproběhlo</span>'
                }

                if (suggestion.accepted === 'PRIJATO') {
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
            },
        },
        mounted: function () {
            this.init();
        }
    }
</script>