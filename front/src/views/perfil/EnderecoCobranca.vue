<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Endereço de Cobrança</span>
      </md-toolbar>
    </div>
    <div class="md-layout">
      <div v-for="(endereco, index) in cliente.enderecosCobranca" class="md-layout-item md-medium-size-100 md-size-33">
          <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon v-if="endereco.favorito == true">star_border</md-icon><md-icon v-else>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              {{ endereco.tipoLogradouro }} {{ endereco.logradouro }}, {{ endereco.numero }}, {{ endereco.bairro }}, {{ endereco.tipoResidencia }}<br>
              {{ endereco.cep }}<br>
              {{ endereco.cidade }}, {{ endereco.estado }}, {{ endereco.pais }}<br>
              {{ endereco.complemento }}
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger" @click="deletarEndereco(index)">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoCobranca = true; indexEndereco = index">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div> 

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-toolbar class="md-white my-30">
          <md-button class="md-white">
            <md-icon>add_circle</md-icon>
            <span class="md-title md-toolbar-section-start" @click="modalEnderecoCobranca = true; indexEndereco = null">Novo Endereço</span>
          </md-button>
        </md-toolbar> 
      </div>
    </div>
    <modal v-if="modalEnderecoCobranca" @close="fecharModalEnderecoCobranca">
      <template slot="body">
        <cad-dados-endereco @dados-valido-cliente="valido" v-if="indexEndereco != null" v-bind:dados="{enderecosCobranca: this.cliente.enderecosCobranca[indexEndereco]}"></cad-dados-endereco>
        <cad-dados-endereco @dados-valido-cliente="validoNovo" v-else v-bind:dados="{enderecosCobranca: this.enderecosCobranca}"></cad-dados-endereco>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm" @click="fecharModalEnderecoCobranca">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="ValidarEnderecoCobranca">Salvar</md-button>
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
import CadDadosEndereco from '../cliente/CadDadosEndereco'
import { eventBus } from '../../main';
import axios from 'axios';
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  mounted(){
    this.novoEndereco();
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
    modalEnderecoCobranca : false,
    indexEndereco : null,
    enderecosCobranca : {}
}),
  components: {
    EditProfileForm,
    UserCard,
    Modal,
    CadDadosEndereco
  },
  methods: {
    deletarEndereco(indice){
      this.cliente.enderecosCobranca[indice].deletar = false;
      this.AtualizarCliente();
    },
    valido(){
      this.AtualizarCliente();
    },
    validoNovo(){
      this.cliente.enderecosCobranca.push(this.enderecosCobranca);
      this.AtualizarCliente();
    },
    fecharModalEnderecoCobranca() {
      this.modalEnderecoCobranca = false;
    },
    ValidarEnderecoCobranca(){
      eventBus.$emit('validarDadosEnderecoCobranca', true);
    },
    novoEndereco(){
      this.enderecosCobranca = {
        tipoResidencia : "",
        tipoLogradouro : "",
        pais : "",
        estado : "",
        cidade : "",
        logradouro : "",
        numero : "",
        bairro : "",
        cep : "",
        observacao : "",
        favorito : false
      }
    },
    resetarEnderecos(){
      for(let e of this.cliente.enderecosCobranca){
        e.deletar = true;
      }
    },
    AtualizarCliente(){
      this.modalCliente = false;
      var dadosAtuais = this;
      if(undefined != this.cliente && null != this.cliente){
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
              dadosAtuais.novoEndereco();
              dadosAtuais.$router.push({name: "perfil"})
              dadosAtuais.$router.push({name: "Endereço_Cobrança"})
              
          }).catch(function(e){
              dadosAtuais.resetarEnderecos();
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
      }
    },
  }
}
</script>
