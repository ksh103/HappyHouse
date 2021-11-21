import http from "@/common/axios.js";
import router from '@/routers/routers.js'

const userStore = {
  namespaced: true,
  state: {
    isAuth: false,
    seq: 0,
    id: '',
    level: 0,
    password: '',
    name: '',
    email: '',
  },

  getters: {
    isAuth: function(state){
      return state.isAuth;
    },
  },

  mutations: {
    SET_USER(state, payload) {
      state.isAuth = true;
      state.seq = payload.seq;
      state.id = payload.id,
      state.level = payload.level;
      state.password = payload.password;
      state.name = payload.name;
      state.email = payload.email;
    },
    SET_USER_LOGOUT(state){
      state.isAuth = false;
      state.seq = 0,
      state.id = '',
      state.level = 0,
      state.password = '',
      state.name = '',
      state.email = ''
    },
    SET_USER_MODIFY(state, payload) {
      state.name = payload.name;
      state.email = payload.email;
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
        
          context.commit('SET_USER', { seq, id, level, password, name, email });

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
  }
}

export default userStore;