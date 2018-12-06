<template>
    <div>
        <h3 class="text-center m-0 p-0">Cartão de Credito</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>call_to_action</md-icon>
                    <label>Número do cartão...</label>
                    <md-input v-mask="'####.####.####.####'" id="numero" v-model="numero"></md-input>
                    <span class="md-error erros">O numero do cartão tem que ter 16 digitos</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>https</md-icon>
                    <label>Código de segurança...</label>
                    <md-input v-mask="'###'" id="codigo" v-model="codSeguranca"></md-input>
                    <span class="md-error erros">O código de segurança tem que ter 3 digitos</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>payment</md-icon>
                    <label>Bandeira</label>
                    <md-select id="bandeira" v-model="bandeira" class="select-option">
                        <md-option v-for="bandeira in bandeiras" class="select"  v-bind:value="bandeira">{{bandeira | firstUpperCase() }}</md-option>
                    </md-select>
                    <span class="md-error erros">A bandeira é obrigatória</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group" >
                    <md-icon>perm_identity</md-icon>
                    <label>Nome Impresso...</label>
                    <md-input id="nomeImpresso" v-model="nomeImpresso"></md-input>
                    <span class="md-error erros">O nome necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-icon class="icon-cartao">favorite_border</md-icon>
                <md-checkbox v-model="preferencial">Cartão favorido?</md-checkbox>
            </div>
        </div>
    </div>
</template>


<script>
import { eventBus } from '../../main';

export default {
    data: () => ({
        bandeiras : [],
        bandeira : "",
        numero : "",
        nomeImpresso : "",
        codSeguranca : "",
        preferencial : false
    }),
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosCartao', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        })
        this.preencherDados
         
        if(this.dados != null){
            this.bandeira = this.dados.cartoes.bandeira
            this.numero = this.dados.cartoes.numero
            this.nomeImpresso = this.dados.cartoes.nomeImpresso
            this.codSeguranca = this.dados.cartoes.codSeguranca
            this.preferencial = this.dados.cartoes.preferencial
        }

    },
    props:['dados'],
    filters: {
        firstUpperCase(str){
            return str.toLowerCase().replace(/(?:^)\S/g, function(a) { return a.toUpperCase(); });
        }
    },
    computed: {
        preencherDados(){
            this.bandeiras = this.$store.state.bandeiras.tipos;
        }
    },
    methods:{
        validar(){
            var erro = false;

            var regCartao = /\d{4}\.\d{4}\.\d{4}\.\d{4}/;
            if(!regCartao.test(this.numero)){
                this.corErroInput("numero")
                erro = true;
            }

            if(this.codSeguranca.trim().length != 3){
                this.corErroInput("codigo")
                erro = true;
            }

            if(this.bandeira == ""){
                this.corErroSelect("bandeira");
                erro = true;
            }

            if(this.nomeImpresso.trim().length < 3){
                this.corErroInput("nomeImpresso");
                erro = true;
            }

            if(erro == false){
                this.dados.cartoes.bandeira = this.bandeira
                this.dados.cartoes.numero = this.numero
                this.dados.cartoes.nomeImpresso = this.nomeImpresso
                this.dados.cartoes.codSeguranca = this.codSeguranca
                this.dados.cartoes.preferencial = this.preferencial
                eventBus.$emit('page', 4);
                this.$emit('dados-valido-cliente',true);
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


