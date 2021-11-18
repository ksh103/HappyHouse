<template>
  <div class="container">
    <div class="d-flex flex-column align-items-center">
      <h2>회원가입 시 등록한 아이디와 이메일 입력해주세요.</h2>
      <form class="row g-1 p-0 p-md-4" style="max-width: 32rem;">
        <div class="col-12">
          <div class="mb-2">
            <label class="form-label" for="userId">아이디</label>
            <input v-model="userId" id="userId" type="text" class="form-control form-control-lg" placeholder="아이디를 입력하세요">
          </div>
        </div>
        <div class="col-12">
          <div class="mb-2">
            <div class="form-label" for="userEmail">이메일</div>
            <input v-model="userEmail" id="userEmail" type="email" class="form-control form-control-lg" placeholder="이메일을 입력하세요">
          </div>
        </div>
        <div class="col-12 text-center mt-4">
          <a @click="confirmBtn"  class="w-100 btn btn-lg btn-block btn-primary lift text-uppercase">비밀번호 찾기</a>
        </div>
      </form>
    </div>
  </div> 
</template>

<script>
import http from "@/common/axios.js";

export default {
  name: 'FindPassword',
  data() {
    return {
      userId: '',
      userEmail: '',
    }
  },
  methods: {
    confirmBtn(){
      http.post(
        "/user/password",
        {
          userId: this.userId,
          userEmail: this.userEmail
        }
      )
      .then((response) => {
        console.log(response.data);
        // 올바른 데이터 -> mem29238@gmail.com(으)로 임시비밀번호를 보냈습니다. 로그인 후 비밀번호를 변경해주세요. 메시지 띄우기

        // 잘못된 정보 메시지 처리
        this.$router.push("/")
      })
      .catch( error => {
        console.log(error);
      });
      this.findPassword();
    }
  }
}
</script>

<style></style>