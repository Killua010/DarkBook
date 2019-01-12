<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title">Dados Pessoais</span>
        <md-button class="md-danger md-sm" @click="inativarCliente">Inativar Conta</md-button>
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
            <cad-dados-basicos @dados-valido-cliente="valido" v-bind:dados="{dadosPessoais: this.cliente.dadosPessoais}"></cad-dados-basicos>
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
  mounted(){
    var dadosAtuais = this; 
    this.dadosCliente;
  },
  computed: {
    dadosCliente(){
      this.cliente = this.$store.state.cliente.dados;
    }
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
    valido(){
      this.AtualizarCliente();
    },
    inativarCliente(){
      let dadosAtuais = this;
      swal({
        title: 'Tem certeza que deseja inativar sua conta?',
        text: "Após inativado, caso queira recuperar sua conta, entre em contato com a nossa equipe de desenvolvimento!",
        icon: 'warning',
        buttons: {
          cancel: 'Não, cancelar',
          confirm: {
            text: 'Sim, desejo deletar!'
            }
        },
      }).then((result) => {
        if (result) {
          axios.post(`http://localhost:8082/DarkBook/cliente?operacao=EXCLUIR`, 
            dadosAtuais.cliente, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                }
            }).then(function(e){
              swal({
                  title: e.data,
                  icon: "success"
                });
                let cliente = {
                  id: null,
                  nome: ""
                }
                dadosAtuais.$store.commit('ALTERAR_USUARIO', cliente)
                dadosAtuais.$router.replace({name: "login"})
            }).catch(function(e){
                console.log(e)
                alert(e.response.data)
            })  
        }
      })
    },
    fecharModalCliente() {
      this.modalCliente = false;
    },
    AtualizarCliente(){
      $.blockUI({
          message: '<i class="fa fa-circle-notch fa-spin fa-5x"></i>' ,
    			css: { 
    				border: 'none',
    				backgroundColor: 'transparent',
    				color: '#f6f6f6'
    			}
       });
        this.modalCliente = false;
        let dadosAtuais = this;
        axios.post(`http://localhost:8082/DarkBook/cliente?operacao=ALTERAR`, 
          this.cliente, {
              headers: {
                  'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
              }
          }).then(function(e){
            $.unblockUI();
            dadosAtuais.$router.push({name: "Dados_Pessoais"})
            swal({
              title: "Alterado com sucesso",
              icon: "success"
            });
            eventBus.$emit('atualizarPagina', true);
          }).catch(function(e){
              $.unblockUI();
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
