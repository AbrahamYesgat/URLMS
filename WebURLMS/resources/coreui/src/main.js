// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VeeValidate from 'vee-validate';
import BootstrapVue from 'bootstrap-vue'
import App from './App'
import router from './router'

Vue.use(BootstrapVue)
Vue.use(VeeValidate);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {
    App
  }
})
