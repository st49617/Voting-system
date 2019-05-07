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
                                                  item-text="start"
                                                  return-object
                                        ></v-select>
                                    </v-flex>
                                    <v-flex class="pt-1">
                                        <v-icon color="primary" @click="goToMeeting()">arrow_forward</v-icon>
                                    </v-flex>
                                </v-layout>

                            </v-flex>
                        </v-layout>
                    </v-card-text>
                    <v-card-actions class="pa-3">
                        <v-layout justify-space-between>
                            <v-btn @click="backToMenu()" color="primary">Zpět</v-btn>
                            <v-btn @click="saveSuggestion()" color="primary">Uložit</v-btn>
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

    import {getAllMeetings, getSuggestion, addSugesstion} from '../api/Api'

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
            }
        },
        computed: {
            meetingsItems: function () {
                let items = this.meetings;
                return items;
            },
        },
        methods: {
            init: function () {
                getAllMeetings().then(response => {
                    this.meetings = response.data;
                });
                if (this.suggestion.id !== undefined) {
                    getSuggestion(this.suggestion.id).then(response => {
                        this.suggestion = response.data;
                    });
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


                console.log(savedSuggestion)
                addSugesstion(savedSuggestion);

                this.$router.push({name: 'Menu'});
            },
            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            },
            goToMeeting: function () {
                if (this.suggestion.meeting !== undefined) {
                    console.log(this.suggestion.meeting);
                    this.$router.push({
                        name: 'Meeting', params: {
                            meetingId: this.suggestion.meeting.id
                        }
                    });
                }
            },
            goToUserStatistic: function () {
                this.$router.push({name: 'UserStatistic'});
            }
        },

        mounted: function () {
            this.init();
        }
    }
</script>