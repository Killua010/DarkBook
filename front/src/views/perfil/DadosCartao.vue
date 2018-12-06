<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Cartões</span>
      </md-toolbar>
    </div>
    <div class="md-layout">

      <div v-for="(cartao, index) in cliente.cartoes" class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
            <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon v-if="cartao.preferencial == true">star_border</md-icon><md-icon v-else>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              {{ cartao.bandeira }}<br>
              {{ cartao.numero }}<br>
              {{ cartao.nomeImpresso }}<br>
              Cod segurança: {{ cartao.codSeguranca }}
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger" @click="deletarCartao(index)">Deletar</md-button>
              <md-button class="md-primary" @click="modalCartao = true; indexCartao = index">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-toolbar class="md-white my-30">
          <md-button class="md-white">
            <md-icon>add_circle</md-icon>
            <span class="md-title md-toolbar-section-start" @click="modalCartao = true; indexCartao = null">Novo Cartão</span>
          </md-button>
        </md-toolbar> 
      </div>
    </div>
    <modal v-if="modalCartao" @close="fecharModalCartao">
      <template slot="body">
        <cad-dados-cartao @dados-valido-cliente="valido" v-if="indexCartao != null" v-bind:dados="{cartoes: this.cliente.cartoes[indexCartao]}"></cad-dados-cartao>
        <cad-dados-cartao @dados-valido-cliente="validoNovo" v-else v-bind:dados="{cartoes: this.cartao}"></cad-dados-cartao>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm" @click="fecharModalCartao">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="ValidarCartao">Salvar</md-button>
      </template>
  </modal>
  </div>
</template>

<style>
.my-30{
  margin: 30px 0;
}

.md-menu-content.md-select-menu {
    z-index: 9998 !important;
}
</style>


<script>
import { Modal } from "@/mk/components";
import CadDadosCartao from '../cliente/CadDadosCartao'
import { eventBus } from '../../main';
import axios from 'axios';
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  mounted(){
    this.novoCartao();
    var dadosAtuais = this; 
    this.dadosCliente;
  },
  computed: {
    dadosCliente(){
      this.cliente = this.$store.state.cliente.dados;
    }
  },
  data: () => ({
    cliente:{},
    modalCartao : false,
    indexCartao: null,
    cartao : {}
  }),
  components: {
    EditProfileForm,
    UserCard,
    Modal,
    CadDadosCartao
  },
  methods: {
    fecharModalCartao() {
      this.modalCartao = false;
    },
    deletarCartao(indice){
      this.cliente.cartoes[indice].deletar = false;
      this.AtualizarCliente();
    },
    valido(){
      this.AtualizarCliente();
    },
    validoNovo(){
      this.cliente.cartoes.push(this.cartao);
      this.AtualizarCliente();
    },
    novoCartao(){
      this.cartao = {
        bandeira : "",
        numero : "",
        nomeImpresso : "",
        codSeguranca : "",
        preferencial : false
      }
    },
    AtualizarCliente(){
      var dadosAtuais = this;
        axios.post(`http://localhost:8082/DarkBook/cliente?operacao=ALTERAR`, 
        this.cliente, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            }
        }).then(function(e){
              swal({
                title: "Alterado com sucesso",
                icon: "success"
              });
              dadosAtuais.novoCartao();
              dadosAtuais.$router.push({name: "perfil"})
              dadosAtuais.$router.push({name: "Cartao"})
        }).catch(function(e){
            console.log(e)
            try{
              swal({
                  title: e.response.data,
                  icon: "error"
              });
            } catch (e) {
              swal({
                  title: e,
                  icon: "error"
              });
            }
        })
      
    },
    ValidarCartao(){
      eventBus.$emit('validarDadosCartao', true);
    }
  }
}
</script>
