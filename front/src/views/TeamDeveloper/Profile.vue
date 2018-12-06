<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle"></parallax>
    <div class="main main-raised">
      <div class="section profile-content mb-50">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <img :src="img" alt="Circle Image" class="img-raised rounded-circle img-fluid">
                </div>
                <div class="name">
                  <h3 class="title">Daniel Dias de Souza</h3>
                  <h6>Web Desingner</h6>
                  <md-button href="https://github.com/Killua010/ES3" target="_blank" class="md-just-icon md-simple md-github"><i class="fab fa-github"></i></md-button>
                  <md-button href="https://www.linkedin.com/in/daniel-dias-112a07127/" target="_blank" class="md-just-icon md-simple md-linkedin"><i class="fab fa-linkedin"></i></md-button>
                  <md-button href="https://twitter.com/Daniel160598?lang=pt-br" target="_blank" class="md-just-icon md-simple md-twitter"><i class="fab fa-twitter"></i></md-button>
                  <md-button href="https://www.facebook.com/daniel.dias.5249349" target="_blank" class="md-just-icon md-simple md-facebook"><i class="fab fa-facebook"></i></md-button>
                </div>
              </div>
            </div>
          </div>
          <div class="description text-center">
            <p>
              <ul>
                <li>Estudante do curso de Análise e Desenvolvimento de Sistemas</li>
                <li>Grande interesse em novas linguagens e novas metodologias</li>
                <li>Amante do desenvolvimento web com foco no front-end</li>
                <li>Conhecimento das linguagens: JavaScript, PHP, Java e C+</li>
              </ul>
            </p>
          </div>
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
      default: require("@/assets/img/bg-dark8.jpg")
    },
    img: {
      type: String,
      default: require("@/assets/img/faces/itsme2.jpg")
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

.mb-50{
  padding-bottom: 25px;
  margin-bottom: 50px;
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
</style>
