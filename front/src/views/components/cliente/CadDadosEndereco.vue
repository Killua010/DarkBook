<template>
    <div>
        <h3 class="text-center m-0 p-0">Endereço de cobrança</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>business</md-icon>
                    <label>Tipo de endereco</label>
                    <md-select id="tpEndereco" v-model="dados.enderecosCobranca.tipoResidencia" class="select-option">
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
                    <md-input v-on:keyup="buscarCep" id="cep" v-model="dados.enderecosCobranca.cep"></md-input>
                    <span class="md-error erros">O cep tem que ter 8 digitos</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>explore</md-icon>
                    <label>Tipo Logradouro</label>
                    <md-select id="tpLogradouro" v-model="dados.enderecosCobranca.tipoLogradouro" class="select-option">
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
                    <md-input id="logradouro" v-model="dados.enderecosCobranca.logradouro"></md-input>
                    <span class="md-error erros">O logradouro necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_city</md-icon>
                    <label>Cidade...</label>
                    <md-input id="cidade" v-model="dados.enderecosCobranca.cidade"></md-input>
                    <span class="md-error erros">A cidade necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>map</md-icon>
                    <label>Estado...</label>
                    <md-input id="estado" v-model="dados.enderecosCobranca.estado"></md-input>
                    <span class="md-error erros">O estado necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>public</md-icon>
                    <label>Pais...</label>
                    <md-input id="pais" v-model="dados.enderecosCobranca.pais"></md-input>
                    <span class="md-error erros">O pais necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>store_mall_directory</md-icon>
                    <label>Bairro...</label>
                    <md-input id="bairro" v-model="dados.enderecosCobranca.bairro"></md-input>
                    <span class="md-error erros">O bairro necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>home</md-icon>
                    <label>Numero...</label>
                    <md-input type="number" min="1" id="numero" v-model="dados.enderecosCobranca.numero"></md-input>
                    <span class="md-error erros">O numero é obrigatório</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>directions</md-icon>
                    <label>Complemento...</label>
                    <md-input id="complemento" v-model="dados.enderecosCobranca.observacao"></md-input>
                    <span class="md-error erros">O complemento necessida ter no minimo 5 caracteres</span>
                </md-field> 
            </div>
        </div>
    </div>
</template>

<script>
import { eventBus } from '../../../main';

export default {
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosEnderecoCobranca', function(e){
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

            if(this.dados.enderecosCobranca.tipoResidencia == ""){
                this.corErroSelect("tpEndereco");
                erro = true;
            }

            if(!regCep.test(this.dados.enderecosCobranca.cep)){
                this.corErroInput("cep");
                erro = true;
            }

            if(this.dados.enderecosCobranca.tipoLogradouro == ""){
                this.corErroSelect("tpLogradouro");
                erro = true;
            }

            if(this.dados.enderecosCobranca.logradouro.trim().length <= 3){
                this.corErroInput("logradouro");
                erro = true;
            }

            if(this.dados.enderecosCobranca.cidade.trim().length <= 3){
                this.corErroInput("cidade");
                erro = true;
            }

            if(this.dados.enderecosCobranca.estado.trim().length < 2){
                this.corErroInput("estado");
                erro = true;
            }

            if(this.dados.enderecosCobranca.pais.trim().length <= 3){
                this.corErroInput("pais");
                erro = true;
            }

            if(this.dados.enderecosCobranca.bairro.trim().length <= 3){
                this.corErroInput("bairro");
                erro = true;
            }

            if(this.dados.enderecosCobranca.numero <= 0){
                this.corErroInput("numero");
                erro = true;
            }

            if(this.dados.enderecosCobranca.observacao.trim().length > 0
                && this.dados.enderecosCobranca.observacao.trim().length < 5){
                this.corErroInput("complemento");
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
                            self.dados.enderecosCobranca.logradouro = dadosEndereco.logradouro;
                            //Atualiza os campos com os valores da consulta.
                            self.dados.enderecosCobranca.bairro = dadosEndereco.bairro
                            self.dados.enderecosCobranca.cidade = dadosEndereco.localidade
                            self.dados.enderecosCobranca.estado = dadosEndereco.uf
                            self.dados.enderecosCobranca.pais = "Brasil"
                        } //end if.
                       
                    });


                } //end if.
            } //end if.
        }   
        
    }
        
}
</script>


