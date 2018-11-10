import Vue from "vue";
import Router from "vue-router";
import IndexMaterialKit from "./mk/views/Index-material-kit.vue";
import Landing from "./mk/views/Landing.vue";
import Login from "./mk/views/Login.vue";
import Profile from "./mk/views/Profile.vue";
import MainNavbar from "./mk/layout/MainNavbar.vue";
import MainFooter from "./mk/layout/MainFooter.vue";
import Footer from "./views/Footer.vue";
import Template from "./views/Template.vue";
import DashboardLayout from './mk_admin/pages/Layout/DashboardLayout.vue'

import Dashboard from './mk_admin/pages/Dashboard.vue'
import UserProfile from './mk_admin/pages/UserProfile.vue'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/cadastroCliente",
      name: "index",
      components: { default: Template, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
    path: '/perfil',
    component: DashboardLayout,
    children: [
      {
        path: 'dadosPessoais',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: 'enderecoCobranca',
        name: 'User Profile',
        component: UserProfile
      }
    ]
    },{
      path: "/index2",
      name: "index2",
      components: { default: IndexMaterialKit, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/landing",
      name: "landing",
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    }
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
