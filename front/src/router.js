import Vue from "vue";
import Router from "vue-router";
import IndexMaterialKit from "./mk/views/Index-material-kit.vue";
import Landing from "./mk/views/Landing.vue";
import Login from "./mk/views/Login.vue";
import Profile from "./mk/views/Profile.vue";
import MainNavbar from "./views/MainNavbar.vue";
import MainFooter from "./mk/layout/MainFooter.vue";
import Footer from "./views/Footer.vue";
import Template from "./views/Template.vue";
import DashboardLayout from './views/perfil/DashboardLayout.vue'

import Dashboard from './views/perfil/DadosPessoais.vue'
import EnderecoEntrega from './views/perfil/EnderecoEntrega.vue'
import EnderecoCobranca from './views/perfil/EnderecoCobranca.vue'
import JavascriptComponents from "./mk/views/components/JavascriptComponentsSection.vue"

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: JavascriptComponents, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
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
        name: 'Dados Pessoais',
        component: Dashboard
      },
      {
        path: 'enderecoEntrega',
        name: 'Endereço Entrega',
        component: EnderecoEntrega
      },{
        path: 'enderecoCobranca',
        name: 'Endereço Cobrança',
        component: EnderecoCobranca
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
