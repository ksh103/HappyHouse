<template>
  <div class="body_area auth-pages d-flex align-items-center p-0 h100vh">
    <div class="container">
      <div class="card col-5 mt-5" style="float: none; margin:0 auto;">
      <!-- Form -->
      <form class="row g-1 p-0 p-md-4">
        <div class="col-12 text-center mt-5 mb-5">
          <h1>JOIN US</h1>
          <span >회원가입 시 다양한 주거지 실거래가 정보를 제공합니다.</span>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label">이름</label>
            <input v-model="userName" type="name" class="form-control" placeholder="이름을 입력하세요." />
          </div>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label">아이디</label>
            <input v-model="userId" type="id" class="form-control" placeholder="아이디를 입력하세요." />
          </div>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label">이메일</label>
            <input v-model="userEmail" type="email" class="form-control" placeholder="이메일을 입력하세요." />
          </div>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label">비밀번호</label>
            <input v-model="userPassword" type="password" class="form-control" placeholder="비밀번호를 입력하세요."/>
          </div>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label">비밀번호 확인</label>
            <input v-model="userRePassword" type="password" class="form-control" placeholder="비밀번호를 다시 입력하세요." />
          </div>
        </div>
        
        <div class="col-12 text-center mt-4">
          <a @click="join" href="#" class="btn btn-dark lift text-uppercase">join us</a>
        </div>
        <div class="col-12 text-center mt-4">
          <span class="text-muted">이미 계정이 존재하시나요?<router-link class="nav-link" to="/user/login">login</router-link></span>
        </div>
      </form>
      <!-- End Form -->
      </div>
      <!-- End Row -->
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
      // alert('hi')
      http.post('/user', {
          userName: this.userName,
          userId: this.userId,
          userPassword: this.userPassword,
          userEmail: this.userEmail,
        })
        .then(response => {
          let $this = this;
          if (response.result == 1) {
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
