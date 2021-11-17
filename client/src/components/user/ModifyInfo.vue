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
          <form class="mt-4">
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">이름</label>
                <div class="col">
                    <input type="text" readonly class="form-control-plaintext" placeholder="이름" value="강소현">
                </div>
            </div>
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">아이디</label>
                <div class="col">
                    <input type="id" readonly class="form-control-plaintext" placeholder="아이디" required="" value="ksh">
                </div>
            </div>
            <div class="row mb-2">
                <label class="col-sm-3 col-form-label">이메일</label>
                <div class="col">
                    <input type="email" readonly class="form-control-plaintext" placeholder="아이디" required="" value="ksh@ssafy.com">
                </div>
            </div>
            <div class="row mb-2">
              <label class="col-sm-3 col-form-label">현재 비밀번호</label>
              <div class="col">
                  <input type="password" placeholder="현재 비밀번호를 입력해주세요." class="form-control" required="">
              </div>
            </div>
            <div class="row mb-2">
              <label class="col-sm-3 col-form-label">새 비밀번호</label>
              <div class="col">
                  <input type="password" placeholder="변경할 비밀번호를 입력해 주세요." class="form-control" required="">
              </div>
            </div>
            <div class="row mb-2">
              <label class="col-sm-3 col-form-label">새 비밀번호 확인</label>
              <div class="col">
                  <input type="password" placeholder="변경할 비밀번호를 다시 입력해 주세요." class="form-control" required="">
              </div>
            </div>
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-dark">확인</button>
            </div>
          </form>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'ModifyInfo',
}
</script>

<style>

</style>