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
            <span>E-mail: daniel160598@hotmail.com</span>
            <span>Senha: 123456</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Dados pessoais</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">email</md-icon>

          <div class="md-list-item-text">
            <span>Nome e sobrenome: Daniel Dias de Souza</span>
            <span>Genero: Masculino</span>
            <span>CPF: 08589589</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Contato</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">picture_in_picture_alt</md-icon>

          <div class="md-list-item-text">
            <span>Telefone: (11) 97590-5803</span>
            <span>Tipo: Movel</span>
            <span>Email: daniel160598@hotmail.com</span>
          </div>
        </md-list-item>

        <md-divider></md-divider>
        <md-subheader>Data de nascimento</md-subheader>
        <md-list-item>
          <md-icon class="md-primary">event</md-icon>

          <div class="md-list-item-text">
            <span>16/05/1998</span>
          </div>
        </md-list-item>

      </md-list>

      <modal v-if="modalCliente" @close="fecharModalCliente">
        
          <template slot="body">
            <cad-dados-basicos v-bind:dados="{dadosPessoais: this.cliente.dadosPessoais}"></cad-dados-basicos>
          </template>
          <template slot="footer">
            <md-button class="md-danger md-sm">Cancelar</md-button>
            <md-button class="md-primary md-simple" @click="fecharModalCliente">Salvar</md-button>
          </template>
      </modal>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/mk/components";
import CadDadosBasicos from '../cliente/CadDadosBasicos';
import { Modal } from "@/mk/components";
import {
  StatsCard,
  ChartCard,
  NavTabsCard,
  NavTabsTable,
  OrderedTable
} from '@/mk_admin/components'

export default{
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
    cliente:{
      dadosPessoais:{
        primeiroNome : "Daniel",
        sobrenome : "Dias de Souza",
        email : "daniel160598@hotmail.com",
        genero : "MASCULINO",
        cpf : "45856020898",
        dataNascimento : "16/05/1998",
        tipoTelefone : "MOVEL",
        telefone : "",
        ddd: "",
        senha1 : "123456",
        senha2 : "123456",
        senha : ""
      }
    },
    modalCliente : false

  }),
  methods: {
    fecharModalCliente() {
      this.modalCliente = false;
    }
  }
}
</script>
