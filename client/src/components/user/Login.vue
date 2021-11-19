<template>
  <div>
    <div class="block-header py-4 py-lg-5">
      <div class="container">
        <div class="row mb-4">
          <div class="col">
            <ul class="breadcrumb bg-transparent mb-0">
              <li class="breadcrumb-item"><a href="index.html">Home</a></li>
              <li class="breadcrumb-item active">로그인</li>
            </ul>
          </div>
        </div>
        <!-- .row end-->
        <div class="row">
          <div class="col-xl-8 col-lg-7 col-md-12">
            <div class="welcome-text mb-4">
              <h1 class="fs-3 mb-1">로그인</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h2>아이디와 비밀번호를 입력해 로그인해주세요.</h2>
        <h2>아직 회원이 아니라면 회원가입을 먼저 해주시기 바랍니다.</h2>
        <form class="row g-1 p-0 p-md-4" style="max-width: 32rem;">
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
  </div>
</template>

<script>

import Vue from "vue";
import { mapActions } from 'vuex';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'Login',
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