<template>
    <div>
        <h3 class="text-center m-0 p-0">Endereço de entrega</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>business</md-icon>
                    <label>Tipo de endereco</label>
                    <md-select v-model="dados.enderecosEntrega.tipoResidencia" class="select-option">
                        <md-option class="select" value="APARTAMENTO">Apartamento</md-option>
                        <md-option class="select" value="CASA">Casa</md-option>
                        <md-option class="select" value="COMERCIAL">Comercial</md-option>
                    </md-select>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>place</md-icon>
                    <label>CEP...</label>
                    <md-input v-model="dados.enderecosEntrega.cep"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>explore</md-icon>
                    <label>Tipo Logradouro</label>
                    <md-select v-model="dados.enderecosEntrega.tipoLogradouro" class="select-option">
                        <md-option class="select" value="RUA">Rua</md-option>
                        <md-option class="select" value="AVENIDA">Avenida</md-option>
                        <md-option class="select" value="VIELA">Viela</md-option>
                    </md-select>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_searching</md-icon>
                    <label>Logradouro...</label>
                    <md-input v-model="dados.enderecosEntrega.logradouro"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_city</md-icon>
                    <label>Cidade...</label>
                    <md-input v-model="dados.enderecosEntrega.cidade"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>map</md-icon>
                    <label>Estado...</label>
                    <md-input v-model="dados.enderecosEntrega.estado"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>public</md-icon>
                    <label>Pais...</label>
                    <md-input v-model="dados.enderecosEntrega.pais"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>store_mall_directory</md-icon>
                    <label>Bairro...</label>
                    <md-input v-model="dados.enderecosEntrega.bairro"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>home</md-icon>
                    <label>Numero...</label>
                    <md-input v-model="dados.enderecosEntrega.numero"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>directions</md-icon>
                    <label>Complemento...</label>
                    <md-input v-model="dados.enderecosEntrega.observacao"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>account_box</md-icon>
                    <label>Nome Composto...</label>
                    <md-input v-model="dados.enderecosEntrega.nomeComposto"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-icon class="icon-endereco">favorite_border</md-icon>
                <md-checkbox v-model="dados.enderecosEntrega.favorito">Endereço favorido?</md-checkbox>
            </div>
        <button @click="enviar()">teste</button>
        </div>
    </div>
</template>

<script>


export default {
    props:['dados'],
    data() {
        return{
            
        }
    },
    methods: {
          
      }
}
</script>


<style>
.icon-endereco{
    margin-right: 5px !important;  
    margin-top:-5px!important;
}
</style>

