<template>
  <div class="wrapper" :class="{'nav-open': $sidebar.showSidebar}">
    <notifications></notifications>

    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link class="link-ativo" to="/perfil/dadosPessoais">
        <md-icon>person</md-icon>
        <p>Dados Pessoais</p>
      </sidebar-link>
      <sidebar-link to="/perfil/enderecoEntrega">
        <md-icon>location_city</md-icon>
        <p>Endereço de entrega</p>
      </sidebar-link>
      <sidebar-link to="/perfil/enderecoCobranca">
        <md-icon>domain</md-icon>
        <p>Endereço de cobrança</p>
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
import MobileMenu from '@/mk_admin/pages/Layout/MobileMenu.vue'
import MainNavbar from '@/views/MainNavbar.vue'
import Footer from "@/views/Footer.vue";

export default {
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

