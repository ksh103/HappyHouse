<template>
  <div class="container">
    <div class="d-flex flex-column align-items-center">
      <h2>아이디와 비밀번호를 입력해 로그인해주세요.</h2>
      <h2>아직 회원이 아니라면 회원가입을 먼저 해주시기 바랍니다.</h2>
      <form class="row g-1 p-0 p-md-4" style="max-width: 32rem;">
        <!-- <div class="col-12 text-center mb-5">
            <h1>Sign in</h1>
            <span>Free access to our dashboard.</span>
        </div> -->
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label" for="userId">아이디</label>
            <input v-model="userId" id="userId" type="text" class="form-control form-control-lg" placeholder="아이디를 입력하세요">
          </div>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <div class="form-label" for="userPassword">비밀번호</div>
            <input v-model="userPassword" id="userPassword" type="password" class="form-control form-control-lg" placeholder="비밀번호를 입력하세요">
          </div>
        </div>
        <div class="col-12">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
              아이디 저장
            </label>
          </div>
        </div>
        <div class="col-12 text-center mt-4">
          <a @click="validateForm" class="w-100 btn btn-lg btn-block btn-primary lift text-uppercase">로그인</a>
        </div>
        <div class="d-flex col-12 text-center mt-2">
          <router-link to="/user/join" class="flex-grow-1 btn btn-lg btn-block btn-secondary lift fs-6 text-uppercase">회원가입</router-link>
          &nbsp;&nbsp;
          <router-link to="/user/findpassword" class="flex-grow-1 btn btn-lg btn-block btn-secondary lift fs-6 text-uppercase">비밀번호 찾기</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script>

import Vue from "vue";
import { mapActions } from 'vuex';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'Login2',
  data() {
    return {
      userId: '',
      userPassword: '',
    }
  },
  methods: {
    ...mapActions(['login']),
    validateForm() {
      // TODO : id, password validation
      this.$alertify.message("로그인 완료");
      // alert('validate form 후 로그인 호출')
      this.login({
        userId: this.userId,
        userPassword: this.userPassword
      });
    }
  }
}
</script>

<style>

</style>