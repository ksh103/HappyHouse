import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';
import http from "@/common/axios.js";
import router from '@/routers/routers.js'
import VueAlertify from 'vue-alertify';

Vue.use(Vuex);
Vue.use(VueAlertify);

// const SUCCESS = 1;

export default new Vuex.Store({
  state: {
    // 유저 정보
    user: {
      isAuth: false,
      seq: 0,
      id: '',
      level: 0,
      password: '',
      name: '',
      email: '',
    },
    // 공지사항 게시판 정보
    boardNotice: {

    },
    // ...
  },
  mutations: {
    SET_USER(state, payload) {
      state.user.isAuth = true;
      state.user = payload;
    },
    SET_USER_LOGOUT(state){
      state.user.isAuth = false;
      state.user.seq = 0,
      state.user.id = '',
      state.user.level = 0,
      state.user.password = '',
      state.user.name = '',
      state.user.email = ''
    },
    SET_USER_MODIFY(state, payload) {
      state.user.name = payload.name;
      state.user.password = payload.password;
      state.user.email = payload.email;
    },
  },
  actions: {
    login(context, { userId, userPassword }) {
      http.post(
          "/user/login",
          {
            userId,
            userPassword
          }
        )
        .then((response) => {
          console.log("LoginVue: data : ");
          console.log(response.data);

          const { 
            dto: {
              userId: id,
              userLevel: level,
              userSeq: seq,
              userName: name,
              userPassword: password,
              userEmail: email,
            }
          } = response.data;

          context.commit('SET_USER', { isAuth: true, seq, id, level, password, name, email });

          router.push("/")
        })
        .catch( error => {
          console.log("LoginVue: error : ");
          console.log(error);

          if (error.response.status == '404'){
            alert('아이디 또는 비밀번호를 확인하세요.')
            // this.$alertify.error('이메일 또는 비밀번호를 확인하세요.');
          } else {
            alert('Opps!! 서버에 문제가 발생했습니다.');
          }
        });
      },
    logout(context){
      http.get('/user/logout')
      .then(() => {
        context.commit("SET_USER_LOGOUT");
        router.push('/');
        }).catch(error => {
          console.log(error)
          alert('Opps!! 서버에 문제가 발생했습니다.');
          //this.$alertify.error('서버에서 문제 발생했습니다');
        })
    },
  },
  modules: {},
  getters: {
    isAuth : function(state){
      return state.user.isAuth;
    },
  },
  plugins: [
    createPersistedState()
  ]
});