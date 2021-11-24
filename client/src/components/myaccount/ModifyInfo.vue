<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">정보 수정</h1>
          <div class="d-flex flex-column align-items-center">
            <form class="row g-1 p-0 p-md-4 w-100">
              <table>
                <tr>
                  <td style="width: 15%;" class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userName">이름 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input v-model="userName" id="userName" type="text" class="mb-4 form-control form-control-lg"></td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">아이디 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input readonly v-model="userId" id="userId" type="text" class="mb-4 form-control form-control-lg"></td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">이메일 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input v-model="userEmail" id="userEmail" type="email" class="mb-4 form-control form-control-lg"></td>
                </tr>
                <tr v-if="level == '3'">
                  <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userId">주소 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input v-model="compAddress" id="compAddress" type="text" class="mb-4 form-control form-control-lg"></td>
                </tr>
              </table>
              <div class="col-12 text-center my-4">
                <router-link to="/myaccount" class="btn px-4 btn-block btn-secondary lift text-uppercase">취소</router-link>&nbsp;
                <a @click="modifyInfo" class="btn px-4 btn-block btn-primary lift text-uppercase">수정 완료</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import { mapState, mapMutations } from "vuex";

const storeName = 'userStore';
const SUCCESS = 1;

export default {
  name: 'ModifyInfo',
  data() {
    return {
      userId: '',
      userName: '',
      userEmail: '',
      compAddress: '',
    }
  },
  methods: {
    ...mapMutations(storeName, ['SET_USER_MODIFY', 'SET_COMPANY_USER_MODIFY']),
    modifyInfo() {
      if(this.level == '2'){
        http.put('/user/modify', {
          userName: this.userName,
          userId: this.userId,
          userEmail: this.userEmail,
        })
        .then(response => {
          console.log(response)
          if (response.data.result === SUCCESS) {
            this.SET_USER_MODIFY({
              name: this.userName,
              email: this.userEmail,
            });
            this.$swal('정보 수정이 완료되었습니다.', { icon: 'success' })
              .then(() => this.$router.push('/myaccount'));
          }
        })
        .catch(error => {
          console.log("RegisterVue: error : ");
          console.log(error);
          this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
      }else if(this.level == '3'){
        http.put('/company/modify', {
          compName: this.userName,
          compId: this.userId,
          compEmail: this.userEmail,
          compAddress: this.compAddress
        })
        .then(response => {
          console.log(response)
          if (response.data.result === SUCCESS) {
            this.SET_COMPANY_USER_MODIFY({
              name: this.userName,
              email: this.userEmail,
              address: this.compAddress
            });
            this.$swal('정보 수정이 완료되었습니다.', { icon: 'success' })
              .then(() => this.$router.push('/myaccount'));
          }
        })
        .catch(error => {
          console.log("RegisterVue: error : ");
          console.log(error);
          this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
      }
    },
  },
  computed: {
    ...mapState(storeName, ['id', 'name', 'email', 'address', 'level']),
  },
  created() {
    this.userId = this.id;
    this.userName = this.name;
    this.userEmail = this.email;
    this.compAddress = this.address;
  }
}
</script>

<style>

</style>