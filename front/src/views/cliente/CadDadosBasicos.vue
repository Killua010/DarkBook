<template>
    <div>
        <h3 class="text-center m-0 p-0">Dados Pessoais</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>face</md-icon>
                    <label>Primeiro Nome...</label>
                    <md-input id="primeiroNome" v-model="nome"></md-input>
                    <span class="md-error erros">O nome necessida ter no minimo 3 caracteres</span>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>face</md-icon>
                    <label>Sobrenome...</label>
                    <md-input id="sobrenome" v-model="sobrenome"></md-input>
                    <span class="md-error erros">O sobrenome necessida ter no minimo 3 caracteres</span>
                </md-field> 
            </div>  

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>mail</md-icon>
                    <label>Email...</label>
                    <md-input id="email" type="email" v-model="email"></md-input>
                    <span class="md-error erros">O email tem que estar no formato nome@dominio.com</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>wc</md-icon>
                    <label>Gênero</label>
                    <md-select id="genero" v-model="genero" class="select-option">
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
                    <md-input masked="true" v-mask="'###.###.###-##'" id="cpf" v-model="cpf"></md-input>
                    <span class="md-error erros">O cpf é invalido</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>event</md-icon>
                    <label>Data de nascimento...</label>
                    <md-input v-mask="'##/##/####'" id="dtNascimento" v-model="dataNascimento"></md-input>
                    <span class="md-error erros">A data tem que estar no formato DD/MM/AAAA</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>contact_phone</md-icon>
                    <label>Tipo Telefone</label>
                    <md-select id="tpTelefone" v-model="tipoTelefone" class="select-option">
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
                    <md-input v-mask="['(##) ####-####', '(##) #####-####']" id="telefone" v-model="telefone"></md-input>
                    <span class="md-error erros">O telefone informado é invalido</span>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>fingerprint</md-icon>
                    <label>Senha...</label>
                    <md-input id="senha1" v-model="senha1" type="password"></md-input>
                    <span class="md-error erros">A senha tem que ter mais que 6 caracteres</span>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>fingerprint</md-icon>
                    <label>Digite novamente sua senha...</label>
                    <md-input id="senha2" v-model="senha2" type="password"></md-input>
                    <span class="md-error erros">As senhas precisam ser iguais</span>
                </md-field> 
            </div>
        </div>
    </div>
</template>

<script>
    // import axios from 'axios';
import { eventBus } from '../../main';

  export default {
      data: () => ({
        nome : "",
        sobrenome : "",
        email : "",
        genero : "",
        cpf : "",
        dataNascimento : "",
        tipoTelefone : "",
        telefone : "",
        ddd : "",
        senha1 : "",
        senha2 : "",
        senha : ""
      }),
    created(){
        var dadosAtuais = this;
        eventBus.$on('validarDadosBasicos', function(e){
            if(e === true){
                dadosAtuais.validar()
            }
        })

        if(this.dados != null){
            this.nome = this.dados.dadosPessoais.nome
            this.sobrenome = this.dados.dadosPessoais.sobrenome
            this.email = this.dados.dadosPessoais.email
            this.genero = this.dados.dadosPessoais.genero
            this.cpf = this.dados.dadosPessoais.cpf
            this.dataNascimento = this.dados.dadosPessoais.dataNascimento
            this.tipoTelefone = this.dados.dadosPessoais.tipoTelefone
            this.telefone = `${this.dados.dadosPessoais.ddd}${this.dados.dadosPessoais.telefone}`
            this.senha1 = this.dados.dadosPessoais.senha,
            this.senha2 = this.dados.dadosPessoais.senha
        }

    },
    props:['dados'],
    methods:{
        VerificaData: function (cData) {
            var now = new Date	
            var data = cData; 	
            var tam = data.length
            	if (tam != 10) {  
                    return false
                }	
                var dia = data.substr(0,2)	
                var mes = data.substr (3,2)	
                var ano = data.substr (6,4)		
                if (ano < 1900)	{  
                    return false
                }    
                if (ano > now.getFullYear())	{  
                    return false
                }	
                switch (mes) {  
                    case '01': 	 if  (dia <= 31)     return (true)
                        break
                    case '02': 	 if  (dia <= 29)     return (true)
                        break
                    case '03': 	 if  (dia <= 31)     return (true)
                        break
                    case '04': 	 if  (dia <= 30)     return (true)
                        break
                    case '05': 	 if  (dia <= 31)     return (true)
                        break
                    case '06': 	 if  (dia <= 30)     return (true)
                        break
                    case '07': 	 if  (dia <= 31)     return (true)
                        break
                    case '08': 	 if  (dia <= 31)     return (true)
                        break
                    case '09': 	 if  (dia <= 30)     return (true)
                        break
                    case '10': 	 if  (dia <= 31)     return (true)
                        break
                    case '11': 	 if  (dia <= 30)     return (true)
                        break
                    case '12': 	 if  (dia <= 31)     return (true)
                        break
                    default: return false
                }	
                return true
            },
        validar(){
            var erro = false

            var regCPF = /\d{3}\.\d{3}\.\d{3}-\d{2}/;

            var regEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

            var regTelefone = /\(\d{2}\)\s?\d{4,5}-?\d{4}/;

            var regData = /[0-3][0-9]\/[0-1][0-9]\/[1-2]([0]|[9])([0-1]|[9])[0-9]/
            
            if(undefined === this.nome || this.nome.trim().length < 3){
                this.corErroInput("primeiroNome")
                erro = true;
            }

            if(this.sobrenome.trim().length < 3){
                this.corErroInput("sobrenome")
                erro = true;
            }
            
            if(!regEmail.test(this.email)){
                this.corErroInput("email")
                erro = true;
            }

            if(this.genero == ""){
                this.corErroSelect("genero")
                erro = true;
            }

            if(!regCPF.test(this.cpf) || !this.testaCPF(this.cpf)){
                this.corErroInput("cpf")
                erro = true;
            }   

            if(!regData.test(this.dataNascimento) || !this.VerificaData(this.dataNascimento)){
                this.corErroInput("dtNascimento")
                erro = true;
            }

            if(this.tipoTelefone == ""){
                this.corErroSelect("tpTelefone")
                erro = true;
            }
            
            if(!regTelefone.test(this.telefone)){
                this.corErroInput("telefone")
                erro = true;
            } 
            
            if(this.senha1.trim().length <= 6){
                this.corErroInput("senha1")
                erro = true;
            }

            if(this.senha2.trim().length < 5
                || this.senha2 != this.senha1){
                this.corErroInput("senha2")
                erro = true;
            }

            if(erro === false){
                this.dados.dadosPessoais.nome = this.nome
                this.dados.dadosPessoais.sobrenome = this.sobrenome
                this.dados.dadosPessoais.email = this.email
                this.dados.dadosPessoais.genero = this.genero
                this.dados.dadosPessoais.cpf = this.cpf = this.cpf.replace(/[^\d]+/g,'');
                this.dados.dadosPessoais.dataNascimento = this.dataNascimento
                this.dados.dadosPessoais.tipoTelefone = this.tipoTelefone
                this.dados.dadosPessoais.ddd = this.telefone.replace(/[^\d]+/g,'').substring(0, 2)
                this.dados.dadosPessoais.telefone = this.telefone.replace(/[^\d]+/g,'').substring(2)
                this.dados.dadosPessoais.senha = this.senha2;
                eventBus.$emit('page', 1);
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
        testaCPF: function (strCPF) {
            var Soma;
            var Resto;
            Soma = 0;

            strCPF = strCPF.replace(/[\.]|[\-]/g,"")
            if (strCPF == "00000000000" ||
            strCPF == "11111111111" ||
            strCPF == "22222222222" ||
            strCPF == "33333333333" ||
            strCPF == "44444444444" ||
            strCPF == "55555555555" ||
            strCPF == "66666666666" ||
            strCPF == "77777777777" ||
            strCPF == "88888888888" ||
            strCPF == "99999999999"){
                 return false;
            }
                
            for (let i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
            Resto = (Soma * 10) % 11;
            
                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(strCPF.substring(9, 10)) ) return false;
            
            Soma = 0;
                for (let i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
                Resto = (Soma * 10) % 11;
            
                if ((Resto == 10) || (Resto == 11))  Resto = 0;
                if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
                return true;
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

