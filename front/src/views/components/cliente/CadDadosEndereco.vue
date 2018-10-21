<template>
    <div>
        <h3 class="text-center m-0 p-0">Endereço de cobrança</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>business</md-icon>
                    <label>Tipo de endereco</label>
                    <md-select v-model="dados.enderecosCobranca.tipoResidencia" class="select-option">
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
                    <md-input v-model="dados.enderecosCobranca.cep"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>explore</md-icon>
                    <label>Tipo Logradouro</label>
                    <md-select v-model="dados.enderecosCobranca.tipoLogradouro" class="select-option">
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
                    <md-input v-model="dados.enderecosCobranca.logradouro"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_city</md-icon>
                    <label>Cidade...</label>
                    <md-input v-model="dados.enderecosCobranca.cidade"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>map</md-icon>
                    <label>Estado...</label>
                    <md-input v-model="dados.enderecosCobranca.estado"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>public</md-icon>
                    <label>Pais...</label>
                    <md-input v-model="dados.enderecosCobranca.pais"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>store_mall_directory</md-icon>
                    <label>Bairro...</label>
                    <md-input v-model="dados.enderecosCobranca.bairro"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>home</md-icon>
                    <label>Numero...</label>
                    <md-input v-model="dados.enderecosCobranca.numero"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>directions</md-icon>
                    <label>Complemento...</label>
                    <md-input v-model="dados.enderecosCobranca.observacao"></md-input>
                </md-field> 
            </div>
        </div>
    </div>
</template>

<script>

export default {
    props:['dados'],
    data() {
        return{
            
        }
    }
        
}
</script>
