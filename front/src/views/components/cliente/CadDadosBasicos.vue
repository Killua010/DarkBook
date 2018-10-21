<template>
    <div>
        <h3 class="text-center m-0 p-0">Dados Pessoais</h3>
        <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>face</md-icon>
                    <label>Primeiro Nome...</label>
                    <md-input v-model="dados.dadosPessoais.primeiroNome"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>face</md-icon>
                    <label>Sobrenome...</label>
                    <md-input v-model="dados.dadosPessoais.sobrenome"></md-input>
                </md-field> 
            </div>  

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>mail</md-icon>
                    <label>Email...</label>
                    <md-input v-model="dados.dadosPessoais.email"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>wc</md-icon>
                    <label>Genero</label>
                    <md-select v-model="dados.dadosPessoais.genero" class="select-option">
                        <md-option class="select" value="MASCULINO">Masculino</md-option>
                        <md-option class="select" value="FEMININO">Feminino</md-option>
                        <md-option class="select" value="NAOBINARIO">Não binário</md-option>
                    </md-select>
                </md-field>
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>picture_in_picture_alt</md-icon>
                    <label>CPF...</label>
                    <md-input v-model="dados.dadosPessoais.cpf"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>event</md-icon>
                    <label>Data de nascimento...</label>
                    <md-input v-model="dados.dadosPessoais.dataNascimento"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>contact_phone</md-icon>
                    <label>Tipo Telefone</label>
                    <md-select v-model="dados.dadosPessoais.tipoTelefone" class="select-option">
                        <md-option class="select" value="FIXO">Fixo</md-option>
                        <md-option class="select" value="MOVEL">Movel</md-option>
                    </md-select>
                </md-field>
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>local_phone</md-icon>
                    <label>Telefone...</label>
                    <md-input v-model="dados.dadosPessoais.telefone"></md-input>
                </md-field> 
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>fingerprint</md-icon>
                    <label>Senha...</label>
                    <md-input v-model="dados.dadosPessoais.senha1" type="password"></md-input>
                </md-field> 
            </div>
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <md-field class="md-form-group">
                    <md-icon>fingerprint</md-icon>
                    <label>Digite novamente sua senha...</label>
                    <md-input v-model="dados.dadosPessoais.senha2" type="password"></md-input>
                </md-field> 
            </div>
            <button @click="enviar()">teste</button>
            

        </div>
    </div>
</template>

<script>
    // import axios from 'axios';


  export default {
      props:['dados'],  
      data(){
          return{
              
          }
      },

      methods: {
          enviar: function(){
            //    try {
            //       axios.post(`http://localhost:8082/DarkBook/cliente`, 
            //       JSON.stringify (this.dadosPessoais))
            //     } catch (e) {
            //       console.log(e)
            //     }
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

