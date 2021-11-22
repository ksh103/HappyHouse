import Vue from 'vue'
import App from './App.vue'
import router from './routers/routers.js'
import VueSwal from 'vue-swal'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import store from './store/store.js'

Vue.config.productionTip = false
Vue.use(VueSwal);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
