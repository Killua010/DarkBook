<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title">Dados Pessoais</span>
        <div class="md-toolbar-section-end">
          <md-button class="md-primary" @click="modalCliente = true"><md-icon>edit</md-icon>  Editar  </md-button>
        </div>
      </md-toolbar>

      <md-list class="md-triple-line">
        <md-subheader>Dados da conta</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">fingerprint</md-icon>

          <div class="md-list-item-text">
            <span>E-mail: {{ cliente.dadosPessoais.email }}</span>
            <span>Senha: {{ cliente.dadosPessoais.senha }}</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Dados pessoais</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">email</md-icon>

          <div class="md-list-item-text">
            <span>Nome e sobrenome: {{ cliente.dadosPessoais.nome }} {{ cliente.dadosPessoais.sobrenome }}</span>
            <span>Genero: {{ cliente.dadosPessoais.genero }}</span>
            <span>CPF: {{ cliente.dadosPessoais.cpf }}</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Contato</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">picture_in_picture_alt</md-icon>

          <div class="md-list-item-text">
            <span>Telefone: ({{ cliente.dadosPessoais.ddd }}) {{ cliente.dadosPessoais.telefone }}</span>
            <span>Tipo: {{ cliente.dadosPessoais.tipoTelefone }}</span>
            <span>Email: {{ cliente.dadosPessoais.email }}</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Data de nascimento</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">event</md-icon>

          <div class="md-list-item-text">
            <span>{{ cliente.dadosPessoais.dataNascimento }}</span>
          </div>
        </md-list-item>

      </md-list>

      <modal v-if="modalCliente" @close="fecharModalCliente">
        
          <template slot="body">
            <cad-dados-basicos v-bind:dados="{dadosPessoais: this.cliente.dadosPessoais}"></cad-dados-basicos>
          </template>
          <template slot="footer">
            <md-button class="md-danger md-sm" @click="fecharModalCliente">Cancelar</md-button>
            <md-button class="md-primary md-simple" @click="ValidarCliente">Salvar</md-button>
          </template>
      </modal>
    </div>
  </div>
</template>

<style>
.md-menu-content.md-select-menu {
    z-index: 9998 !important;
}
</style>


<script>
import { LoginCard } from "@/mk/components";
import CadDadosBasicos from '../cliente/CadDadosBasicos';
import { Modal } from "@/mk/components";
import { eventBus } from '../../main';
import axios from 'axios';
import {
  StatsCard,
  ChartCard,
  NavTabsCard,
  NavTabsTable,
  OrderedTable
} from '@/mk_admin/components'

export default{
  created(){
    var dadosAtuais = this; 
    this.cliente = this.$route.params.cliente;
    eventBus.$on('dadosValidoCliente', function(e){
      if(e == true){
        dadosAtuais.AtualizarCliente();
      }
    })
  },
  components: {
    StatsCard,
    ChartCard,
    NavTabsCard,
    NavTabsTable,
    OrderedTable,
    CadDadosBasicos,
    Modal,
    LoginCard
  },
  data: () => ({
    cliente: {},
    modalCliente : false

  }),
  methods: {
    fecharModalCliente() {
      this.modalCliente = false;
    },
    AtualizarCliente(){
      this.modalCliente = false;
      var dadosAtuais = this;
        axios.post(`http://localhost:8082/DarkBook/cliente?operacao=ALTERAR`, 
        this.cliente, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            }
        }).then(function(e){
            // alert(e.data)
            dadosAtuais.$router.replace({name: "perfil", params: { "id": dadosAtuais.cliente.dadosPessoais.id, "nome": dadosAtuais.cliente.dadosPessoais.primeiroNome, salvou: true }})
        }).catch(function(e){
            console.log(e)
            alert(e.response.data)
        })
    },
    ValidarCliente(){
      eventBus.$emit('validarDadosBasicos', true);
    }
  }
}
</script>
