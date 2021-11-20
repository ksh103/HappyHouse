<template>
  <div class="navbar navbar-expand-lg sticky-top py-md-2 border-bottom">
    <div class="container">
      <div class="left d-flex">
        <button class="navbar-toggler color-900" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-collapse"
          aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fa fa-bars"></i>
        </button>
        <router-link class="navbar-brand" to="/">
          <img style="width: 64px;" src="../../assets/images/logo.png">
        </router-link>
      </div>
      
      <div class="collapse navbar-collapse justify-content-between" id="navbar-collapse">
        <ul class="navbar-nav">
          <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="/dealInfo">부동산 매물 조회</router-link></li>
          <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="/board/notice">공지사항</router-link></li>
        </ul>
      </div>
      <div class="d-flex align-items-center">
        <div class="d-flex profile-dropdown">
          <div class="dropdown" v-show="isAuth">
            <a href="javascript:void(0);" role="button" data-bs-toggle="dropdown" >
              <img alt="Image" :src="profileImg" class="avatar rounded-circle">
            </a>
            {{ name }}님 환영합니다.
            <ul class="dropdown-menu dropdown-menu-end shadow border-0 m-0 p-3">
              <li><a class="dropdown-item py-2 rounded" href="#"><i class="fa fa-user me-3"></i>프로필</a></li>
              <li><router-link class="dropdown-item py-2 rounded" to="/user/modify"><i class="fa fa-cog me-3"></i>프로필 수정</router-link></li>
              <li><router-link class="dropdown-item py-2 rounded" to="/user/modify"><i class="fa fa-heart me-3"></i>관심 매물</router-link></li>
              <li><a class="dropdown-item py-2 rounded" @click="onClickLogout"><i class="fa fa-info-circle me-3"></i>로그아웃</a></li>
            </ul>
          </div>
          <div class="isNotAuth" v-show="!isAuth">
            <ul class="navbar-nav">
              <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="/user/login">로그인</router-link></li>
              <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="/user/join">회원가입</router-link></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import Vue from "vue";
import { mapActions, mapState, mapGetters } from 'vuex';
import VueAlertify from 'vue-alertify';

Vue.use(VueAlertify);

export default {
  name: 'NavBar',
  methods: {
    ...mapActions('userStore', ["logout"]),
    onClickLogout(){
      this.logout();
      this.$router.push("/user/login");
      this.$alertify.message("로그아웃 완료");
    },
  },
  computed: {
    ...mapState('userStore', ['name']),
    ...mapGetters('userStore', ['isAuth']),
    profileImg: function() {
      return require('../../assets/images/noProfile.png')
    },
  },
}
</script>

<style>

</style>