<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg6 md6 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout align-center justify-start>
                            <v-flex class="headline mb-0 text-xs-left">
                                <!--<span> -->
                                Zastupitelstvo
                                <!--</span>-->
                                <!--<div class="headline mb-0 text-xs-left">-->
                                <!--<p>-->
                                <!--</p>-->
                                <!--</div>-->
                            </v-flex>
                            <v-flex>
                                <v-select
                                        v-model="selectedMeeting"
                                        :items="this.meetingsItems"
                                        label="Zastupitelstvo"
                                        item-text="start"
                                        return-object
                                ></v-select>
                            </v-flex>
                        </v-layout>
                        <v-layout column>
                            <v-flex>
                                <v-data-table :items="this.suggestionsList" :headers="headers" hide-actions="true">
                                    <template v-slot:items="suggestion">
                                        <td class="text-xs-left">{{ suggestion.item.content }}</td>
                                        <td class="text-xs-left">{{ (suggestion.item.accepted)?"Přijato" :"Zamítnuto"
                                            }}
                                        </td>
                                        <!--<td class="text-xs-right">{{ props.item.calories }}</td>-->
                                        <!--<td class="text-xs-right">{{ props.item.fat }}</td>-->
                                        <!--<td class="text-xs-right">{{ props.item.carbs }}</td>-->
                                        <!--<td class="text-xs-right">{{ props.item.protein }}</td>-->
                                        <!--<td class="text-xs-right">{{ props.item.iron }}</td>-->
                                    </template>
                                </v-data-table>
                            </v-flex>
                        </v-layout>
                    </v-card-title>

                    <v-card-actions>
                        <v-layout justify-space-around>
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
                console.log(this.selectedMeeting);
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
                });
            },
            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            },
            goToSuggestion: function () {
                this.$router.push({name: 'Suggestion'});
            }
        },
        mounted: function () {
            this.init();
        }
    }
</script>