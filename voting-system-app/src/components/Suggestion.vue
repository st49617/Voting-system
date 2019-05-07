<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg6 md6 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout align-center justify-space-between>
                            <v-flex>
                                <h3 class="headline mb-0 text-xs-left">Detail návrhu</h3>
                            </v-flex>
                            <v-flex class="text-xs-right">
                                <span class="text--primary">Přijato</span>
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
                                        <p class="text-xs-left">Návrh se bude projednávat na zastupitelstvu</p>
                                    </v-flex>
                                    <v-flex lg3>
                                        <v-select class="mt-0"
                                                items="a"
                                                menu-props="auto"
                                                hide-details
                                                label="Select"
                                                single-line
                                        ></v-select>
                                    </v-flex>
                                    <v-flex lg3>
                                            <v-icon color="primary" @click="goToMeeting()">arrow_forward</v-icon>
                                    </v-flex>
                                </v-layout>

                            </v-flex>
                        </v-layout>
                    </v-card-text>
                    <v-card-actions>
                        <v-layout justify-space-around>
                            <v-btn @click="backToMenu()" color="primary">Zpět</v-btn>
                            <v-btn @click="goToMeeting()" color="primary">Zastupitelstvo</v-btn>
                            <v-btn @click="goToUserStatistic()" color="primary">Statistika zastupitele</v-btn>
                        </v-layout>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
    export default {
        name: 'Meeting',
        data() {
            return {
                suggestion: {},
            }
        },
        methods: {
            init: function () {
            },
            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            },
            goToMeeting: function () {
                this.$router.push({name: 'Meeting'});
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