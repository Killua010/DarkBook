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
<<<<<<< HEAD
import swal from 'sweetalert';

=======
>>>>>>> f647c41b7d3b912f5018d4e8d27686af9ec981ed
export default{
  // created() {
  //   if(this.$route.params.id == '' || this.$route.params.nome == ''){
  //     alert("Usuario não logado");
  //     this.$router.push("/login")
  //   } else {
  //     this.id = this.$route.params.id;
  //     this.clienteNome = this.$route.params.nome;
  //   }
   
  // },
  mounted(){
  console.log(this.$route.params)
  if(this.$route.params.id == '' || this.$route.params.nome == undefined
  || this.$route.params.nome == '' || this.$route.params.nome == undefined){
<<<<<<< HEAD
      swal({
          title: "Usuario não logado",
          text: "Entre antes para acessar está pagina",
          icon: "error",
        });
=======
      alert("Usuario não logado");
>>>>>>> f647c41b7d3b912f5018d4e8d27686af9ec981ed
      this.$router.push("/login")
    } else {
      this.id = this.$route.params.id;
      this.clienteNome = this.$route.params.nome;
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
      this.id = undefined;
      this.clienteNome = undefined;
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
