<template>
  <div class="container">
      <div class="card col-md-5 mt-5" style="float: none; margin:0 auto;">
          <!-- Form -->
              <form class="row g-1 p-0 p-md-4">
                  <div class="col-12 text-center mb-5">
                      <h1>FIND PASSWORD</h1>
                      <span>회원가입 시 입력했던 이메일을 적어주세요</span>
                  </div>
                  <div class="col-12">
                      <div class="mb-2">
                          <label class="form-label">아이디</label>
                          <input v-model="userId" type="id" class="form-control" placeholder="아이디을 입력해주세요.">
                      </div>
                      <div class="mb-2">
                          <label class="form-label">이름</label>
                          <input v-model="userName" type="name" class="form-control" placeholder="이름을 입력해주세요.">
                      </div>
                      <div class="mb-2">
                          <label class="form-label">이메일</label>
                          <input v-model="userEmail" type="email" class="form-control" placeholder="이메일을 입력해주세요.">
                      </div>
                  </div>
                  <div class="col-12 text-center mt-4">
                      <a @click="findPassword" href="#" class="btn btn-dark">OK</a>
                  </div>
                  <div class="col-12 text-center mt-4">
                      <span class="text-muted"><router-link class="nav-link" to="/user/login">login</router-link></span>
                  </div>
              </form>
      </div>
  </div> 
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'FindPassword',
  data() {
    return {
      userId: '',
      userName: '',
      userEmail: '',
    }
  },
  methods: {
    ...mapActions(['findpassword']),
    findPassword(){
      this.findpassword({
        userId: this.userId,
        userName: this.userName,
        userEmail: this.userEmail
      });
    }
  }
}
</script>

<style></style>