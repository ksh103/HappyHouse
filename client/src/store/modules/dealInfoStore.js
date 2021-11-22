import http from "@/common/axios.js";
import Vue from 'vue';

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
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    getDong({ commit }, guCode) {
      http.get(`/info/dong/${guCode}`)
        .then(response => {
          console.log(response.data)
          commit('SET_DONG', response.data.dongDto);
        })
        .catch(error => {
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    getHouseListByDong({ commit }, dongName) {
      http.get(`/house/detail/dong/${dongName}`)
        .then(({ data }) => {
          console.log(data)
          commit('SET_HOUSE_LIST', data.houseDetailDto);
        })
        .catch(error => {
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    getHouseListByKeyword({ commit }, keyword) {
      http.get(`/house/detail/keyword/${keyword}`)
        .then(({ data }) => {          
          if (data.houseDetailDto) {
            console.log(data)
            commit('SET_HOUSE_LIST', data.houseDetailDto);
          }
        })
        .catch(error => {
          console.log(error)
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    }
  }
}

export default dealInfoStore;