<template>
  <div class="body_area py-4 py-md-5">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-12 col-12">
          <div class="card mb-3">
              <div class="card-body p-4">
                  <h4 class="mb-4">개인정보 수정</h4>
                  <div class="d-flex align-items-center">
                      <!-- <img src="../../assets/images/profile_av.png" class="avatar lg rounded-circle" alt=""> -->
                      <div class="ms-4">
                          <input type="file" class="form-control d-none" id="avatar">
                          <label class="position-relative" for="avatar">
                              <span class="btn btn-primary btn-sm">
                                  image upload
                              </span>
                          </label>
                          <small class="d-block">이미지 사이즈 "256px"x"256px", 이미지 확장자 .jpg 또는 .png</small>
                      </div>
                  </div>
                  <form class="mt-4">
                      <div class="row mb-2">
                          <label class="col-sm-3 col-form-label">이름</label>
                          <div class="col">
                              <input type="text" class="form-control" placeholder="이름" value="강소현">
                          </div>
                      </div>
                      <div class="row mb-2">
                          <label class="col-sm-3 col-form-label">아이디</label>
                          <div class="col">
                              <input type="email" placeholder="아이디" class="form-control" required="" value="ksh">
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
                        <button type="submit" class="btn btn-primary">확인</button>
                    </div>
                  </form>
              </div>
          </div>
        </div>
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