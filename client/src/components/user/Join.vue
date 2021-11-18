<template>
  <div class="container">
    <div class="d-flex flex-column align-items-center">
      <h2>Happy House 가입을 환영합니다!</h2>
      <form class="row g-1 p-0 p-md-4 w-100">
        <table>
          <tr>
            <td style="width: 15%;" class="px-4 border-top border-dark"><label class="form-label" for="userName">이름 <span class="text-danger">*</span></label></td>
            <td class="px-4"><input v-model="userName" id="userName" type="text" class="form-control form-control-lg"></td>
          </tr>
          <tr>
            <td class="px-4 border-top border-dark"><label class="form-label" for="userId">아이디 <span class="text-danger">*</span></label></td>
            <td class="px-4"><input v-model="userId" id="userId" type="text" class="form-control form-control-lg"></td>
          </tr>
          <tr>
            <td class="px-4 border-top border-dark"><label class="form-label" for="userPassword">비밀번호 <span class="text-danger">*</span></label></td>
            <td class="px-4"><input v-model="userPassword" id="userPassword" type="password" class="form-control form-control-lg"></td>
          </tr>
          <tr>
            <td class="px-4 border-top border-dark"><label class="form-label" for="userRePassword">비밀번호 확인<span class="text-danger">*</span></label></td>
            <td class="px-4"><input v-model="userRePassword" id="userRePassword" type="password" class="form-control form-control-lg"></td>
          </tr>
          <tr>
            <td class="px-4 border-top border-dark"><label class="form-label" for="userId">이메일 <span class="text-danger">*</span></label></td>
            <td class="px-4"><input v-model="userEmail" id="userEmail" type="email" class="form-control form-control-lg"></td>
          </tr>
        </table>
        <div class="col-12 text-center mt-4">
          <router-link to="/user/login" class="btn btn-lg px-5 btn-block btn-secondary lift text-uppercase">로그인 이동</router-link>&nbsp;
          <a @click="join" class="btn btn-lg px-5 btn-block btn-primary lift text-uppercase">회원가입</a>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import { mapActions } from 'vuex';

Vue.use(VueAlertify);

export default {
  name: 'Join',
  data() {
    return {
      userId: '',
      userPassword: '',
      userRePassword: '',
      userName: '',
      userEmail: '',
    }
  },
  methods: {
    ...mapActions(['login']),
    join() {
      http.post('/user', {
          userName: this.userName,
          userId: this.userId,
          userPassword: this.userPassword,
          userEmail: this.userEmail,
        })
        .then(({ data }) => {
          let $this = this;
          if (data.result == 1) {
            this.$alertify.alert('가입이 완료되었습니다. 로그인 페이지로 이동합니다.', function() {
              $this.$router.push('/user/login/');
            });
          }
        })
        .catch(error => {
          console.log("RegisterVue: error : ");
          console.log(error);
          if( error.response.status == '404'){
            this.$alertify.error('Opps!! 서버에 문제가 발생했습니다.');          
          }
        })
    }
  }
};
</script>

<style></style>
