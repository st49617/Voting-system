// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'

import 'vuetify/dist/vuetify.min.css'
import store from './store/store'
import VueCookies from 'vue-cookies';

Vue.use(Vuetify, {
    theme: {
        primary: '#8bc34a',
        secondary: '#2196f3',
        accent: '#ffc107',
        error: '#f44336'
    }
})

Vue.config.productionTip = false


/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    components: {App},
    template: '<App/>',
    store,
})
