import Vue from 'vue'
import Router from 'vue-router'
import Menu from '@/components/Menu'
import Login from '@/components/Login'
import Voting from '@/components/Voting'
import Meeting from '@/components/Meeting'
import Suggestion from '@/components/Suggestion'
import UserStatistic from '@/components/UserStatistic'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Menu',
            component: Menu
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/voting',
            name: 'Voting',
            component: Voting
        },
        {
            path: '/meeting',
            name: 'Meeting',
            component: Meeting,
            props: true
        },
        {
            path: '/suggestion',
            name: 'Suggestion',
            component: Suggestion,
            props: true
        },
        {
            path: '/user-statistic',
            name: 'UserStatistic',
            component: UserStatistic,
            props: true
        }
    ]
})
