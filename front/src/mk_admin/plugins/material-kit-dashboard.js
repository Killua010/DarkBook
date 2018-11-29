// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import VueRouter from 'vue-router'

// Plugins
import GlobalComponents from './globalComponents'
import GlobalDirectives from './globalDirectives'
import Notifications from '../components/NotificationPlugin'
import '../../assets/scss/material-dashboard.scss'

// MaterialDashboard plugin
import MaterialDashboard from './material-dashboard'

export default {
  install(Vue) {
    Vue.use(VueRouter);
    Vue.use(MaterialDashboard)
    Vue.use(GlobalComponents)
    Vue.use(GlobalDirectives)
    Vue.use(Notifications)
  }
}
