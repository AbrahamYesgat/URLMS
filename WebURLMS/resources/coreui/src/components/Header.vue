<template>
  <header class="app-header navbar">
    <button class="navbar-toggler mobile-sidebar-toggler d-lg-none" type="button" @click="mobileSidebarToggle">&#9776;</button>
    <b-link class="navbar-brand" to="#"></b-link>
    <button class="navbar-toggler sidebar-toggler d-md-down-none" type="button" @click="sidebarMinimize">&#9776;</button>
    <b-nav is-nav-bar class="ml-auto top-right-name">
      <b-nav-item-dropdown right>
        <template slot="button-content">
          <span class="d-md-down-none">Andre</span>
        </template>
        <b-dropdown-item @click="seeLabs"><i class="fa fa-flask"></i> See labs</b-dropdown-item> 
        <b-dropdown-divider></b-dropdown-divider> 
        <b-dropdown-item><i class="fa fa-user"></i> Profile</b-dropdown-item>
        <b-dropdown-item><i class="fa fa-lock"></i> Logout</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-nav>
  </header>
</template>
<script>
export default {
  name: 'header',
  methods: {
    sidebarToggle (e) {
      e.preventDefault()
      document.body.classList.toggle('sidebar-hidden')
    },
    sidebarMinimize (e) {
      e.preventDefault()
      document.body.classList.toggle('sidebar-minimized')
    },
    mobileSidebarToggle (e) {
      e.preventDefault()
      document.body.classList.toggle('sidebar-mobile-show')
    },
    seeLabs (e) {
      e.preventDefault()
      this.$router.push('/choose_lab')
    }
  }
}
</script>
