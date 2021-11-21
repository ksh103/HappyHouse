<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">비밀번호 변경</h1>
          <div class="d-flex flex-column align-items-center">
            <form class="row g-1 p-0 p-md-4 w-100">
              <table>
                <tr>
                  <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userPw">비밀번호 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input readonly v-model="userPassword" id="userPassword" type="text" class="mb-4 form-control form-control-lg"></td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userRePassword">비밀번호 확인 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input v-model="userRePassword" id="userRePassword" type="email" class="mb-4 form-control form-control-lg"></td>
                </tr>
              </table>
              <div class="col-12 text-center mt-4">
                <router-link to="/myaccount" class="btn btn-lg px-5 btn-block btn-secondary lift text-uppercase">취소</router-link>&nbsp;
                <a @click="changePassword" class="btn btn-lg px-5 btn-block btn-primary lift text-uppercase">변경 완료</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueSwal from 'vue-swal'
import http from "@/common/axios.js";

Vue.use(VueSwal);

const storeName = 'userStore';
const SUCCESS = 1;

export default {
  name: 'ModifyInfo',
  data() {
    return {
      userPassword: '',
      userRePassword: '',
    }
  },
  methods: {
    changePassword() {
      if (this.userPassword === this.userRePassword) {
        alert('TODO')
      } else {
        this.$swal('비밀번호가 일치하지 않습니다. 정확히 입력하세요.', { icons : 'error' })
      }

      // http.put('/user', {
      //     userName: this.userName,
      //     userId: this.userId,
      //     userEmail: this.userEmail,
      //   })
      //   .then(response => {
      //     console.log(response)
      //     if (response.data.result === SUCCESS) {
      //       this.SET_USER_MODIFY({
      //         name: this.userName,
      //         email: this.userEmail,
      //       });
      //       this.$swal('정보 수정이 완료되었습니다.', { icon: 'success' })
      //         .then(() => this.$router.push('/myaccount'));
      //     }
      //   })
      //   .catch(error => {
      //     console.log("RegisterVue: error : ");
      //     console.log(error);
      //     if( error.response.status == '404'){
      //       this.$alertify.error('Opps!! 서버에 문제가 발생했습니다.');
      //     }
      //   })
    },
  },
}
</script>

<style>

</style>