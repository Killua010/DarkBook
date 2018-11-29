<template>
  <md-toolbar class="md-dark">
    <div class="md-toolbar-row">
      <div class="md-toolbar-section-end">
        <md-button class="md-just-icon md-simple md-toolbar-toggle" :class="{toggled: $sidebar.showSidebar}" @click="toggleSidebar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <md-list-item href="#/catalogo">
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
                    <p>Olá Daniel</p>
                  </md-button>
                  <ul class="dropdown-menu">
                    <li>
                      <a href="#/" class="dropdown-item">
                        <i class="material-icons">close</i> Sair
                      </a>
                    </li>
                  </ul>
                </drop-down>
              </div>
            </a>
          </li>
        </div>
      </div>
    </div>

  </md-toolbar>
</template>

<script>

export default{
  data () {
    return {
      selectedEmployee: null,
      employees: [
        'Jim Halpert',
        'Dwight Schrute',
        'Michael Scott',
        'Pam Beesly',
        'Angela Martin',
        'Kelly Kapoor',
        'Ryan Howard',
        'Kevin Malone'
      ]
    }
  },
  methods: {
    toggleSidebar () {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
    }
  }
}
</script>

<style lang="css">
</style>
