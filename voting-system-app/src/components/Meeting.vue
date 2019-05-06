<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg3 md4 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout column>
                            <h3 class="headline mb-0">Zastupitelstvo</h3>
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
    export default {
        name: 'Meeting',
        data() {
            return {}
        },
        methods: {

            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            },
            goToSuggestion: function () {
                this.$router.push({name: 'Suggestion'});
            }
        }
    }
</script>