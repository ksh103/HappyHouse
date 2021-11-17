<template>
    <div class="container">
      <div class="card col-md-6 mt-5" style="float: none; margin:0 auto;">
        <div class="card-body p-4">
          <div class="col-12 text-center mt-3 mb-5" >
            <h2>개인정보 수정</h2>
          </div>
          <div class="d-flex align-items-center">
              <!-- <img src="../../assets/images/profile_av.png" class="avatar lg rounded-circle" alt=""> -->
              <div class="ms-4">
                  <input type="file" class="form-control d-none" id="avatar">
                  <label class="position-relative" for="avatar">
                      <span class="btn btn-dark">image upload</span>
                  </label>
                  <small class="d-block">이미지 사이즈 "256px"x"256px", 이미지 확장자 .jpg 또는 .png</small>
              </div>
          </div>
          <!-- <form class="mt-4"> -->
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">이름</label>
                <div class="col">
                    <input v-model="userName" type="text" class="form-control" placeholder="이름">
                </div>
            </div>
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">아이디</label>
                <div class="col">
                    <input v-model="userId" type="text" readonly class="form-control-plaintext" placeholder="아이디">
                </div>
            </div>
            <div class="row mb-2">
              <label class="col-sm-3 col-form-label">현재 비밀번호</label>
              <div class="col">
                  <input v-model="userPassword" type="password" placeholder="현재 비밀번호를 입력해주세요." class="form-control">
              </div>
            </div>
            <div class="row mb-2">
              <label class="col-sm-3 col-form-label">새 비밀번호</label>
              <div class="col">
                  <input type="password" placeholder="변경할 비밀번호를 입력해 주세요." class="form-control">
              </div>
            </div>
            <div class="row mb-2">
              <label class="col-sm-3 col-form-label">새 비밀번호 확인</label>
              <div class="col">
                  <input type="password" placeholder="변경할 비밀번호를 다시 입력해 주세요." class="form-control">
              </div>
            </div>
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">이메일</label>
                <div class="col">
                    <input v-model="userEmail" type="email" class="form-control" placeholder="이메일">
                </div>
            </div>
            <div class="d-flex justify-content-end">
                <button @click="modifyInfo" class="btn btn-dark">확인</button>
                <button @click="deleteUser" class="btn">탈퇴</button>
                <button @click="testAlertify" class="btn btn-dark">test</button>
            </div>
          <!-- </form> -->
        </div>
      </div>
    </div>
</template>

<script>
import http from "@/common/axios.js";
import Vue from 'vue';
import VueAlertify from 'vue-alertify';
import {  mapMutations } from 'vuex';

Vue.use(VueAlertify);

const SUCCESS = 1;

export default {
  name: 'ModifyInfo',
  data() {
    return {
      userId: '',
      userPassword: '',
      userRePassword: '',
      userName: '',
      userEmail: '',
    }
  },
  created() {
    this.userId = this.$store.state.user.id;
    this.userName = this.$store.state.user.name;
    this.userEmail = this.$store.state.user.email;
  },
  methods: {
    ...mapMutations(['SET_USER_MODIFY']),
    // ...mapActions([]),
    testAlertify() {
      this.$alertify.alert('정보 수정이 완료되었습니다.');
    },
    modifyInfo() {
      // validation

      http.put('/user', {
          userName: this.userName,
          userId: this.userId,
          userPassword: this.userPassword,
          userEmail: this.userEmail,
        })
        .then(response => {
          console.log(response)
          if (response.result == SUCCESS) {
            this.SET_USER_MODIFY({
              name: this.userName,
              password: this.userPassword,
              email: this.userEmail,
            });
            // 여기선 왜 alertify 가 안뜰까?
            this.$alertify.alert('정보 수정이 완료되었습니다.');
          }
        })
        .catch(error => {
          console.log("RegisterVue: error : ");
          console.log(error);
          if( error.response.status == '404'){
            this.$alertify.error('Opps!! 서버에 문제가 발생했습니다.');
          }
        })
    },

    deleteUser() {
      this.$alertify.confirm('회원 탈퇴하시겠습니까?', () => {
        console.log("why?");
        http.delete('/user')
          .then(response => {
            if (response.result == SUCCESS) {
              // 로그아웃 처리
            }
          })
      })
    }
  }
}
</script>

<style>

</style>