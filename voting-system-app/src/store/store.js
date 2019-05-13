import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {user: null}

const mutations = {

    LOGIN_SUCCESS(user, response) {
        state.user = user
    }
}

const store = new Vuex.Store({
    state,
    mutations,
})

export default store;