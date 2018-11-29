<template>
  <div class="wrapper bg-dark-blue">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>The Dark Book</h1>
              <h3>A sua melhor escolha em livros</h3>
            </div>
          </div>

        </div>
      </div>
    </parallax>
    
    <div class="main main-raised">
      <div class="section section-basic">
        <div class="container">      
          <div class="md-layout">
            <div class="md-layout-item md-large-size-33 md-small-size-100">
              <md-card>
                <md-card-header data-background-color="purple">
                  <h3 v-if="carousel == 0" class="title text-center px-5">Origem</h3>
                  <h3 v-if="carousel == 1" class="title text-center px-5">O reino das vozes que não se calam</h3>
                  <h3 v-if="carousel == 2" class="title text-center px-5">Nevernight</h3>
                </md-card-header>
                <md-card-content>
                  <div v-if="carousel == 0">
                    <p>
                      Robert Langdon, o famoso professor de Simbologia de Harvard, chega ao ultramoderno Museu Guggenheim de Bilbao para assistir a uma apresentação sobre uma grande descoberta que promete “mudar para sempre o papel da ciência”.
                    </p>
                  </div>
                  <div v-if="carousel == 1">
                    <p>
                      O livro conta a história de Sophie, uma garota cansada de sofrer com a indiferença das pessoas até descobrir o Reino, um mundo mágico onde seus talentos são reconhecidos. Cedo ou tarde, porém, ela terá que decidir entre a realidade e a fantasia, numa jornada repleta de surpresas e descobertas.
                    </p>
                  </div>
                  <div v-if="carousel == 2">
                    <p>
                      Mia Corvere viu seu pai ser morto em praça pública por ter traído a República de Itreya. Sua mãe e o irmão ainda bebê acabam sendo presos e, mesmo que seu destino também seja a morte, ela consegue escapar. Rondando as ruas de Godsgrave ela cruza caminho com o Velho Mercúrio e sua sede de vingança, mais o conhecimento que ele tem, selam seu caminho.
                    </p>
                  </div>
                </md-card-content>
              </md-card>
            </div>    
            <carousel class="md-layout-item md-large-size-67 md-small-size-100"
              :per-page="1"
              loop
              :speed="700"
              autoplay
              v-model="carousel"
              :autoplay-timeout="5000"
              :mouse-drag="true"
              navigationEnabled
              navigationNextLabel="<i class='material-icons'>keyboard_arrow_right</i>"
              navigationPrevLabel="<i class='material-icons'>keyboard_arrow_left</i>">
              <slide id="eae">
                <a href="#/descricaoLivro">
                  <img src="../assets/img/livros/livro1.jpg" class="img-carousel" alt="carousel1">
                </a>
              </slide>
              <slide>
                <a href="#/descricaoLivro">
                  <img src="../assets/img/livros/livro2.jpg" class="img-carousel" alt="carousel2">
                </a>
              </slide>
              <slide>
                <a href="#/descricaoLivro">
                  <img src="../assets/img/livros/livro3.jpg" class="img-carousel" alt="carousel3">
                </a>
              </slide>
            </carousel> 
          </div>
        
          <div class="md-layout-item text-center">
            <h2 class="title text-center">Lançamentos</h2>
          </div>

          <div class="md-layout">
            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">Origem</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=9731880&qld=90&l=430&a=-1=1005874488" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">O Homem De Giz</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=10109811&qld=90&l=430&a=-1=1005166356" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">O Labirinto De Fogo</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=10133789&qld=90&l=430&a=-1=1005337457" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">Princesa Das Cinzas</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=10337422&qld=90&l=430&a=-1=1005879211" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">Estou Atrás De Você</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=9864058&qld=90&l=430&a=-1=1004887141" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>
          </div>


          <div class="md-layout-item text-center">
            <h2 class="title text-center">Mais Vendidos</h2>
          </div>

          <div class="md-layout">
            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">A Origem Das Espécies</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=7246326&qld=90&l=430&a=-1=1001435829" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">O Orfanato da Srta...</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=9022040&qld=90&l=430&a=-1=1002908450" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">A Revolução Dos Bichos</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=1574783&qld=90&l=430&a=-1=1005808113" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">Princesa Das Cinzas</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=10337422&qld=90&l=430&a=-1=1005879211" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>

            <div class="md-layout-item md-size-20 md-xsmall-size-100">
              <h4 class="text-center">Laranja Mecânica</h4>
              <a href="#/descricaoLivro">
                <img src="https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=158643&qld=90&l=430&a=-1=1005889316" alt="Rounded Image" class="rounded img-livros">
              </a>
            </div>
          </div>

          <div class="md-layout-item text-center">
            <md-button class="md-primary" to="/catalogo">veja o catálogo completo</md-button>
          </div>

        </div>
      </div>
    </div>
  </div>


</template>

<script>
import BasicElements from "@/mk/views/components/BasicElementsSection";
import Navigation from "@/mk/views/components/NavigationSection";
import SmallNavigation from "@/mk/views/components/SmallNavigationSection";
import Tabs from "@/mk/views/components/TabsSection";
import NavPills from "@/mk/views/components/NavPillsSection";
import Notifications from "@/mk/views/components/NotificationsSection";
import TypographyImages from "@/mk/views/components/TypographyImagesSection";
import JavascriptComponents from "@/mk/views/components/JavascriptComponentsSection";
import { LoginCard } from "@/mk/components";

export default {
  components: {
    BasicElements,
    Navigation,
    SmallNavigation,
    Tabs,
    NavPills,
    Notifications,
    TypographyImages,
    JavascriptComponents,
    LoginCard
  },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/bg-dark1.jpg")
    },
    signup: {
      type: String,
      default: require("@/assets/img/city.jpg")
    },
    landing: {
      type: String,
      default: require("@/assets/img/landing.jpg")
    },
    profile: {
      type: String,
      default: require("@/assets/img/profile.jpg")
    }
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false,
      carousel: 0
    };
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`
      };
    }
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  }
};
</script>
<style lang="scss">
.bg-dark-blue{
  background-color: #000405 !important;
}


.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}

.img-livros{
  max-height: 284px;
}

.px-5{
  padding-left: 5px !important;
  padding-right: 5px !important;
}
</style>
