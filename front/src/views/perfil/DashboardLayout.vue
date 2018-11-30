<template>
  <div class="wrapper" :class="{'nav-open': $sidebar.showSidebar}">
    <notifications></notifications>

    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link :to="{name: 'Dados_Pessoais', params: { cliente: this.cliente }}">
        <md-icon>person</md-icon>
        <p>Dados Pessoais</p>
      </sidebar-link>
      <sidebar-link :to="{name: 'Endereço_Entrega', params: { cliente: this.cliente }}">
        <md-icon>location_city</md-icon>
        <p>Endereço de entrega</p>
      </sidebar-link>
      <sidebar-link :to="{name: 'Endereço_Cobrança', params: { cliente: this.cliente }}">
        <md-icon>domain</md-icon>
        <p>Endereço de cobrança</p>
      </sidebar-link>
      <sidebar-link :to="{name: 'Cartao', params: { cliente: this.cliente }}">
        <md-icon>credit_card</md-icon>
        <p>Cartões</p>
      </sidebar-link>
      <sidebar-link to="/perfil/pedidos">
        <md-icon>shopping_basket</md-icon>
        <p>Pedidos</p>
      </sidebar-link>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>
      
      <dashboard-content>

      </dashboard-content>

      <footer v-if="!$route.meta.hideFooter"></footer>
      <!-- <content-footer v-if="!$route.meta.hideFooter"></content-footer> -->
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
import TopNavbar from '@/views/perfil/TopNavbarDashboard.vue'
import ContentFooter from '@/mk_admin/pages/Layout/ContentFooter.vue'
import DashboardContent from '@/mk_admin/pages/Layout/Content.vue'
import MobileMenu from '@/views/MobileMenu.vue'
import MainNavbar from '@/views/MainNavbar.vue'
import Footer from "@/views/Footer.vue";
import { eventBus } from '../../main';
import axios from 'axios';
<<<<<<< HEAD
import swal from 'sweetalert';
=======
>>>>>>> f647c41b7d3b912f5018d4e8d27686af9ec981ed

export default {
  data: () => ({
    cliente : {
      dadosPessoais : {},
      enderecosEntrega : [],
      enderecosCobranca : [],
      cartoes : {}
  }
  }),
  updated(){
    var dadosAtuais = this;
    if(this.$route.params.id != undefined || this.$route.params.nome != undefined){
      this.cliente.dadosPessoais.id = this.$route.params.id;
      this.cliente.dadosPessoais.primeiroNome = this.$route.params.nome;
      axios.post(`http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR`, 
      this.cliente, {
          headers: {
              'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
          }
      }).then(function(e){
        dadosAtuais.cliente.dadosPessoais = e.data.dadosPessoais
        dadosAtuais.cliente.enderecosEntrega = e.data.enderecosEntrega
        dadosAtuais.cliente.enderecosCobranca = e.data.enderecosCobranca
        dadosAtuais.cliente.cartoes = e.data.cartoes
        // dadosAtuais.$router.push({name: "Dados_Pessoais", params: { "cliente": e.data }})
      }).catch(function(e){
          alert(e)
          this.$router.push("/login")
      })
      if(this.$route.params.salvou == true){
<<<<<<< HEAD
        swal({
          title: "Alterado com sucesso",
          icon: "success"
        });
=======
        alert("Alterado com sucesso")
>>>>>>> f647c41b7d3b912f5018d4e8d27686af9ec981ed
      }
      }
    },
  created(){
    var dadosAtuais = this;
    if(this.$route.params.id != undefined || this.$route.params.nome != undefined){
      this.cliente.dadosPessoais.id = this.$route.params.id;
      this.cliente.dadosPessoais.primeiroNome = this.$route.params.nome;
      axios.post(`http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR`, 
      this.cliente, {
          headers: {
              'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
          }
      }).then(function(e){
        dadosAtuais.cliente.dadosPessoais = e.data.dadosPessoais
        dadosAtuais.cliente.enderecosEntrega = e.data.enderecosEntrega
        dadosAtuais.cliente.enderecosCobranca = e.data.enderecosCobranca
        dadosAtuais.cliente.cartoes = e.data.cartoes
        dadosAtuais.$router.push({name: "Dados_Pessoais", params: { "cliente": e.data }})
      }).catch(function(e){
          alert(e)
          this.$router.push("/login")
      })
      
    } 
  },
  methods:{
    catalogoPath(){
      debugger
       this.$router.push({name: "catalogo"})
    }
  },
  components: {
    TopNavbar,
    DashboardContent,
    ContentFooter,
    MobileMenu,
    MainNavbar,
    Footer
  }
}
</script>

<style>
  .router-link-exact-active .md-list-item-content {
    color: white !important;
  }
</style>

