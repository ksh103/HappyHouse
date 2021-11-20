import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

import userStore from "./modules/userStore";
import boardNoticeStore from "./modules/boardNoticeStore";

export default new Vuex.Store({
  modules: {
    userStore,
    boardNoticeStore,
  },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ]
});