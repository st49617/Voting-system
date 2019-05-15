<template>
    <div>
        <v-layout justify-space-around fill-height>
            <v-flex lg3 md4 sm12 px-5>
                <v-card>
                    <v-card-title primary-title>
                        <v-layout column v-if="!userIsLogged()">
                            <h3 class="headline mb-0">Přihlášení</h3>
                            <v-text-field
                                    v-model="credentials.email"
                                    :rules="[rules.required]"
                                    type="text"
                                    name="input-email"
                                    label="E-mail"
                                    hint="Zadejte E-mail"
                            ></v-text-field>
                            <v-text-field
                                    v-model="credentials.password"
                                    :rules="[rules.required, rules.min]"
                                    type="password"
                                    name="input-password"
                                    label="Heslo"
                                    hint="Zadejte heslo"
                            ></v-text-field>
                        </v-layout>
                        <v-layout column v-if="userIsLogged()">
                            <h3 class="headline mb-0">Odhlášení</h3>
                        </v-layout>
                    </v-card-title>

                    <v-card-actions>
                        <v-layout justify-space-around>
                            <v-btn @click="backToMenu()" color="primary">Zpět</v-btn>
                            <v-btn v-if="!userIsLogged()" @click="login()" color="primary">Přihlásit</v-btn>
                            <v-btn v-if="userIsLogged()" @click="logout()" color="primary">Odhlásit</v-btn>
                        </v-layout>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>

    import {userLogin, userIsLogged} from '../api/Api'

    export default {
        name: 'Login',
        data() {
            return {
                credentials: {
                    email: '',
                    password: '',
                },
                rules: {
                    show: true,
                    required: value => !!value || 'Povinné pole.',
                    min: v => v.length >= 8 || 'Minimální délka hesla je 8 znaků',
                }
            }
        },
        methods: {

            login: function () {
                userLogin(this.credentials).then(response => {

                    if (response.data.user === null || response.data.token === null) {
                        return;
                    }

                    let user = response.data.user;
                    let token = response.data.jwtToken;
                    this.$store.state.user = user;
//                    this.$store.commit('LOGIN_SUCCESS', user.email)

                    $cookies.set('AUTH_TOKEN', token);
                    this.$router.push({name: 'Menu'});
                }).catch(error => {
                    console.log("Error login")
                    console.log(error)
                })
            },

            logout: function () {
                $cookies.set('AUTH_TOKEN', '');
                this.$router.push({name: 'Menu'});
            },

            userIsLogged: function () {
                return userIsLogged();
            },

            backToMenu: function () {
                this.$router.push({name: 'Menu'});
            }
        }
    }
</script>
