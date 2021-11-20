import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

import userStore from "./modules/userStore";
import boardNoticeStore from "./modules/boardNoticeStore";
import dealInfoStore from './modules/dealInfoStore';

export default new Vuex.Store({
  modules: {
    userStore,
    boardNoticeStore,
    dealInfoStore,
  },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ]
});