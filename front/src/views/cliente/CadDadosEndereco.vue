<template>
    <div>
        <h3 class="text-center m-0 p-0">Endereço de cobrança</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>business</md-icon>
                    <label>Tipo de endereco</label>
                    <md-select id="tpEndereco" v-model="tipoResidencia" class="select-option">
                        <md-option v-for="tipo in tiposResidencia" class="select"  v-bind:value="tipo">{{tipo | firstUpperCase() }}</md-option>
                    </md-select>
                    <span class="md-error erros">O tipo de endereço é obrigatório</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>place</md-icon>
                    <label>CEP...</label>
                    <md-input v-mask="'#####-###'" v-on:keyup="buscarCep(this)" id="cep" v-model="cep"></md-input>
                    <span class="md-error erros">O cep tem que ter 8 digitos</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>explore</md-icon>
                    <label>Tipo Logradouro</label>
                    <md-select id="tpLogradouro" v-model="tipoLogradouro" class="select-option">
                        <md-option v-for="tipo in tiposLogradouro" class="select"  v-bind:value="tipo">{{tipo | firstUpperCase() }}</md-option>
                    </md-select>
                    <span class="md-error erros">O tipo de logradouro é obrigatório</span>
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
                    <md-icon>public</md-icon>
                    <label>Pais...</label>
                     <md-select id="pais" v-model="dados.enderecosCobranca.pais" class="select-option">
                        <md-option v-for="pais in paises" class="select"  v-bind:value="pais.pais">{{pais.pais}}</md-option>
                    </md-select>
                    <span class="md-error erros">O pais é obrigatório</span>
                </md-field>
            </div>
             <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>map</md-icon>
                    <label>Estado...</label>
                    <md-select id="estado" v-model="dados.enderecosCobranca.estado" class="select-option">
                        <md-option v-if="null != indexPais" v-for="estado in estados" class="select"  v-bind:value="estado.sigla">{{ estado.estado }}</md-option>
                    </md-select>
                    <span class="md-error erros">O estado é obrigatório</span>
                </md-field>
            </div>


            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_city</md-icon>
                    <label>Cidade...</label>
                    <md-select id="cidade" v-model="dados.enderecosCobranca.cidade" class="select-option">
                        <md-option v-if="null != indexEstado" class="select" v-for="(cidade, index) in cidades" v-bind:value="cidade.cidade">{{ cidade.cidade }}</md-option>
                    </md-select>
                    <span class="md-error erros">A cidade é obrigatório</span>
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
                    <md-input type="number" min="1" id="numero" v-model="numero"></md-input>
                    <span class="md-error erros">O numero é obrigatório</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>directions</md-icon>
                    <label>Complemento...</label>
                    <md-input id="complemento" v-model="observacao"></md-input>
                    <span class="md-error erros">O complemento necessida ter no minimo 5 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-icon class="icon-endereco">favorite_border</md-icon>
                <md-checkbox v-model="favorito">Endereço favorito?</md-checkbox>
            </div>
        </div>
    </div>
</template>

<script>
import { eventBus } from '../../main';
import axios from 'axios';

export default {
    watch: {
        'dados.enderecosCobranca.pais' : function(e){
            this.indexPais = this.mapPais[e];
            this.estados = this.paises[this.indexPais].estados;
        },
        'dados.enderecosCobranca.estado' : function(e){
            this.indexEstado = this.mapEstados[e];
            this.cidades = this.estados[this.indexEstado].cidades;
        }
    },
     data: () => ({
        paises : [],
        estados : [],
        cidades : [],
        mapPais: [],
        mapEstados: [],
        indexPais : null,
        indexEstado : null,
        favorito : null
     }),
     filters: {
        firstUpperCase(str){
            return str.toLowerCase().replace(/(?:^)\S/g, function(a) { return a.toUpperCase(); });
        }
    },
    computed: {
        preencherDados(){
            this.tiposLogradouro = this.$store.state.tipoLogradouros.tipos;
            this.tiposResidencia = this.$store.state.tipoResidencia.tipos 

            this.paises = this.$store.state.paises.paises;
            for(let i = 0; i < this.paises.length; i++){
                this.mapPais[this.paises[i].pais] = i;
                for(let j = 0; j < this.paises[i].estados.length; j++){
                    this.mapEstados[this.paises[i].estados[j].sigla] = j;
                }
            }

            if(this.dados.enderecosCobranca.pais != ""){
                this.indexPais = this.mapPais[this.dados.enderecosCobranca.pais];
                this.estados = this.paises[this.indexPais].estados;
                this.indexEstado = this.mapEstados[this.dados.enderecosCobranca.estado];
                this.cidades = this.estados[this.indexEstado].cidades;
            }
        }
    },
    created(){
        this.preencherDados;
        var dadosAtuais = this;
        eventBus.$on('validarDadosEnderecoCobranca', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        });
        
        if(this.dados != null){
            this.tipoResidencia = this.dados.enderecosCobranca.tipoResidencia;
            this.tipoLogradouro = this.dados.enderecosCobranca.tipoLogradouro;
            this.numero = this.dados.enderecosCobranca.numero;
            this.cep = this.dados.enderecosCobranca.cep;
            this.observacao = this.dados.enderecosCobranca.observacao;
            this.nomeComposto = this.dados.enderecosCobranca.nomeComposto;
            this.favorito = this.dados.enderecosCobranca.favorito;
            this.cep = this.dados.enderecosCobranca.cep;
            this.favorito = this.dados.enderecosCobranca.favorito;
        }

        
    },
    props:['dados'],
    methods:{
        validar(){
            var erro = false;

            var regCep = /\d{5}-?\d{3}/;

            if(this.tipoResidencia == ""){
                this.corErroSelect("tpEndereco");
                erro = true;
            }

            if(!regCep.test(this.cep)){
                this.corErroInput("cep");
                erro = true;
            } else {
                this.cep = this.cep.replace("-","")
            }

            if(this.tipoLogradouro == ""){
                this.corErroSelect("tpLogradouro");
                erro = true;
            }

            if(this.dados.enderecosCobranca.logradouro.trim().length < 3){
                this.corErroInput("logradouro");
                erro = true;
            }

            if(this.dados.enderecosCobranca.cidade == ""){
                this.corErroSelect("cidade");
                erro = true;
            }

            if(this.dados.enderecosCobranca.estado == ""){
                this.corErroSelect("estado");
                erro = true;
            }

            if(this.dados.enderecosCobranca.pais == ""){
                this.corErroSelect("pais");
                erro = true;
            }

            if(this.dados.enderecosCobranca.bairro.trim().length < 3){
                this.corErroInput("bairro");
                erro = true;
            }

            if(this.numero <= 0){
                this.corErroInput("numero");
                erro = true;
            }

            if(this.observacao.trim().length > 0
                && this.observacao.trim().length < 5){
                this.corErroInput("complemento");
                erro = true;
            }

            if(erro == false){
                this.dados.enderecosCobranca.tipoResidencia = this.tipoResidencia;
                this.dados.enderecosCobranca.tipoLogradouro = this.tipoLogradouro;
                this.dados.enderecosCobranca.numero = this.numero;
                this.dados.enderecosCobranca.cep = this.cep;
                this.dados.enderecosCobranca.observacao = this.observacao;
                this.dados.enderecosCobranca.nomeComposto = this.nomeComposto;
                this.dados.enderecosCobranca.favorito = this.favorito;
                this.dados.enderecosCobranca.cep = this.cep;
                eventBus.$emit('page', 3);
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
        },
        buscarCep: function(event) {
            // Nova variável "cep" somente com dígitos.
            
            let cep = $("#cep").val().replace(/\D/g, '');
            var self = this;

            //Verifica se campo cep possui valor informado.
            if (cep != "") {
                //Expressão regular para validar o CEP.
                let validacep = /^[0-9]{8}$/;

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

            console.log(this)
        }   
        
    }
        
}
</script>


