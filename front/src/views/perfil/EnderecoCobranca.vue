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
              <md-button class="md-primary md-just-icon md-round"><md-icon>star_border</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              {{ endereco.tipoLogradouro }} {{ endereco.logradouro }}, {{ endereco.numero }}, {{ endereco.bairro }}, {{ endereco.tipoResidencia }}<br>
              {{ endereco.cep }}<br>
              {{ endereco.cidade }}, {{ endereco.estado }}, {{ endereco.pais }}<br>
              {{ endereco.complemento }}
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
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
        <cad-dados-endereco v-if="indexEndereco != null" v-bind:dados="{enderecosCobranca: this.cliente.enderecosCobranca[indexEndereco]}"></cad-dados-endereco>
        <cad-dados-endereco v-else v-bind:dados="{enderecosCobranca: this.enderecosCobranca}"></cad-dados-endereco>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="fecharModalEnderecoCobranca">Salvar</md-button>
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
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  created(){
    this.cliente = this.$route.params.cliente
  },
  data: () => ({
    cliente:{},
    modalEnderecoCobranca : false,
    indexEndereco : null,
    enderecosCobranca : {
      tipoResidencia : "",
      tipoLogradouro : "",
      pais : "",
      estado : "",
      cidade : "",
      logradouro : "",
      numero : "",
      bairro : "",
      cep : "",
      observacao : ""
  }
}),
  components: {
    EditProfileForm,
    UserCard,
    Modal,
    CadDadosEndereco
  },
  methods: {
    fecharModalEnderecoCobranca() {
      this.modalEnderecoCobranca = false;
    }
  }
}
</script>
