<template>
    <div>
        <h3 class="text-center m-0 p-0">Cartão de Credito</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>call_to_action</md-icon>
                    <label>Número do cartão...</label>
                    <md-input v-model="dados.cartoes.numero"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>https</md-icon>
                    <label>Código de segurança...</label>
                    <md-input v-model="dados.cartoes.codSeguranca"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>payment</md-icon>
                    <label>Bandeira</label>
                    <md-select v-model="dados.cartoes.bandeira" class="select-option">
                        <md-option class="select" value="VISA">Visa</md-option>
                        <md-option class="select" value="MASTER">Master</md-option>
                        <md-option class="select" value="ELO">Elo</md-option>
                    </md-select>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>perm_identity</md-icon>
                    <label>Nome Impresso...</label>
                    <md-input v-model="dados.cartoes.nomeImpresso"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-icon class="icon-cartao">favorite_border</md-icon>
                <md-checkbox v-model="dados.cartoes.preferencial">Endereço favorido?</md-checkbox>
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


<style>
.icon-cartao{
    margin-right: 5px !important;  
    margin-top:-5px!important;
}
</style>


