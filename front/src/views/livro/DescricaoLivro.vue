<template>
  <div class="wrapper bg-dark-blue">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 md-xsmall-size-100">
              <carousel class="md-layout-item md-large-size-67 md-small-size-100"
              :per-page="1"
              loop
              v-model="carousel"
              :mouse-drag="true"
              navigationEnabled
              navigationNextLabel="<i class='material-icons'>keyboard_arrow_right</i>"
              navigationPrevLabel="<i class='material-icons'>keyboard_arrow_left</i>">
              <slide>
                
                <img src="https://image.freepik.com/vetores-gratis/template-mockup-livro-realista_1017-6282.jpg" class="img-carousel" alt="carousel1">
              </slide>
              <slide>
                
                <img src="https://image.freepik.com/vetores-gratis/modelo-de-vetor-de-capa-de-livro-branco-vertical-em-branco_53562-2189.jpg" class="img-carousel" alt="carousel2">
              </slide>
            </carousel>
            </div>

            <div class="md-layout-item md-size-50 md-xsmall-size-100">
                <h2 class="text-center">Titulo livro</h2>
                <h5>Subtitulo livro</h5>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nulla metus, congue ac velit id, bibendum lobortis arcu. Proin id libero non mi fermentum volutpat nec ut nibh. Mauris consectetur ex a vehicula lobortis. Praesent vehicula urna scelerisque tortor interdum, gravida euismod justo varius. Pellentesque maximus nisi eget sapien ullamcorper vulputate. Sed vulputate vitae sem vitae ornare. In consectetur facilisis elit, et euismod tortor aliquet non. Curabitur rhoncus consequat ex, at aliquet erat maximus quis. Mauris eget sagittis tortor, accumsan pharetra est. Etiam ipsum neque, eleifend ut sapien ac, congue vehicula elit. Phasellus eu nunc sodales, tincidunt est vitae, fringilla mi. Etiam nibh quam, efficitur non arcu id, mattis accumsan felis. Mauris posuere sapien ut ipsum molestie, at pharetra lorem gravida. Duis in tortor vitae justo mollis semper quis id lacus.</p>
                <p>Suspendisse consectetur dignissim arcu. Nunc pulvinar in nulla eu aliquam. Duis malesuada varius tortor fringilla luctus. Cras ultricies neque odio, ut hendrerit est efficitur vitae. Nullam eget semper est.</p>
                <div class="md-layout-item text-right">
                    <md-button class="md-primary md-lg">Comprar</md-button>
                </div>
            </div>
          </div>

            <md-table md-card>
                <md-table-row>
                    <md-table-head colspan="2">Ficha Técnica</md-table-head>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>ISBN</md-table-cell>
                    <md-table-cell>9788592783648</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Autor</md-table-cell>
                    <md-table-cell>Fulano</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Quantidade de páginas</md-table-cell>
                    <md-table-cell>424</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Tradutor</md-table-cell>
                    <md-table-cell>Fulana</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Comprimento</md-table-cell>
                    <md-table-cell>16 cm</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Altura</md-table-cell>
                    <md-table-cell>23 cm</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Profundidade</md-table-cell>
                    <md-table-cell>2,5 cm</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Peso</md-table-cell>
                    <md-table-cell>0.50 kg</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Acabamento</md-table-cell>
                    <md-table-cell>Brochura</md-table-cell>
                </md-table-row>

                <md-table-row>
                    <md-table-cell>Edição</md-table-cell>
                    <md-table-cell>1ª/2018</md-table-cell>
                </md-table-row>
            </md-table>


        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Tabs } from "@/mk/components";
export default {
  components: {
    Tabs
  },
  bodyClass: "profile-page",
  data() {
    return {
      tabPane1: [
        { image: require("@/assets/img/examples/studio-1.jpg") },
        { image: require("@/assets/img/examples/studio-2.jpg") },
        { image: require("@/assets/img/examples/studio-4.jpg") },
        { image: require("@/assets/img/examples/studio-5.jpg") }
      ],
      tabPane2: [
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/cynthia-del-rio.jpg") },
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/clem-onojegaw.jpg") }
      ],
      tabPane3: [
        { image: require("@/assets/img/examples/mariya-georgieva.jpg") },
        { image: require("@/assets/img/examples/studio-3.jpg") },
        { image: require("@/assets/img/examples/clem-onojeghuo.jpg") },
        { image: require("@/assets/img/examples/olu-eletu.jpg") },
        { image: require("@/assets/img/examples/studio-1.jpg") }
      ]
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg-dark4.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs /deep/ {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*="tab-pane-"] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}

.mt--50{
  margin-top: -50px !important;
}   

.mb-25{
  margin-bottom: 25px;
}

.bg-dark-blue{
  background-color: #000405 !important;
}
</style>
