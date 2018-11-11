<template>
  <div class="content">
    <div class="viewport">
      <md-toolbar class="md-primary">
        <span class="md-title md-toolbar-section-start">Endereço de Cobrança</span>
      </md-toolbar>
    </div>
    <div class="md-layout">
      <div class="md-layout-item md-medium-size-100 md-size-33">
          <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star_border</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Rua Sardonica, 80, JD. Nicea, Casa<br>
              08589-589<br>
              Itaquaquecetuba, São Paulo, Brasil<br>
              Próximo ao CDHU
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoCobranca = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Rua Sardonica, 80, JD. Nicea, Casa<br>
              08589-589<br>
              Itaquaquecetuba, São Paulo, Brasil<br>
              Próximo ao CDHU
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoCobranca = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-card>
             <md-card-actions>
              <md-button class="md-primary md-just-icon md-round"><md-icon>star</md-icon></md-button>
            </md-card-actions>
            <md-card-content>
              Rua Sardonica, 80, JD. Nicea, Casa<br>
              08589-589<br>
              Itaquaquecetuba, São Paulo, Brasil<br>
              Próximo ao CDHU
            </md-card-content>

            <md-card-actions>
              <md-button class="md-danger">Deletar</md-button>
              <md-button class="md-primary" @click="modalEnderecoCobranca = true">Editar</md-button>
            </md-card-actions>
          </md-card>
      </div>

      <div class="md-layout-item md-medium-size-100 md-size-33">
        <md-toolbar class="md-white">
          <md-button class="md-white">
            <md-icon>add_circle</md-icon>
            <span class="md-title md-toolbar-section-start" @click="modalEnderecoCobranca = true">Novo Endereço</span>
          </md-button>
        </md-toolbar> 
      </div>
    </div>
    <modal v-if="modalEnderecoCobranca" @close="fecharModalEnderecoCobranca">
      <template slot="body">
        <cad-dados-endereco v-bind:dados="{enderecosCobranca: this.cliente.enderecosCobranca}"></cad-dados-endereco>
      </template>
      <template slot="footer">
        <md-button class="md-danger md-sm">Cancelar</md-button>
        <md-button class="md-primary md-simple" @click="fecharModalEnderecoCobranca">Salvar</md-button>
      </template>
  </modal>
  </div>
</template>

<script>
import { Modal } from "@/mk/components";
import CadDadosEndereco from '../cliente/CadDadosEndereco'
import {
  EditProfileForm,
  UserCard
} from '@/mk_admin/pages'

export default{
  data: () => ({
    cliente:{
      enderecosCobranca : {
        tipoResidencia : "CASA",
        tipoLogradouro : "RUA",
        pais : "",
        estado : "",
        cidade : "",
        logradouro : "",
        numero : "40",
        bairro : "",
        cep: "",
        observacao : ""
    }
  },
  modalEnderecoCobranca : false
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
