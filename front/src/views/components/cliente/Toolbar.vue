<template>
  <div class="hello">
    <nav class="navbar navbar-transparent bg-dark navbar-color-on-scroll fixed-top navbar-expand-lg" color-on-scroll="100" id="sectionsNav">
      <div class="container">
        <div class="navbar-translate">
          <!--img src="../assets/logo2.png" class="img-nav"-->
          <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="sr-only">Sanduiche de navegação</span>
            <span class="navbar-toggler-icon"></span>
            <span class="navbar-toggler-icon"></span>
            <span class="navbar-toggler-icon"></span>
          </button>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav ml-auto">
            <li>
              <form class="form-inline search-form">
                <div class="form-group has-white bmd-form-group">
                  <input type="text" class="form-control input-white search" placeholder="Procurando algum livro?">
                </div>
                <button type="submit" class="btn btn-white btn-raised btn-fab btn-round">
                    <i class="material-icons">search</i>
                </button>
              </form>
            </li>
            <li class="dropdown nav-item">
              <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                <i class="material-icons">face</i> Embarque Conosco
              </a>
              <div class="dropdown-menu dropdown-with-icons">
                <a href="#" class="dropdown-item">
                  <i class="material-icons">person_add</i> Novo Usuário
                </a>
                <a href="#" class="dropdown-item">
                  <i class="material-icons">how_to_reg</i> Entrar
                </a>
              </div>
            </li>
            <li class="nav-item">
              <v-badge left>
                <span slot="badge" class="badge badge-pill badge-warning shop">6</span>
                <a href="#" class="icon-shop">
                  <v-icon>
                    shopping_cart
                  </v-icon>
                </a>
              </v-badge>

            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .img-nav{
    max-height: 50px;
  }

  .input-white{
    color: #000 !important;
  }

  .icon-shop{
    color: #000;
  }

  .shop{
    font-size: 0.8em;
    padding: 0.4em 0.5em 0.25em 0.48em;
  }

  .input-white::-webkit-input-placeholder {
   color: #000;
  }
  .input-white:-moz-placeholder { /* Firefox 18- */
    color: #000;  
  }
  .input-white::-moz-placeholder {  /* Firefox 19+ */
    color: #000;  
  }
  .input-white:-ms-input-placeholder {  
    color: #000;  
  }

  .search{
    width: auto;
  }

  @media (min-width:768px){
    .search{
      width: 300px;
    }
    .search-form{
      margin-right: 150px;
    }
    .input-white{
      color: #fff !important;
    }
    .input-white::-webkit-input-placeholder {
      color: #fff;
     }
    .input-white:-moz-placeholder { /* Firefox 18- */
      color: #fff;  
    }
    .input-white::-moz-placeholder {  /* Firefox 19+ */
      color: #fff;  
    }
    .input-white:-ms-input-placeholder {  
      color: #fff;  
    }
    .icon-shop{
      color: #fff;
    }
  }
</style>
