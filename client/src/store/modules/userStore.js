import Vue from 'vue';
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
    phone: '',
    profileImgUrl: undefined,
    regDt: '',

    // company
    address: '',
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
      state.phone = payload.phone;
      if (payload.profileImgUrl) 
        state.profileImgUrl = `http://localhost:8080${payload.profileImgUrl}`;
      state.regDt = payload.regDt;
    },
    SET_USER_LOGOUT(state){
      state.isAuth = false;
      state.seq = 0,
      state.id = '',
      state.level = 0,
      state.password = '',
      state.name = '',
      state.email = '',
      state.profileImgUrl = undefined
    },
    SET_USER_MODIFY(state, payload) {
      state.name = payload.name;
      state.email = payload.email;
    },
    SET_PASSWORD(state, payload) {
      state.password = payload;
    },
    SET_COMPANY_USER(state, payload) {
      state.isAuth = true;
      state.seq = payload.seq;
      state.id = payload.id,
      state.level = payload.level;
      state.password = payload.password;
      state.name = payload.name;
      state.email = payload.email;
      state.address = payload.address;
      state.phone = payload.phone;
      if (payload.profileImgUrl) 
        state.profileImgUrl = `http://localhost:8080${payload.profileImgUrl}`;
      state.regDt = payload.regDt;
    },
    SET_COMPANY_USER_MODIFY(state, payload) {
      state.name = payload.name;
      state.email = payload.email;
      state.address = payload.address;
    },
    SET_PROFILE_IMG(state, payload) {
      state.profileImgUrl = payload;
    }
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
        console.log("UserLoginVue: data : ");
        console.log(response.data);

        const { 
          dto: {
            userId: id,
            code: level,
            userSeq: seq,
            userName: name,
            userPassword: password,
            userEmail: email,
            userProfileimage: profileImgUrl,
            userPhone: phone,
            regDt: {
              date: regDt
            }
          }
        } = response.data;
        
          context.commit('SET_USER', { seq, id, level, password, name, email, profileImgUrl, phone, regDt });

          router.push("/")
        })
        .catch( error => {
          console.log("LoginVue: error : ");
          console.log(error);

          if (error.response.status == '404'){
            Vue.$swal('아이디 또는 비밀번호를 확인하세요.', { icon: 'error' });
          } else {
            Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
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
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    modifyPassword({ commit }, newPassword) {
      http.put('/user/password', {
          userPassword: newPassword,
        })
        .then(response => {
          if (response.data.result === 1) {
            commit('SET_PASSWORD', newPassword);
            Vue.$swal('비밀번호 변경이 완료되었습니다.', { icon: 'success' })
              .then(() => router.push('/myaccount'));
          }
        })
        .catch(error => {
          console.log("modifyPasswordVue: error : ");
          console.log(error);
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    complogin(context, { compId, compPassword }) {
      http.post(
        "/company/login",
        {
          compId,
          compPassword
        }
      )
      .then((response) => {
        console.log("CompanyLoginVue: data : ");
        console.log(response.data);

        const { 
          dto: {
            compId: id,
            code: level,
            compSeq: seq,
            compName: name,
            compPassword: password,
            compEmail: email,
            compAddress: address,
            compProfileimage: profileImgUrl,
            compPhone: phone,
            regDt: {
              date: regDt
            }
          }
        } = response.data;
        
          context.commit('SET_COMPANY_USER', { seq, id, level, password, name, email, address, profileImgUrl, regDt, phone });

          router.push("/")
        })
        .catch( error => {
          console.log("CompanyLoginVue: error : ");
          console.log(error);

          if (error.response.status == '404'){
            Vue.$swal('아이디 또는 비밀번호를 확인하세요.', { icon: 'error' });
          } else {
            Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
          }
        });
      },
      compModifyPassword({ commit }, newPassword) {
        http.put('/company/password', {
            compPassword: newPassword,
          })
          .then(response => {
            if (response.data.result === 1) {
              commit('SET_PASSWORD', newPassword);
              Vue.$swal('비밀번호 변경이 완료되었습니다.', { icon: 'success' })
                .then(() => router.push('/myaccount'));
            }
          })
          .catch(error => {
            console.log("compModifyPasswordVue: error : ");
            console.log(error);
            Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
          })
      },
  }
}

export default userStore;