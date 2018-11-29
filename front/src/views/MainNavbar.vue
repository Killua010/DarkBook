<template>
  <md-toolbar id="toolbar" md-elevation="0" class="md-transparent md-absolute" :class="extraNavClasses" :color-on-scroll="colorOnScroll">
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <a href="#/">
          <img src="../assets/logo2.png" class="img-nav">
        </a>
      </div>
      <div class="md-toolbar-section-end">
        <md-button class="md-just-icon md-simple md-toolbar-toggle" :class="{toggled: toggledClass}" @click="toggleNavbarMobile()">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="true">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>


              <md-list-item href="#" to="/catalogo"> 
                <i class="material-icons">book</i> 
                    <p>Catálogo</p>
              </md-list-item>
              

                <md-list-item href="javascript:void(0)">
                  <a href="#" class="icon-shop">
                    <i class="material-icons">
                      shopping_cart
                    </i>
                  </a>
                  
                  <div>
                    <span slot="badge" class="badge badge-pill badge-warning shop">6</span>
                  </div>
                </md-list-item>

                <li class="md-list-item">
                  <a href="javascript:void(0)" class="md-list-item-router md-list-item-container md-button-clean dropdown">
                    <div class="md-list-item-content">
                      <drop-down direction="down">
                        <md-button slot="title" class="md-button md-button-link md-white md-simple dropdown-toggle" data-toggle="dropdown">
                          <i class="material-icons">face</i> 
                          <p>Embarque Conosco</p>
                        </md-button>
                        <ul class="dropdown-menu">
                          <li>
                            <a href="#/cadastroCliente" class="dropdown-item">
                              <i class="material-icons">person_add</i> Novo Usuário
                            </a>
                          </li>
                          <li>
                            <a href="#/login" class="dropdown-item">
                              <i class="material-icons">how_to_reg</i> Entrar
                            </a>
                          </li>
                        </ul>
                      </drop-down>
                    </div>
                  </a>
                </li>


              
            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<style>
.img-nav{
    max-height: 50px;
  }

  .shop{
    font-size: 0.8em !important;
    padding: 0.35em 0.5em 0.35em 0.48em !important;
    margin-top: 1em !important;
  }
</style>


<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

import MobileMenu from "@/mk/layout/MobileMenu";
export default {
  components: {
    MobileMenu
  },
  props: {
    type: {
      type: String,
      default: "dark"
    },
    colorOnScroll: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false
    };
  },
  computed: {
    showDownload() {
      const excludedRoutes = ["login", "landing", "profile"];
      return excludedRoutes.every(r => r !== this.$route.name);
    }
  },
  methods: {
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove("md-transparent");
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = "";
          navbarColor.classList.add("md-transparent");
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    }
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  }
};
</script>
