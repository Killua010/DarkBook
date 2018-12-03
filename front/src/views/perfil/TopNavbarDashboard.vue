<template>
  <md-toolbar class="md-dark">
    <div class="md-toolbar-row">
      <div class="md-toolbar-section-end">
        <md-button class="md-just-icon md-simple md-toolbar-toggle" :class="{toggled: $sidebar.showSidebar}" @click="toggleSidebar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <md-list-item @click="catalogoPath()" href="javascript:void(0)">
            <i class="material-icons">book</i> 
                <p>Catálogo</p>
          </md-list-item>

          <md-list-item href="javascript:void(0)">
            <a href="#" class="icon-shop">
              <i class="material-icons">
                shopping_cart
              </i>
            </a>
            
            <div>
              <span slot="badge" class="badge badge-pill badge-warning shop">6</span>
            </div>
          </md-list-item>
          
          <li class="md-list-item">
            <a href="javascript:void(0)" class="md-list-item-router md-list-item-container md-button-clean dropdown">
              <div class="md-list-item-content">
                <drop-down direction="down">
                  <md-button slot="title" class="md-button md-button-link md-white md-simple dropdown-toggle" data-toggle="dropdown">
                    <i class="material-icons">face</i> 
                    <p>Olá {{this.clienteNome}}</p>
                  </md-button>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="javascript:void(0)" @click="sair()" class="dropdown-item">
                        <i class="material-icons">close</i> Sair
                      </a>
                    </li>
                  </ul>
                </drop-down>
              </div>
            </a>
          </li>
        </div>
      </div>
    </div>

  </md-toolbar>
</template>

<script>
import { eventBus } from '@/main';
import swal from 'sweetalert';

export default{
  mounted(){
    let usuario = this.dadosUsuario;
    if(usuario.id == null){
      swal({
          title: "Usuario não logado",
          text: "Entre antes para acessar está pagina",
          icon: "error",
        });
      this.$router.push("/login")
    } else {
      this.id = usuario.id;
      this.clienteNome = usuario.nome;
    }
  },
  computed: {
    dadosUsuario(){
      return this.$store.state.usuario;
    }
  },
  data() {
    return {
      id: '',
      clienteNome: ''
    };
  },
  methods: {
    sair: function(){
      let cliente = {
        id: null,
        nome: ""
      }
      this.$store.commit('ALTERAR_USUARIO', cliente)
      this.$router.push({name: "index"})
    },
    toggleSidebar () {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
    },
    catalogoPath(){
       this.$router.push({name: "catalogo", params: { "id": this.id, "nome": this.clienteNome }})
    }
  }
}
</script>

<style lang="css">
</style>
