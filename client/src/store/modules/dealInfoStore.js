import http from "@/common/axios.js";

const dealInfoStore = {
  namespaced: true,
  state: {
    gu: [],
    dong: [],
    houseList: [],
  },

  getters: {
    
  },

  mutations: {
    SET_GU(state, payload) {
      state.gu = payload;
    },
    SET_DONG(state, payload) {
      state.dong = payload;
    },
    SET_HOUSE_LIST(state, payload) {
      state.houseList = payload;
    },
  },

  actions: {
    getGu({ commit }, siCode) {
      http.get(`/info/gu/${siCode}`)
        .then(response => {
          console.log(response.data)
          commit('SET_GU', response.data.guDto);
        })
        .catch(error => {
          alert('Opps!! 서버에 문제가 발생했습니다.');
        })
    },
    getDong({ commit }, guCode) {
      http.get(`/info/dong/${guCode}`)
        .then(response => {
          console.log(response.data)
          commit('SET_DONG', response.data.dongDto);
        })
        .catch(error => {
          alert('Opps!! 서버에 문제가 발생했습니다.');
        })
    },
    getHouseListByDong({ commit }, dongName) {
      http.get(`/house/detail/${dongName}`)
        .then(response => {
          console.log(response.data)
          commit('SET_HOUSE_LIST', response.data.houseDetailDto);
        })
        .catch(error => {
          alert('Opps!! 서버에 문제가 발생했습니다.');
        })
    }
  }
}

export default dealInfoStore;