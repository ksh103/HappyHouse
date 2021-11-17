<template>
  <!-- Main Body -->
    <div class="container">
        <div class="card col-md-5 mt-5" style="float: none; margin:0 auto;">
        <!-- Form -->
        <form class="row g-1 p-0 p-md-4">
            <div class="col-12 text-center mt-5" >
                <h1>LOGIN</h1>
            </div>
            <div class="col-12 mt-5">
                <div class="mb-3">
                    <label class="form-label">아이디</label>
                    <input type="email" class="form-control" placeholder="name@example.com">
                </div>
            </div>
            <div class="col-12">
                <div class="mb-3">
                    <div class="form-label">
                        <span class="d-flex justify-content-between align-items-center"> 
                        비밀번호
                        <a class="text-primary" href="auth-password-reset.html">비밀번호 찾기</a>
                        </span>
                    </div>
                    <input type="password" class="form-control" placeholder="***************">
                </div>
            </div>
            <div class="col-12">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        아이디 저장
                    </label>
                </div>
            </div>
            <div class="col-12 text-center mt-4 mb-3">
                <a href="index.html" class="btn btn-dark">login</a>
            </div>
            <div class="col-12 text-center mt-4">
                <span class="text-muted">회원가입 시 다양한 주거지 실거래가 정보를 제공합니다! <router-link class="nav-link" to="/user/join">join us</router-link></span>
            </div>
        </form>
        <!-- End Form -->
        </div>
    </div> <!-- End Row -->
</template>

<script>
export default {

}
</script>

<style>

</style>