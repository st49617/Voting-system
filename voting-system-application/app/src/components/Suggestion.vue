<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg6 md6 sm12 px-5>
                <v-card>
                    <v-card-title primary-title class="pb-0">
                        <v-layout column>
                            <v-flex>
                                <v-layout align-center justify-space-between>
                                    <v-flex>
                                        <h3 class="headline mb-0 text-xs-left">Detail návrhu <span
                                                v-if="this.suggestion.id !== undefined">#{{this.suggestion.id}}</span>
                                        </h3>
                                    </v-flex>
                                    <v-flex class="text-xs-right">
                                        <v-chip color="primary" class="white--text"
                                                v-if="this.suggestion.accepted === 'PRIJATO'">
                                            Přijato
                                        </v-chip>
                                        <v-chip color="error" class="white--text"
                                                v-if="this.suggestion.accepted === 'NEPRIJATO'">
                                            Zamítnuto
                                        </v-chip>
                                    </v-flex>
                                </v-layout>
                            </v-flex>
                            <v-flex>
                                <v-layout justify-start row>
                                    <v-chip close v-model="tagsToRemove[index]"
                                            v-for="(tag, index) in this.tags"
                                            :color="tag.color" :key="tag.id">{{tag.name}}
                                    </v-chip>
                                    <v-dialog v-model="tagChoserDialog" width="500">
                                        <template v-slot:activator="{ on }">
                                            <v-chip v-if="!readOnly()" v-on="on">
                                                <v-icon>add</v-icon>
                                            </v-chip>
                                        </template>

                                        <v-card>
                                            <v-card-title class="headline pa-2" primary-title>
                                                Výběr štítku
                                            </v-card-title>

                                            <v-card-text>
                                                <v-chip v-for="tag in this.availableTags"
                                                        @click="addTagToThisSuggestion(tag)" :color="tag.color"
                                                        :key="tag.id">{{tag.name}}
                                                </v-chip>
                                                <v-divider class="ma-2 mb-4"></v-divider>
                                                <h4 class="headline text-xs-left">Nový štítek</h4>
                                                <v-layout>
                                                    <v-flex>
                                                        <v-text-field
                                                                v-model="newTag.name"
                                                                label="Název"
                                                                required
                                                        ></v-text-field>
                                                    </v-flex>
                                                    <v-flex>
                                                        <v-text-field
                                                                v-model="newTag.color"
                                                                label="Barva"
                                                                required
                                                        ></v-text-field>
                                                    </v-flex>
                                                    <v-flex>
                                                        <v-chip @click="saveNewTag()" :color="this.newTag.color">
                                                            {{this.newTag.name}}
                                                        </v-chip>
                                                    </v-flex>
                                                </v-layout>
                                            </v-card-text>
                                            <v-card-actions>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>


                                </v-layout>
                            </v-flex>
                        </v-layout>
                    </v-card-title>
                    <v-card-text class="pt-0">
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
    import {
        getAllMeetings,
        getSuggestion,
        addSugesstion,
        getVotingForSuggestion,
        addTag,
        addTagToSuggestion,
        removeTagToSuggestion,
        getAllTags,
        getAllTagsForSuggestion
    } from '../api/Api'

    export default {
        name: 'Meeting',
        props: ['suggestionId'],
        data() {
            return {
                meetings: [],
                suggestion: {
                    "id": this.suggestionId,
                    "accepted": 'NEROZHODNUTO'
                },
                votings: [],
                tags: [],
                availableTags: [],
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
                tagChoserDialog: false,
                newTag: {
                    name: "",
                    color: "",
                },
                tagsToRemove: []
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
                    if (this.suggestionId === undefined) {

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

                        if (!this.readOnly()) {

                            let now = moment();

                            this.meetings = this.meetings.filter(meeting => {
                                let meetingStart = moment(meeting.start, moment.HTML5_FMT.DATETIME_LOCAL_MS);
                                return meetingStart.isSameOrAfter(now, 'day');
                            })
                        }
                    })
                    getAllTagsForSuggestion(this.suggestion.id).then(response => {
                        this.tags = response.data;
                    })
                }

                getAllTags().then(response => {
                    this.availableTags = response.data;
                })


            },
            saveSuggestion() {

                let savedSuggestion = {};

                if (this.suggestion.id !== undefined) {
                    savedSuggestion.id = this.suggestion.id;
                }


                savedSuggestion.accepted = this.suggestion.accepted;
                savedSuggestion.content = this.suggestion.content;
                savedSuggestion.meetingId = this.suggestion.meeting.id;

                if (this.suggestion.id !== undefined) {
                    addSugesstion(savedSuggestion)
                } else {
                    addSugesstion(savedSuggestion).then(response => {
                        let suggestionId = response.data.id;
                        this.tags.forEach(tag => {
                            addTagToSuggestion(tag.id, suggestionId);
                        })
                    });
                }

                this.$router.push({name: 'Menu'});
            },
            saveNewTag() {
                addTag(this.newTag).then(response => {
                    this.tags.push(response.data);
                    if (this.suggestion.id !== undefined) {
                        addTagToSuggestion(response.data.id, this.suggestion.id);
                    }
                });
                this.tagChoserDialog = false;
            },
            addTagToThisSuggestion(tag) {
                this.tags.push(tag);
                if (this.suggestionId !== undefined) {
                    addTagToSuggestion(tag.id, this.suggestionId);
                }
            },
            removeTagToThisSuggestion(index) {

                let tag = this.tags[index];
                if (tag === undefined) {
                    return;
                }
                if (this.readOnly()) {
                    return;
                }
                this.tags = this.tags.filter(item => {
                    return item.id !== tag.id;
                });
                if (this.suggestionId !== undefined) {
                    removeTagToSuggestion(tag.id, this.suggestionId);
                }
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
        watch: {
            tagsToRemove: function (val) {
                let removeFunction = this.removeTagToThisSuggestion
                val.forEach(function (value, tagIndex) {
                    if (value === false) {
                        delete val[tagIndex];
                        removeFunction(tagIndex);
                    }
                })
            }
        },

        mounted: function () {
            this.init();
        }
    }
</script>