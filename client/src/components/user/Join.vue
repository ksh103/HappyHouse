<template>
  <div>
    <BasicHeader name="회원가입" />
    <div class="container my-5">
      <div class="d-flex flex-column align-items-center">
        <h2 class="mb-4">Happy House 가입을 환영합니다!</h2>
        <ul class="nav nav-tabs tab-body-header rounded d-inline-flex w-25 mb-4">
          <li class="nav-item flex-grow-1 text-center">
            <input value="common" v-model="userType" class="d-none" type="radio" id="common">
            <label :class="[userType=='common' ? 'active' : '']" class="nav-link cursor-pointer" for="common">일반 회원</label>
          </li>
          <li class="nav-item flex-grow-1 text-center">
            <input value="company" v-model="userType" class="d-none" type="radio" id="company">
            <label :class="[userType=='company' ? 'active' : '']" class="nav-link cursor-pointer" for="company">기업 회원</label>
          </li>
        </ul>
        <form class="row g-1 p-0 p-md-4 w-100">
          <table>
            <tr>
              <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userName">이름 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userName" id="userName" type="text" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">아이디 <span class="text-danger">*</span></label></td>
              <td v-if="userIdChk" class="px-4">
                <input v-model="userId" @change="idcheck" id="userId" type="text" class="mb-4 form-control form-control-lg">
              </td>
              <td v-else class="px-4 form-floating">
                <input v-model="userId" @change="idcheck" id="userId" type="text" class="mb-4 form-control is-invalid form-control-lg">
                <label for="userId" class="ps-5 text-danger">중복된 아이디가 존재합니다. 다른 아이디를 입력하세요.</label>
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userPassword">비밀번호 <span class="text-danger">*</span></label></td>
              <td v-if="userPwdChk" class="px-4">
                <input v-model="userPassword"  id="userPassword" type="password" class="mb-4 form-control form-control-lg">
              </td>
              <td v-else class="px-4 form-floating">
                <input v-model="userPassword"  id="userPassword" type="password" class="mb-4 form-control form-control-lg">
                <label for="userPassword" class="ps-5 text-danger">비밀번호가 일치하지 않습니다. </label>
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userRePassword">비밀번호 확인<span class="text-danger">*</span></label>
              </td>
              <td class="px-4">
                <input v-model="userRePassword" @change="passwordcheck" id="userRePassword" type="password" class="mb-4 form-control form-control-lg">
              </td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userEmail">이메일 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userEmail" id="userEmail" type="email" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr>
              <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userEmail">휴대전화 <span class="text-danger">*</span></label></td>
              <td class="px-4"><input v-model="userPhone" id="userPhone" type="text" class="mb-4 form-control form-control-lg"></td>
            </tr>
            <tr v-if="userType === 'company'">
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
import { mapActions } from 'vuex';
import BasicHeader from '@/components/layout/BasicHeader.vue';

export default {
  name: 'Join',
  data() {
    return {
      userType: 'common',
      userId: '',
      userPassword: '',
      userRePassword: '',
      userName: '',
      userEmail: '',
      userPhone: '',
      userAddress: '',
      userIdChk: true,
      userPwdChk: true,
    }
  },
  components: {
    BasicHeader
  },
  methods: {
    ...mapActions(['login']),
    idcheck(){
      if(this.userId=='' || this.userId==null) {
        this.userIdChk = false;
        return;
      }
      if(this.userType == 'common'){
        http.get(`/user/idcheck/${this.userId}`)
          .then(({data}) => {
            if(data.result == 3){
              this.userIdChk = false;
            } else if (data.result == 4) {
              this.userIdChk = true;
            }
          })
          .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
      } else if(this.userType == 'company'){
        http.get(`/company/idcheck/${this.userId}`)
          .then(({data}) => {
            if(data.result == 3){
              this.userIdChk = false;
            } else if (data.result == 4) {
              this.userIdChk = true;
            }
          })
          .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
      }
    },
    passwordcheck(){
      if(this.userPassword === this.userRePassword){
        this.userPwdChk = true;
        return; 
      }else if(this.userPassword != this.userRePassword) {
        this.userPwdChk = false;
        return;
      }
    },
    validateForm() {
      //if (!this.userIdChk || !this.userPwdCheck) {
      if (!this.userIdChk) {
        this.$swal('가입 양식을 다시 한 번 확인해주세요.', { icon: 'error' });
        return false;
      }
      return true;
    },
    join() {
      if (!this.validateForm()) return;
      if(this.userType == 'common'){
        http.post('/user/register', {
          userName: this.userName,
          userId: this.userId,
          userPassword: this.userPassword,
          userEmail: this.userEmail,
          userPhone: this.userPhone,
        })
        .then(({ data }) => {
          if (data.result == 1) {
            this.$swal('회원 가입이 완료되었습니다.', '환영합니다. 로그인 페이지로 이동합니다.', { icon: 'success' })
              .then((value) => this.$router.push('/user/login/'));
          }
        })
        .catch(error => {
          console.log("UserRegisterVue: error : ");
          console.log(error);
          this.$swal('서버에 문제가 발생하였습니다.');
        })
      }else if(this.userType == 'company'){
        http.post('/company/register', {
          compName: this.userName,
          compId: this.userId,
          compPassword: this.userPassword,
          compEmail: this.userEmail,
          compAddress: this.userAddress,
          compPhone: this.userPhone,
        })
        .then(({ data }) => {
          if (data.result == 1) {
            this.$swal('회원 가입이 완료되었습니다.', '환영합니다. 로그인 페이지로 이동합니다.', { icon: 'success' })
              .then((value) => this.$router.push('/user/login/'));
          }
        })
        .catch(error => {
          console.log("CompanyRegisterVue: error : ");
          console.log(error);
            this.$swal('서버에 문제가 발생하였습니다.');
        })
      }
    },
  }
}
</script>

<style scope>
.input_box button{
      float: left;
    margin-left: 1.047120%;
    width: 10.471204%;
    height: 55px;
    font-size: 16px;
    color: #fff;
    text-align: center;
    background: #6e7c8c;
}
</style>
