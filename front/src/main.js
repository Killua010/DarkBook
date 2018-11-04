import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import MaterialKit from "./plugins/material-kit";
import JQuery from "jquery";
import TheMask from 'vue-the-mask';

global.JQuery = JQuery;
global.$ = JQuery;
global.Mask = TheMask;
Vue.config.productionTip = false;

export var eventBus = new Vue();

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
  router,
  render: h => h(App)
}).$mount("#app");
