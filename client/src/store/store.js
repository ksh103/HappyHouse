import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

import userStore from "./modules/userStore";
import boardNoticeStore from "./modules/boardNoticeStore";
import dealInfoStore from './modules/dealInfoStore';
import houseOnGoingStore from './modules/houseOnGoingStore';

export default new Vuex.Store({
  modules: {
    userStore,
    boardNoticeStore,
    dealInfoStore,
    houseOnGoingStore,
  },

  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ]
});