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
                  <td class="px-4"><input v-model="userPassword" id="userPassword" type="password" class="mb-4 form-control form-control-lg"></td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark"><label class="mb-4 form-label" for="userRePassword">비밀번호 확인 <span class="text-danger">*</span></label></td>
                  <td class="px-4"><input v-model="userRePassword" id="userRePassword" type="password" class="mb-4 form-control form-control-lg"></td>
                </tr>
              </table>
              <div class="col-12 text-center my-4">
                <router-link to="/myaccount" class="btn px-4 btn-block btn-secondary lift">취소</router-link>&nbsp;
                <a @click="onChangeBtn" class="btn px-4 btn-block btn-primary lift text-uppercase">변경 완료</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

const storeName = 'userStore';

export default {
  name: 'ModifyPassword',
  data() {
    return {
      userPassword: '',
      userRePassword: '',
    }
  },
  methods: {
    ...mapActions(storeName, ['modifyPassword']),
    onChangeBtn() {
      if (this.userPassword === '' || this.userRePassword === '') {
        this.$swal('비밀번호를 입력하세요.');
      } else if (this.userPassword === this.userRePassword) {
        this.modifyPassword(this.userPassword);
      } else {
        this.$swal('비밀번호가 일치하지 않습니다. 정확히 입력하세요.', { icons : 'error' })
      }
    },
  },
}
</script>

<style>

</style>