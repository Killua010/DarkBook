<template>
  <div id="material-kit">
    <div :class="{'nav-open': NavbarStore.showNavbar}">
      <router-view name="header"/>
      <div>
        <router-view/>
      </div>
      <router-view name="footer"/>
    </div>
  </div>
</template>

<script>
export default {
  mounted(){
    this.buscarDadosSelect()
  },
  methods: {
    mensagemErro(msg){
      swal({
        title: "Erro ao iniciar a aplicação",
        text: msg.toString(),
        icon: "error"
      }).then((result) => {
        window.location.reload()
      })
    },

    buscarDadosSelect(){
      let dadosAtuais = this;
      // tipo Logradouro
      $.ajax({
          type: "POST",
          url: "http://localhost:8082/DarkBook/tipoLogradouro?operacao=CONSULTAR",
          async: false
      }).done(function(msg){
        dadosAtuais.$store.commit('INSERIR_TIPOLOGRADOURO', msg)
      }).fail(function(jqXHR, textStatus, msg){
        dadosAtuais.mensagemErro(msg)
      });

      // tipo Residencia
      $.ajax({
        type: "POST",
        url: "http://localhost:8082/DarkBook/tipoResidencia?operacao=CONSULTAR",
        async: false
      }).done(function(msg){
        dadosAtuais.$store.commit('INSERIR_TIPORESIDENCIA', msg)
      }).fail(function(jqXHR, textStatus, msg){
          dadosAtuais.mensagemErro(msg)
      })

      // Paises
      $.ajax({
        type: "POST",
        url: "http://localhost:8082/DarkBook/paises?operacao=CONSULTAR",
        async: false
        }).done(function(msg){
          dadosAtuais.$store.commit('INSERIR_PAISES', msg)
        }).fail(function(jqXHR, textStatus, msg){
          dadosAtuais.mensagemErro(msg)
        })
      
      // Bandeiras Cartao
      $.ajax({
        type: "POST",
        url: "http://localhost:8082/DarkBook/bandeira?operacao=CONSULTAR",
        async: false
      }).done(function(msg){
        dadosAtuais.$store.commit('INSERIR_BANDEIRAS', msg)
      }).fail(function(jqXHR, textStatus, msg){
        dadosAtuais.mensagemErro(msg)
      })

    }
  }
}
</script>

