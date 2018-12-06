import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import MaterialKit from "./mk/plugins/material-kit";
import MaterialKitDashboard from "./mk_admin/plugins/material-kit-dashboard";
import JQuery from "jquery";
import TheMask from 'vue-the-mask';
import store from "./store/store";

global.JQuery = JQuery;
global.$ = JQuery;
global.Mask = TheMask;
Vue.config.productionTip = false;

export var eventBus = new Vue();
Vue.use(MaterialKitDashboard);
Vue.use(MaterialKit);
Vue.use(TheMask);

const NavbarStore = {
  showNavbar: false
};

Vue.mixin({
  data() {
    return {
      NavbarStore
    };
  }
});

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount("#app");
