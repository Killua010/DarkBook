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
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              {{ cartao.bandeira }}<br>
              {{ cartao.numero }}<br>
              {{ cartao.nomeImpresso }}<br>
              Cod segurança: {{ cartao.codSeguranca }}
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
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
        <cad-dados-cartao v-if="indexCartao != null" v-bind:dados="{cartoes: this.cliente.cartoes[indexCartao]}"></cad-dados-cartao>
        <cad-dados-cartao v-else v-bind:dados="{cartoes: this.cartao}"></cad-dados-cartao>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="fecharModalCartao">Salvar</md-button>
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
    modalCartao : false,
    indexCartao: null,
    cartao : {
      bandeira : "",
      numero : "",
      nomeImpresso : "",
      codSeguranca : "",
      preferencial : false
  }
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
    }
  }
}
</script>
