<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Cartões</span>
      </md-toolbar>
    </div>
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-33">
          <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star_border</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Visa<br>
              111111######1111<br>
              Daniel Dias de Souza<br>
              Validade: 10/2022
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalCartao = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Visa<br>
              111111######1111<br>
              Daniel Dias de Souza<br>
              Validade: 10/2022
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalCartao = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Visa<br>
              111111######1111<br>
              Daniel Dias de Souza<br>
              Validade: 10/2022
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalCartao = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-toolbar class="md-white">
          <md-button class="md-white">
            <md-icon>add_circle</md-icon>
            <span class="md-title md-toolbar-section-start" @click="modalCartao = true">Novo Cartão</span>
          </md-button>
        </md-toolbar> 
      </div>
    </div>
    <modal v-if="modalCartao" @close="fecharModalCartao">
      <template slot="body">
        <cad-dados-cartao v-bind:dados="{cartoes: this.cliente.cartoes}"></cad-dados-cartao>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="fecharModalCartao">Salvar</md-button>
      </template>
  </modal>
  </div>
</template>

<script>
import { Modal } from "@/mk/components";
import CadDadosCartao from '../cliente/CadDadosCartao'
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  data: () => ({
    cliente:{
      cartoes : {
        bandeira : "VISA",
        numero : "1111.1111.1111.1111",
        nomeImpresso : "ASDASDSA",
        codSeguranca : "123",
        preferencial : false
    }  
  },
  modalCartao : false
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
