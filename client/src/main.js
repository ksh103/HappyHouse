import Vue from 'vue'
import App from './App.vue'
import router from './routers/routers.js'
// import store from './store/store.js'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import store from './store/store.js'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
