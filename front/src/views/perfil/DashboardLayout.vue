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
      
      <dashboard-content v-on:atualizarPagina="validarUsuario">

      </dashboard-content>

      <footer v-if="!$route.meta.hideFooter"></footer>
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
import swal from 'sweetalert';
import { blockUI } from "@/assets/block-ui/block-ui";

export default {
  data: () => ({
    cliente : {
      dadosPessoais : {},
      enderecosEntrega : [],
      enderecosCobranca : [],
      cartoes : {}
    }
  }),
  computed: {
    dadosUsuario(){
      return this.$store.state.usuario;
    }
  },
  created(){
    var dadosAtuais = this;
    let usuario = this.dadosUsuario;
    
    if(usuario.id != null){
      this.cliente.dadosPessoais.id = usuario.id;
      this.cliente.dadosPessoais.primeiroNome = usuario.nome;
      $.ajax({
            type: "POST",
            url: "http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR",
            data: this.cliente,
            async: false
        }).done(function(e){
           dadosAtuais.cliente.dadosPessoais = e.dadosPessoais
          dadosAtuais.cliente.enderecosEntrega = e.enderecosEntrega
          dadosAtuais.cliente.enderecosCobranca = e.enderecosCobranca
          dadosAtuais.cliente.cartoes = e.cartoes
          dadosAtuais.$store.commit('ALTERAR_CLIENTE', dadosAtuais.cliente)
          dadosAtuais.$router.push({name: "Dados_Pessoais"})
        }).fail(function(jqXHR, textStatus, msg){
          alert(msg)
          dadosAtuais.$router.push("/login")
        });
    }



    // if(usuario.id != null){
    //   this.cliente.dadosPessoais.id = usuario.id;
    //   this.cliente.dadosPessoais.primeiroNome = usuario.nome;
    //   axios.post(`http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR`, 
    //   this.cliente, {
    //       headers: {
    //           'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    //       }
    //   }).then(function(e){
    //     dadosAtuais.cliente.dadosPessoais = e.data.dadosPessoais
    //     dadosAtuais.cliente.enderecosEntrega = e.data.enderecosEntrega
    //     dadosAtuais.cliente.enderecosCobranca = e.data.enderecosCobranca
    //     dadosAtuais.cliente.cartoes = e.data.cartoes
    //     dadosAtuais.$store.commit('ALTERAR_CLIENTE', dadosAtuais.cliente)
    //     dadosAtuais.$router.push({name: "Dados_Pessoais"})
    //   }).catch(function(e){
    //       alert(e)
    //       dadosAtuais.$router.push("/login")
    //   })
    // }
    
  },
  updated(){
    // this.validarUsuario();
  },
  mounted(){
      var dadosAtuais = this;
      eventBus.$on('atualizarPagina', function(e){
          if(e === true){
              dadosAtuais.validarUsuario()
          }
      })
  },
  methods:{
    catalogoPath(){
      debugger
       this.$router.push({name: "catalogo"})
    },
    validarUsuario(){
      var dadosAtuais = this;
      let usuario = this.dadosUsuario;
      if(usuario.id != null){
         this.cliente.dadosPessoais.id = usuario.id;
          this.cliente.dadosPessoais.primeiroNome = usuario.nome;
          $.ajax({
                type: "POST",
                url: "http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR",
                data: this.cliente,
                async: false
            }).done(function(e){
              dadosAtuais.cliente.dadosPessoais = e.dadosPessoais
              dadosAtuais.cliente.enderecosEntrega = e.enderecosEntrega
              dadosAtuais.cliente.enderecosCobranca = e.enderecosCobranca
              dadosAtuais.cliente.cartoes = e.cartoes
              dadosAtuais.$store.commit('ALTERAR_CLIENTE', dadosAtuais.cliente)
              dadosAtuais.$router.push({name: "Dados_Pessoais"})
            }).fail(function(jqXHR, textStatus, msg){
              alert(msg)
              this.$router.push("/login")
            });




        // this.cliente.dadosPessoais.id = usuario.id;
        // this.cliente.dadosPessoais.primeiroNome = usuario.nome;
        // axios.post(`http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR`, 
        // this.cliente, {
        //     headers: {
        //         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        //     }
        // }).then(function(e){
        //   dadosAtuais.cliente.dadosPessoais = e.data.dadosPessoais
        //   dadosAtuais.cliente.enderecosEntrega = e.data.enderecosEntrega
        //   dadosAtuais.cliente.enderecosCobranca = e.data.enderecosCobranca
        //   dadosAtuais.cliente.cartoes = e.data.cartoes
        //   dadosAtuais.$store.commit('ALTERAR_CLIENTE', dadosAtuais.cliente)
        // }).catch(function(e){
        //     alert(e)
        //     this.$router.push("/login")
        // })
      }
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

