<template>
    <div>
        <h3 class="text-center m-0 p-0">Dados Pessoais</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>face</md-icon>
                    <label>Primeiro Nome...</label>
                    <md-input id="primeiroNome" v-model="dados.dadosPessoais.primeiroNome"></md-input>
                    <span class="md-error erros">O nome necessida ter no minimo 3 caracteres</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>face</md-icon>
                    <label>Sobrenome...</label>
                    <md-input id="sobrenome" v-model="dados.dadosPessoais.sobrenome"></md-input>
                    <span class="md-error erros">O sobrenome necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>  

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>mail</md-icon>
                    <label>Email...</label>
                    <md-input id="email" type="email" v-model="dados.dadosPessoais.email"></md-input>
                    <span class="md-error erros">O email tem que estar no formato nome@dominio.com</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>wc</md-icon>
                    <label>Gênero</label>
                    <md-select id="genero" v-model="dados.dadosPessoais.genero" class="select-option">
                        <md-option class="select" value="MASCULINO">Masculino</md-option>
                        <md-option class="select" value="FEMININO">Feminino</md-option>
                        <md-option class="select" value="NAOBINARIO">Não binário</md-option>
                    </md-select>
                    <span class="md-error erros">O gênero é obrigatório</span>
                </md-field>
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>picture_in_picture_alt</md-icon>
                    <label>CPF...</label>
                    <md-input id="cpf" v-model="dados.dadosPessoais.cpf"></md-input>
                    <span class="md-error erros">O cpf tem que ter 11 digitos</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>event</md-icon>
                    <label>Data de nascimento...</label>
                    <md-input id="dtNascimento" v-model="dados.dadosPessoais.dataNascimento"></md-input>
                    <span class="md-error erros">A data tem que estar no formato DD/MM/AAAA</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>contact_phone</md-icon>
                    <label>Tipo Telefone</label>
                    <md-select id="tpTelefone" v-model="dados.dadosPessoais.tipoTelefone" class="select-option">
                        <md-option class="select" value="FIXO">Fixo</md-option>
                        <md-option class="select" value="MOVEL">Movel</md-option>
                    </md-select>
                    <span class="md-error erros">O tipo de telefone é obrigatório</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>local_phone</md-icon>
                    <label>Telefone...</label>
                    <md-input id="telefone" v-model="dados.dadosPessoais.telefone"></md-input>
                    <span class="md-error erros">O telefone informado é invalido</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>fingerprint</md-icon>
                    <label>Senha...</label>
                    <md-input id="senha1" v-model="dados.dadosPessoais.senha1" type="password"></md-input>
                    <span class="md-error erros">A senha tem que ter mais que 5 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>fingerprint</md-icon>
                    <label>Digite novamente sua senha...</label>
                    <md-input id="senha2" v-model="dados.dadosPessoais.senha2" type="password"></md-input>
                    <span class="md-error erros">As senhas precisam ser iguais</span>
                </md-field> 
            </div>
        </div>
    </div>
</template>

<script>
    // import axios from 'axios';
import {TheMask} from 'vue-the-mask'
import { eventBus } from '../../../main';

  export default {
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosBasicos', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        })
    },
    props:['dados'],
    methods:{
        validar(){
            var erro = false;

            var regEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

            var regTelefone = /\(\d{2}\)\s?\d{4,5}-?\d{4}/;

            if(this.dados.dadosPessoais.primeiroNome.trim().length < 3){
                this.corErroInput("primeiroNome")
                erro = true;
            }

            if(this.dados.dadosPessoais.sobrenome.trim().length < 3){
                this.corErroInput("sobrenome")
                erro = true;
            }
            
            if(!regEmail.test(this.dados.dadosPessoais.email)){
                this.corErroInput("email")
                erro = true;
            }

            if(this.dados.dadosPessoais.genero == ""){
                this.corErroSelect("genero")
                erro = true;
            }

            if(this.dados.dadosPessoais.cpf.trim().length != 11){
                this.corErroInput("cpf")
                erro = true;
            }

            if(this.dados.dadosPessoais.dataNascimento.trim().length == 0){
                this.corErroInput("dtNascimento")
                erro = true;
            }

            if(this.dados.dadosPessoais.tipoTelefone == ""){
                this.corErroSelect("tpTelefone")
                erro = true;
            }

            if(!regTelefone.test(this.dados.dadosPessoais.telefone)){
                this.corErroInput("telefone")
                erro = true;
            }
            
            if(this.dados.dadosPessoais.senha1.trim().length < 5){
                this.corErroInput("senha1")
                erro = true;
            }

            if(this.dados.dadosPessoais.senha2.trim().length < 5
                || this.dados.dadosPessoais.senha2 != this.dados.dadosPessoais.senha1){
                this.corErroInput("senha2")
                erro = true;
            }

            if(erro === false){
                eventBus.$emit('request','proximo');
            }

        },
        corErroInput(elemento){
            var elemento = document.getElementById(elemento).parentElement;
            elemento.classList.add("md-error")
            elemento.classList.add("md-invalid")
        },
        corErroSelect(elemento){
            var elemento = document.getElementById(elemento).parentElement.parentElement;
            elemento.classList.add("md-error")
            elemento.classList.add("md-invalid")
        }
    }

  }

</script>
<style>
.select button{
    padding-left: 10px;
}

.select-option input{
    margin-left: 12px;
}
</style>

