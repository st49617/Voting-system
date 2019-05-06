import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {token: null}

const mutations = {

    LOGIN_SUCCESS(state, response) {
        state.token = response
    }
}

const store = new Vuex.Store({
    state,
    mutations,
})

export default store;