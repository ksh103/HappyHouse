<template>
  <div>
    <BasicHeader name="로그인" />
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h2>아이디와 비밀번호를 입력해 로그인해주세요.</h2>
        <h2>아직 회원이 아니라면 회원가입을 먼저 해주시기 바랍니다.</h2>
        <form class="row g-1 p-0 p-md-4" style="max-width: 32rem;">
          <ul class="nav nav-tabs tab-body-header rounded d-inline-flex w-50 mb-4">
            <li class="nav-item flex-grow-1 text-center">
              <input value="common" v-model="userType" class="d-none" type="radio" id="common">
              <label :class="[userType=='common' ? 'active' : '']" class="nav-link cursor-pointer" for="common">일반 회원</label>
            </li>
            <li class="nav-item flex-grow-1 text-center">
              <input value="company" v-model="userType" class="d-none" type="radio" id="company">
              <label :class="[userType=='company' ? 'active' : '']" class="nav-link cursor-pointer" for="company">기업 회원</label>
            </li>
          </ul>
          <div class="col-12">
            <div class="mb-2">
              <label class="form-label" for="userId">아이디</label>
              <input v-model="userId" id="userId" type="text" class="form-control form-control-lg" placeholder="아이디를 입력하세요">
            </div>
          </div>
          <div class="col-12">
            <div class="mb-2">
              <div class="form-label" for="userPassword">비밀번호</div>
              <input @keyup.enter="validateForm" v-model="userPassword" id="userPassword" type="password" class="form-control form-control-lg" placeholder="비밀번호를 입력하세요">
            </div>
          </div>
          <div class="col-12 text-center mt-4">
            <a @click="validateForm" class="w-100 btn btn-lg btn-block btn-primary lift text-uppercase">로그인</a>
          </div>
          <div class="d-flex col-12 text-center mt-2">
            <router-link to="/user/join" class="flex-grow-1 btn btn-lg btn-block btn-secondary lift fs-6 text-uppercase">회원가입</router-link>
            &nbsp;&nbsp;
            <router-link to="/user/password" class="flex-grow-1 btn btn-lg btn-block btn-secondary lift fs-6 text-uppercase">비밀번호 찾기</router-link>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import BasicHeader from '@/components/layout/BasicHeader.vue';

export default {
  name: 'Login',
  data() {
    return {
      userType: 'common',
      userId: '',
      userPassword: '',
    }
  },
  components: {
    BasicHeader,
  },
  methods: {
    ...mapActions('userStore', ['login', 'complogin']),

    validateForm() {
      if(this.userType == 'common'){
        this.login({
          userId: this.userId,
          userPassword: this.userPassword
        });
      }else if(this.userType == 'company'){
        this.complogin({
          compId: this.userId,
          compPassword: this.userPassword
        });
      }
    }
  }
}
</script>

<style>

</style>