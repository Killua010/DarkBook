<template>
    <div>
        <h3 class="text-center m-0 p-0">Endereço de entrega</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>business</md-icon>
                    <label>Tipo de endereco</label>
                    <md-select id="tpEndereco" v-model="dados.enderecosEntrega.tipoResidencia" class="select-option">
                        <md-option class="select" value="APARTAMENTO">Apartamento</md-option>
                        <md-option class="select" value="CASA">Casa</md-option>
                        <md-option class="select" value="COMERCIAL">Comercial</md-option>
                    </md-select>
                    <span class="md-error erros">O tipo de endereço é obrigadório</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>place</md-icon>
                    <label>CEP...</label>
                    <md-input v-mask="'#####-###'" v-on:keyup="buscarCep" id="cep" v-model="dados.enderecosEntrega.cep"></md-input>
                     <span class="md-error erros">O cep tem que ter 8 digitos</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>explore</md-icon>
                    <label>Tipo Logradouro</label>
                    <md-select id="tpLogradouro" v-model="dados.enderecosEntrega.tipoLogradouro" class="select-option">
                        <md-option class="select" value="RUA">Rua</md-option>
                        <md-option class="select" value="AVENIDA">Avenida</md-option>
                        <md-option class="select" value="VIELA">Viela</md-option>
                    </md-select>
                    <span class="md-error erros">O tipo de logradouro é obrigadório</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_searching</md-icon>
                    <label>Logradouro...</label>
                    <md-input id="logradouro" v-model="dados.enderecosEntrega.logradouro"></md-input>
                    <span class="md-error erros">O logradouro necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_city</md-icon>
                    <label>Cidade...</label>
                    <md-input id="cidade" v-model="dados.enderecosEntrega.cidade"></md-input>
                    <span class="md-error erros">A cidade necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>map</md-icon>
                    <label>Estado...</label>
                    <md-input id="estado" v-model="dados.enderecosEntrega.estado"></md-input>
                    <span class="md-error erros">O estado necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>public</md-icon>
                    <label>Pais...</label>
                    <md-input id="pais" v-model="dados.enderecosEntrega.pais"></md-input>
                    <span class="md-error erros">O pais necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>store_mall_directory</md-icon>
                    <label>Bairro...</label>
                    <md-input id="bairro" v-model="dados.enderecosEntrega.bairro"></md-input>
                    <span class="md-error erros">O bairro necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>home</md-icon>
                    <label>Numero...</label>
                    <md-input type="number" min="1" id="numero" v-model="dados.enderecosEntrega.numero"></md-input>
                    <span class="md-error erros">O numero é obrigatório</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>directions</md-icon>
                    <label>Complemento...</label>
                    <md-input id="complemento" v-model="dados.enderecosEntrega.observacao"></md-input>
                    <span class="md-error erros">O complemento necessida ter no minimo 5 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>account_box</md-icon>
                    <label>Nome Composto...</label>
                    <md-input id="nomeComposto" v-model="dados.enderecosEntrega.nomeComposto"></md-input>
                    <span class="md-error erros">O nome necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-icon class="icon-endereco">favorite_border</md-icon>
                <md-checkbox v-model="dados.enderecosEntrega.favorito">Endereço favorido?</md-checkbox>
            </div>
        </div>
    </div>
</template>

<script>
import { eventBus } from '../../../main';

export default {
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosEndereco', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        })
    },
    props:['dados'],
    methods:{
        validar(){
            var erro = false;

            var regCep = /\d{5}-?\d{3}/;

            if(this.dados.enderecosEntrega.tipoResidencia == ""){
                this.corErroSelect("tpEndereco");
                erro = true;
            }

            if(!regCep.test(this.dados.enderecosEntrega.cep)){
                this.corErroInput("cep");
                erro = true;
            }

            if(this.dados.enderecosEntrega.tipoLogradouro == ""){
                this.corErroSelect("tpLogradouro");
                erro = true;
            }

            if(this.dados.enderecosEntrega.logradouro.trim().length <= 3){
                this.corErroInput("logradouro");
                erro = true;
            }

            if(this.dados.enderecosEntrega.cidade.trim().length <= 3){
                this.corErroInput("cidade");
                erro = true;
            }

            if(this.dados.enderecosEntrega.estado.trim().length < 2){
                this.corErroInput("estado");
                erro = true;
            }

            if(this.dados.enderecosEntrega.pais.trim().length <= 3){
                this.corErroInput("pais");
                erro = true;
            }

            if(this.dados.enderecosEntrega.bairro.trim().length <= 3){
                this.corErroInput("bairro");
                erro = true;
            }

            if(this.dados.enderecosEntrega.numero <= 0){
                this.corErroInput("numero");
                erro = true;
            }

            if(this.dados.enderecosEntrega.observacao.trim().length > 0
                && this.dados.enderecosEntrega.observacao.trim().length < 5){
                this.corErroInput("complemento");
                erro = true;
            }

            if(this.dados.enderecosEntrega.nomeComposto.trim().length <= 3){
                this.corErroInput("nomeComposto");
                erro = true;
            }

            if(erro == false){
                eventBus.$emit('request','proximo');
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
        },
        buscarCep: function() {
            var self = this;
            // Nova variável "cep" somente com dígitos.
            var cep = $("#cep").val().replace(/\D/g, '');

            //Verifica se campo cep possui valor informado.
            if (cep != "") {
                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if(validacep.test(cep)) {
                    //Consulta o webservice viacep.com.br/
                    $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dadosEndereco) {

                        if (!("erro" in dadosEndereco)) {
                            self.dados.enderecosEntrega.logradouro = dadosEndereco.logradouro;
                            //Atualiza os campos com os valores da consulta.
                            self.dados.enderecosEntrega.bairro = dadosEndereco.bairro
                            self.dados.enderecosEntrega.cidade = dadosEndereco.localidade
                            self.dados.enderecosEntrega.estado = dadosEndereco.uf
                            self.dados.enderecosEntrega.pais = "Brasil"
                        } //end if.
                       
                    });


                } //end if.
            } //end if.
        }   
    }
}
</script>


<style>
.icon-endereco{
    margin-right: 5px !important;  
    margin-top:-5px!important;
}
</style>

