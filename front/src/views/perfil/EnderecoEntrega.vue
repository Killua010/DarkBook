<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Endereço de Entrega</span>
      </md-toolbar>
    </div>
    <div class="md-layout">
      <div v-for="(endereco, index) in cliente.enderecosEntrega" class="md-layout-item md-medium-size-100 md-size-33">
          <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star_border</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              {{ endereco.nomeComposto }}<br>
              {{ endereco.tipoLogradouro }} {{ endereco.logradouro }}, {{ endereco.numero }}, {{ endereco.bairro }}, {{ endereco.tipoResidencia }}<br>
              {{ endereco.cep }}<br>
              {{ endereco.cidade }}, {{ endereco.estado }}, {{ endereco.pais }}<br>
              {{ endereco.complemento }}
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoEntrega = true; indexEndereco = index">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-toolbar class="md-white my-30">
          <md-button class="md-white">
            <md-icon>add_circle</md-icon>
            <span class="md-title md-toolbar-section-start" @click="modalEnderecoEntrega = true; indexEndereco = null">Novo Endereço</span>
          </md-button>
        </md-toolbar> 
      </div>
    </div>
    <modal v-if="modalEnderecoEntrega" @close="fecharModalEnderecoEntrega">
      <template slot="body">
        <cad-dados-endereco-entrega v-if="indexEndereco != null" v-bind:dados="{enderecosEntrega: this.cliente.enderecosEntrega[indexEndereco]}"></cad-dados-endereco-entrega>
        <cad-dados-endereco-entrega v-else v-bind:dados="{enderecosEntrega: this.enderecosEntrega}"></cad-dados-endereco-entrega>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="fecharModalEnderecoEntrega">Salvar</md-button>
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
import CadDadosEnderecoEntrega from '../cliente/CadDadosEnderecoEntrega'
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  created(){
    this.cliente = this.$route.params.cliente
    console.log(this.cliente.enderecosEntrega)
  },
  data: () => ({
  cliente:{},
  modalEnderecoEntrega : false,
  indexEndereco: null,
  enderecosEntrega : {
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
    nomeComposto : "",
    favorito : false
}
}),
  components: {
    EditProfileForm,
    UserCard,
    Modal,
    CadDadosEnderecoEntrega
  },
  methods: {
    fecharModalEnderecoEntrega() {
      this.modalEnderecoEntrega = false;
    }
  }
}
</script>
