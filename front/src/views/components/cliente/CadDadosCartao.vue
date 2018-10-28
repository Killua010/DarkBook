<template>
    <div>
        <h3 class="text-center m-0 p-0">Cartão de Credito</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>call_to_action</md-icon>
                    <label>Número do cartão...</label>
                    <md-input id="numero" v-model="dados.cartoes.numero"></md-input>
                    <span class="md-error erros">O numero do cartão tem que ter 16 digitos</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>https</md-icon>
                    <label>Código de segurança...</label>
                    <md-input id="codigo" v-model="dados.cartoes.codSeguranca"></md-input>
                    <span class="md-error erros">O código de segurança tem que ter 3 digitos</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>payment</md-icon>
                    <label>Bandeira</label>
                    <md-select id="bandeira" v-model="dados.cartoes.bandeira" class="select-option">
                        <md-option class="select" value="VISA">Visa</md-option>
                        <md-option class="select" value="MASTER">Master</md-option>
                        <md-option class="select" value="ELO">Elo</md-option>
                    </md-select>
                    <span class="md-error erros">A bandeira é obrigatória</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>perm_identity</md-icon>
                    <label>Nome Impresso...</label>
                    <md-input id="nomeImpresso" v-model="dados.cartoes.nomeImpresso"></md-input>
                    <span class="md-error erros">O nome necessida ter no minimo 3 caracteres</span>
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
import { eventBus } from '../../../main';

export default {
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosCartao', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        })
    },
    props:['dados'],
    methods:{
        validar(){
            var erro = false;

            var regCartao = /\d{4}\.\d{4}\.\d{4}\.\d{4}/;

            if(!regCartao.test(this.dados.cartoes.numero)){
                this.corErroInput("numero")
                erro = true;
            }

            if(this.dados.cartoes.codSeguranca.trim().length != 3){
                this.corErroInput("codigo")
                erro = true;
            }

            if(this.dados.cartoes.bandeira == ""){
                this.corErroSelect("bandeira");
                erro = true;
            }

            if(this.dados.cartoes.nomeImpresso.trim().length < 3){
                this.corErroInput("nomeImpresso");
                erro = true;
            }

            if(erro == false){
                eventBus.$emit('request','salvar');
            }

        },
        corErroInput(elemento){
            var elementoAtual = document.getElementById(elemento).parentElement;
            elementoAtual.classList.add("md-error")
            elementoAtual.classList.add("md-invalid")
        },
        corErroSelect(elemento){
            var elementoAtual = document.getElementById(elemento).parentElement.parentElement;
            elementoAtual.classList.add("md-error")
            elementoAtual.classList.add("md-invalid")
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


