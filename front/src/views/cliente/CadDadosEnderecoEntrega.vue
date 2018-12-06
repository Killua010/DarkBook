<template>
    <div>
        <h3 class="text-center m-0 p-0">Endereço de entrega</h3>
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
                    <md-input v-mask="'#####-###'" v-on:keyup="buscarCep" id="cep" v-model="cep"></md-input>
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
                    <md-input id="logradouro" v-model="dados.enderecosEntrega.logradouro"></md-input>
                    <span class="md-error erros">O logradouro necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>public</md-icon>
                    <label>Pais...</label>
                    <md-select id="pais" v-model="dados.enderecosEntrega.pais" class="select-option">
                        <md-option v-for="pais in paises" class="select"  v-bind:value="pais.pais">{{pais.pais}}</md-option>
                    </md-select>
                    <span class="md-error erros">O pais é obrigatório</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>map</md-icon>
                    <label>Estado...</label>
                    <md-select id="estado" v-model="dados.enderecosEntrega.estado" class="select-option">
                        <md-option v-if="null != indexPais" v-for="estado in estados" class="select"  v-bind:value="estado.sigla">{{ estado.estado }}</md-option>
                    </md-select>
                    <span class="md-error erros">O estado é obrigatório</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>location_city</md-icon>
                    <label>Cidade...</label>
                    <md-select id="cidade" v-model="dados.enderecosEntrega.cidade" class="select-option">
                        <md-option v-if="null != indexEstado" class="select" v-for="cidade in cidades" v-bind:value="cidade.cidade">{{ cidade.cidade }}</md-option>
                    </md-select>
                    <span class="md-error erros">A cidade é obrigatório</span>
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
                <md-field class="md-form-group">
                    <md-icon>account_box</md-icon>
                    <label>Nome Composto...</label>
                    <md-input id="nomeComposto" v-model="nomeComposto"></md-input>
                    <span class="md-error erros">O nome necessida ter no minimo 3 caracteres</span>
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
    data: () => ({
        paises : [],
        estados : [],
        cidades : [],
        mapPais: [],
        mapEstados: [],
        indexPais : null,
        indexEstado : null,
        tiposLogradouro:[],
        tiposResidencia:[]
    }),
    watch: {
        'dados.enderecosEntrega.pais' : function(e){
            this.indexPais = this.mapPais[e];
            this.estados = this.paises[this.indexPais].estados;
        },
        'dados.enderecosEntrega.estado' : function(e){
            this.indexEstado = this.mapEstados[e];
            this.cidades = this.estados[this.indexEstado].cidades;
        }
    },
    filters: {
        firstUpperCase(str){
            return str.toLowerCase().replace(/(?:^)\S/g, function(a) { return a.toUpperCase(); });
        }
    },
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosEndereco', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        })
        
        this.buscarTipoLogradouro();
        this.buscarTipoResidencia();
        this.buscarPaises();

        if(this.dados != null){
            this.tipoResidencia = this.dados.enderecosEntrega.tipoResidencia;
            this.tipoLogradouro = this.dados.enderecosEntrega.tipoLogradouro;
            this.numero = this.dados.enderecosEntrega.numero;
            this.cep = this.dados.enderecosEntrega.cep;
            this.observacao = this.dados.enderecosEntrega.observacao;
            this.nomeComposto = this.dados.enderecosEntrega.nomeComposto;
            this.favorito = this.dados.enderecosEntrega.favorito;
        }
    },
    props:['dados'],
    methods:{
        buscarTipoLogradouro(){
            var dadosAtuais = this;
            $.ajax({
                type: "POST",
                url: "http://localhost:8082/DarkBook/tipoLogradouro?operacao=CONSULTAR",
                async: false
            }).done(function(msg){
                dadosAtuais.tiposLogradouro = msg
             }).fail(function(jqXHR, textStatus, msg){
                  console.log(msg);
             })
        },
        buscarTipoResidencia(){
            var dadosAtuais = this;
            $.ajax({
                type: "POST",
                url: "http://localhost:8082/DarkBook/tipoResidencia?operacao=CONSULTAR",
                async: false
            }).done(function(msg){
                dadosAtuais.tiposResidencia = msg
             }).fail(function(jqXHR, textStatus, msg){
                  console.log(msg);
             })
        },
        buscarPaises(){
            var dadosAtuais = this;
            axios.post(`http://localhost:8082/DarkBook/paises?operacao=CONSULTAR`, 
            this.cliente, {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
                }
            }).then(function(e){
                dadosAtuais.paises = e.data
                for(let i = 0; i < dadosAtuais.paises.length; i++){
                    dadosAtuais.mapPais[dadosAtuais.paises[i].pais] = i;
                    for(let j = 0; j < dadosAtuais.paises[i].estados.length; j++){
                        dadosAtuais.mapEstados[dadosAtuais.paises[i].estados[j].sigla] = j;
                    }
                }

                if(dadosAtuais.dados.enderecosEntrega.pais != ""){
                    dadosAtuais.indexPais = dadosAtuais.mapPais[dadosAtuais.dados.enderecosEntrega.pais];
                    dadosAtuais.estados = dadosAtuais.paises[dadosAtuais.indexPais].estados;
                    dadosAtuais.indexEstado = dadosAtuais.mapEstados[dadosAtuais.dados.enderecosEntrega.estado];
                    dadosAtuais.cidades = dadosAtuais.estados[dadosAtuais.indexEstado].cidades;
                }

            }).catch(function(e){
                console.log(e.response.data)
            })
        },
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

            if(this.dados.enderecosEntrega.logradouro.trim().length < 3){
                this.corErroInput("logradouro");
                erro = true;
            }
<<<<<<< HEAD
           
=======
            console.log(this.dados.enderecosEntrega.cidade  == "")
>>>>>>> f647c41b7d3b912f5018d4e8d27686af9ec981ed
            if(this.dados.enderecosEntrega.cidade == ""){
                this.corErroSelect("cidade");
                erro = true;
            }

            if(this.dados.enderecosEntrega.estado == ""){
                this.corErroSelect("estado");
                erro = true;
            }

            if(this.dados.enderecosEntrega.pais == ""){
                this.corErroSelect("pais");
                erro = true;
            }

            if(this.dados.enderecosEntrega.bairro.trim().length < 3){
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

            if(this.nomeComposto.trim().length < 3){
                this.corErroInput("nomeComposto");
                erro = true;
            }

            if(erro == false){
                this.dados.enderecosEntrega.tipoResidencia = this.tipoResidencia;
                this.dados.enderecosEntrega.tipoLogradouro = this.tipoLogradouro;
                this.dados.enderecosEntrega.numero = this.numero;
                this.dados.enderecosEntrega.cep = this.cep;
                this.dados.enderecosEntrega.observacao = this.observacao;
                this.dados.enderecosEntrega.nomeComposto = this.nomeComposto;
                this.dados.enderecosEntrega.favorito = this.favorito;
                eventBus.$emit('page', 2);
<<<<<<< HEAD
                eventBus.$emit('dadosValidoEnderecoEntrega', true);
=======
>>>>>>> f647c41b7d3b912f5018d4e8d27686af9ec981ed
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

