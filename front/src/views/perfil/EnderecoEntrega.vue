<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Endereço de Entrega</span>
      </md-toolbar>
    </div>
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-33">
          <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star_border</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Daniel Dias de Souza<br>
              Rua Sardonica, 80, JD. Nicea, Casa<br>
              08589-589<br>
              Itaquaquecetuba, São Paulo, Brasil<br>
              Próximo ao CDHU
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoEntrega = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Daniel Dias de Souza<br>
              Rua Sardonica, 80, JD. Nicea, Casa<br>
              08589-589<br>
              Itaquaquecetuba, São Paulo, Brasil<br>
              Próximo ao CDHU
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoEntrega = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Daniel Dias de Souza<br>
              Rua Sardonica, 80, JD. Nicea, Casa<br>
              08589-589<br>
              Itaquaquecetuba, São Paulo, Brasil<br>
              Próximo ao CDHU
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoEntrega = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-toolbar class="md-white">
          <md-button class="md-white">
            <md-icon>add_circle</md-icon>
            <span class="md-title md-toolbar-section-start" @click="modalEnderecoEntrega = true">Novo Endereço</span>
          </md-button>
        </md-toolbar> 
      </div>
    </div>
    <modal v-if="modalEnderecoEntrega" @close="fecharModalEnderecoEntrega">
      <template slot="body">
        <cad-dados-endereco-entrega v-bind:dados="{enderecosEntrega: this.cliente.enderecosEntrega}"></cad-dados-endereco-entrega>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="fecharModalEnderecoEntrega">Salvar</md-button>
      </template>
  </modal>
  </div>
</template>

<script>
import { Modal } from "@/mk/components";
import CadDadosEnderecoEntrega from '../cliente/CadDadosEnderecoEntrega'
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  data: () => ({
    cliente:{
      enderecosEntrega:{
        tipoResidencia : "CASA",
        tipoLogradouro : "RUA",
        pais : "",
        estado : "",
        cidade : "",
        logradouro : "",
        numero : "20",
        bairro : "",
        cep: "",
        observacao : "",
        nomeComposto : "ASDASD",
        favorito : false
    }
  },
  modalEnderecoEntrega : false
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
