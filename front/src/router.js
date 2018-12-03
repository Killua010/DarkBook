import Vue from "vue";
import Router from "vue-router";
import IndexMaterialKit from "./mk/views/Index-material-kit.vue";
import Landing from "./mk/views/Landing.vue";
import Login from "./views/cliente/Login.vue";
import Profile from "./views/TeamDeveloper/Profile.vue";
import MainNavbar from "./views/MainNavbar.vue";
import MainFooter from "./mk/layout/MainFooter.vue";
import Footer from "./views/Footer.vue";
import Template from "./views/Template.vue";
import DashboardLayout from './views/perfil/DashboardLayout.vue' 
import Catalogo from "./views/livro/Catalogo.vue";
import DescricaoLivro from "./views/livro/DescricaoLivro.vue";

import DadosPessoais from './views/perfil/DadosPessoais.vue'
import DadosCartao from './views/perfil/DadosCartao.vue'
import Pedidos from './views/perfil/Pedidos.vue'
import EnderecoEntrega from './views/perfil/EnderecoEntrega.vue'
import EnderecoCobranca from './views/perfil/EnderecoCobranca.vue'
import Index from "./views/Index.vue"

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },{
      path: "/descricaoLivro",
      name: "Descrição Livro",
      components: { default: DescricaoLivro, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },{
      path: "/catalogo",
      name: "catalogo",
      components: { default: Catalogo, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/cadastroCliente",
      name: "cadastro_cliente",
      components: { default: Template, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
    path: '/perfil',
    name: 'perfil',
    component: DashboardLayout,
    children: [
      {
        path: 'dadosPessoais',
        name: 'Dados_Pessoais',
        component: DadosPessoais
      },
      {
        path: 'enderecoEntrega',
        name: 'Endereço_Entrega',
        component: EnderecoEntrega
      },{
        path: 'enderecoCobranca',
        name: 'Endereço_Cobrança',
        component: EnderecoCobranca
      },{
        path: 'cartao',
        name: 'Cartao',
        component: DadosCartao
      },{
        path: 'pedidos',
        name: 'Pedidos',
        component: Pedidos
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
      components: { default: Login, header: MainNavbar, footer: Footer },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/teamDeveloper",
      name: "Team Developer",
      components: { default: Profile, header: MainNavbar, footer: Footer },
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
