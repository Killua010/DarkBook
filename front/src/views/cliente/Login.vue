<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto">
            <login-card header-color="primary">
              <h4 slot="title" class="card-title">Login</h4>
              <md-button slot="buttons" href="javascript:void(0)" class="md-just-icon md-simple md-white">
                <i class="fab fa-facebook-square"></i>
              </md-button>
              <md-button slot="buttons" href="javascript:void(0)" class="md-just-icon md-simple md-white">
                <i class="fab fa-twitter"></i>
                
              </md-button>
              <md-button slot="buttons" href="javascript:void(0)" class="md-just-icon md-simple md-white">
                <i class="fab fa-google-plus-g"></i>
              </md-button>
              <p slot="description" class="description">Ou tradicionalmente</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label>Email...</label>
                <md-input v-model="cliente.dadosPessoais.email" type="email"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label>Senha...</label>
                <md-input v-model="cliente.dadosPessoais.senha" type="password"></md-input>
              </md-field>
              <md-button slot="footer" class="md-simple md-primary md-lg" @click="login()">
                Entrar
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/mk/components";
import axios from 'axios';
import { eventBus } from '@/main';
import DadosPessoais from '@/model/DadosPessoais.js';
import { blockUI } from "@/assets/block-ui/block-ui";

export default {
  components: {
    LoginCard
  },
  bodyClass: "login-page",
  data: () => ({
      cliente: {
        dadosPessoais: {
          "email" : "",
          "senha" : ""
        }
      }
  }),
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg-dark5.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  },
  methods:{
    login: function(){
      $.blockUI({
          message: '<i class="fa fa-circle-notch fa-spin fa-5x"></i>' ,
    			css: { 
    				border: 'none',
    				backgroundColor: 'transparent',
    				color: '#f6f6f6'
    			}
      });
      var dadosAtuais = this;
        axios.post(`http://localhost:8082/DarkBook/cliente?operacao=CONSULTAR`, 
        this.cliente, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            }
        }).then(function(e){
          $.unblockUI();
          let cliente = {
            id: e.data.dadosPessoais.id,
            nome: e.data.dadosPessoais.nome
          }
          dadosAtuais.$store.commit('ALTERAR_USUARIO', cliente)
          dadosAtuais.$router.push({name: "perfil"})
        }).catch(function(e){
          $.unblockUI();
          try{
              swal({
                  title: "Erro " + e.response.status,
                  text: e.response.data,
                  icon: "error"
              });
          } catch (e) {
              swal({
                  title: e,
                  icon: "error"
              });
          }
        })
      
    }
  }
};
</script>

<style lang="css">
</style>
