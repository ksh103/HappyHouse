<template>
  <div>
    <BasicHeader name="회원가입" />
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h2 class="mb-4">Happy House 가입을 환영합니다!</h2>
          <div class="py-1 px-2 d-flex">
            <div class="form-check pe-3">
                <input value="common" v-model="registerType" class="form-check-input" type="radio" id="registerTypeCommon">
                <label class="form-check-label" for="registerTypeCommon">일반 회원</label>
            </div>
            <div class="form-check">
                <input value="company" v-model="registerType" class="form-check-input" type="radio" id="registerTypeCompany">
                <label class="form-check-label" for="registerTypeCompany">기업 회원</label>
            </div>
          </div>
        <form class="row g-1 p-0 p-md-4 w-100">
          <table>
            <tr>
              <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userName">이름 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userName" id="userName" type="text" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">아이디 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userId" id="userId" type="text" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userPassword">비밀번호 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userPassword" id="userPassword" type="password" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userRePassword">비밀번호 확인<span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userRePassword" id="userRePassword" type="password" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userEmail">이메일 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userEmail" id="userEmail" type="email" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr v-if="registerType === 'company'">
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userAddress">주소 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userAddress" id="userAddress" type="text" class="mb-4 form-control form-control-lg"></td>
            </tr>
          </table>
          <div class="col-12 text-center mt-4">
            <router-link to="/user/login" class="btn px-4 btn-block btn-secondary lift text-uppercase">로그인 이동</router-link>&nbsp;
            <a @click="join" class="btn px-4 btn-block btn-primary lift text-uppercase">회원가입</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import Vue from 'vue';
import VueSwal from 'vue-swal'
import { mapActions } from 'vuex';
import BasicHeader from '@/components/layout/BasicHeader.vue';

Vue.use(VueSwal);

export default {
  name: 'Join',
  data() {
    return {
      registerType: '',
      userId: '',
      userPassword: '',
      userRePassword: '',
      userName: '',
      userEmail: '',
      userAddress: ''
    }
  },
  components: {
    BasicHeader
  },
  methods: {
    ...mapActions(['login']),
    join() {
      if(this.registerType == 'common'){
        http.post('/user', {
          userName: this.userName,
          userId: this.userId,
          userPassword: this.userPassword,
          userEmail: this.userEmail,
        })
        .then(({ data }) => {
          let $this = this;
          if (data.result == 1) {
            this.$swal('회원 가입이 완료되었습니다.', '환영합니다. 로그인 페이지로 이동합니다.', { icon: 'success' })
              .then(() => $this.$router.push('/user/login/'));
          }
        })
        .catch(error => {
          console.log("UserRegisterVue: error : ");
          console.log(error);
          if( error.response.status == '404'){
            this.$swal('서버에 문제가 발생하였습니다.');
          }
        })
      }else if(this.registerType == 'company'){
        http.post('/company/register', {
          compName: this.userName,
          compId: this.userId,
          compPassword: this.userPassword,
          compEmail: this.userEmail,
          compAddress: this.userAddress,
        })
        .then(({ data }) => {
          let $this = this;
          if (data.result == 1) {
            this.$swal('회원 가입이 완료되었습니다.', '환영합니다. 로그인 페이지로 이동합니다.', { icon: 'success' })
              .then(() => $this.$router.push('/user/login/'));
          }
        })
        .catch(error => {
          console.log("CompanyRegisterVue: error : ");
          console.log(error);
          if( error.response.status == '404'){
            this.$swal('서버에 문제가 발생하였습니다.');
          }
        })
      }
    }
  }
}
</script>

<style></style>
