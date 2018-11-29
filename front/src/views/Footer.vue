<template>

<footer class="footer bg-dark"
      :class="{[`footer-${type}`]: type}"
      :data-background-color="backgroundColor">
  <div class="container">
      <nav>
          <ul>
              <li>
                  <a href="#/">
                    The Dark Book
                  </a>
              </li>
             <li>
              <a href="#/teamDeveloper">
                Sobre Nós
              </a>
            </li>
            <li>
              <a href="#">
                Licença
              </a>
            </li>
          </ul>
      </nav>
      <div class="copyright">
          &copy; {{year}}, feito por Daniel Dias
      </div>
  </div>
</footer>





</template>
<style>
  .bg-dark{
    background-color: black;
  }
</style>

<script>
export default {
  props: {
    backgroundColor: String,
    type: String
  },
  data() {
    return {
      year: new Date().getFullYear()
    };
  }
};
</script>