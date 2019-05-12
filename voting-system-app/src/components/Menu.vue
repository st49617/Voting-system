<template>
    <div>
        <v-layout justify-center>
            <v-flex lg3 md4 sm12 px-5>
                <v-layout align-space-around justify-center column fill-height px-10>
                    <v-btn @click="goToLogin()" large color="primary">Login</v-btn>
                    <v-btn v-if="userIsLogged()" @click="goToVoting()" large color="primary">Hlasování</v-btn>
                    <v-btn v-if="userIsLogged()" @click="goToUserStatistic()" large color="primary">Statistiky</v-btn>
                    <v-btn @click="goToMeeting()" large color="primary">Předchozí zastupitelstva</v-btn>
                    <v-btn @click="goToSuggestion()" large color="primary">Nový návrh</v-btn>
                    <!--<v-btn @click="pepa()" large color="primary">Správa návrhů</v-btn>-->
                </v-layout>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>

    import {createNewMeeting, getAllMeetings, userIsLogged} from '../api/Api'

    export default {
        name: 'Menu',
        data() {
            return {}
        },
        methods: {
            pepa: function () {
//                let token = this.$store.state.token;
                let meeting = {start: "2019-10-10T14:08:56.235-0700"}
                createNewMeeting(meeting);

                getAllMeetings().then(response => {
                    console.log(response.data);
                });
            },

            userIsLogged: function () {
                return userIsLogged();
            },

            goToLogin: function () {
                this.$router.push({name: 'Login'});
            },

            goToVoting: function () {
                this.$router.push({name: 'Voting'});
            },
            goToSuggestion: function () {
                this.$router.push({name: 'Suggestion'});
            },
            goToMeeting: function () {
                this.$router.push({name: 'Meeting'});
            },
            goToUserStatistic: function () {
                this.$router.push({name: 'UserStatistic'});
            },
        }
    }
</script>