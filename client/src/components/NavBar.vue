<template>
  <div class="navbar navbar-expand-lg sticky-top py-md-2 border-bottom">
    <div class="container">
      <div class="left d-flex">
        <button class="navbar-toggler color-900" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-collapse"
          aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fa fa-bars"></i>
        </button>
        <a class="navbar-brand" href="index.html">
          <svg xmlns="http://www.w3.org/2000/svg" width="30" viewBox="0 0 64 80" fill="none">
            <path class="fill-primary" d="M58.8996 22.7L26.9996 2.2C23.4996 -0.0999999 18.9996 0 15.5996 2.5C12.1996 5 10.6996 9.2 11.7996 13.3L15.7996 26.8L3.49962 39.9C-3.30038 47.7 3.79962 54.5 3.89962 54.6L3.99962 54.7L36.3996 78.5C36.4996 78.6 36.5996 78.6 36.6996 78.7C37.8996 79.2 39.1996 79.4 40.3996 79.4C42.9996 79.4 45.4996 78.4 47.4996 76.4C50.2996 73.5 51.1996 69.4 49.6996 65.6L45.1996 51.8L58.9996 39.4C61.7996 37.5 63.3996 34.4 63.3996 31.1C63.4996 27.7 61.7996 24.5 58.8996 22.7ZM46.7996 66.7V66.8C48.0996 69.9 46.8996 72.7 45.2996 74.3C43.7996 75.9 41.0996 77.1 37.9996 76L5.89961 52.3C5.29961 51.7 1.09962 47.3 5.79962 42L16.8996 30.1L23.4996 52.1C24.3996 55.2 26.5996 57.7 29.5996 58.8C30.7996 59.2 31.9996 59.5 33.1996 59.5C35.0996 59.5 36.9996 58.9 38.6996 57.8C38.7996 57.8 38.7996 57.7 38.8996 57.7L42.7996 54.2L46.7996 66.7ZM57.2996 36.9C57.1996 36.9 57.1996 37 57.0996 37L44.0996 48.7L36.4996 25.5V25.4C35.1996 22.2 32.3996 20 28.9996 19.3C25.5996 18.7 22.1996 19.8 19.8996 22.3L18.2996 24L14.7996 12.3C13.8996 8.9 15.4996 6.2 17.3996 4.8C18.4996 4 19.8996 3.4 21.4996 3.4C22.6996 3.4 23.9996 3.7 25.2996 4.6L57.1996 25.1C59.1996 26.4 60.2996 28.6 60.2996 30.9C60.3996 33.4 59.2996 35.6 57.2996 36.9Z" fill="black"></path>
          </svg>
        </a>
      </div>
      
      <div class="collapse navbar-collapse justify-content-between" id="navbar-collapse">
        <ul class="navbar-nav">
          <li class="ms-lg-2 ms-md-1 nav-item active"><router-link class="nav-link" to="/board/notice">공지사항</router-link></li>
          <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="">메뉴1</router-link></li>
          <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="">메뉴2</router-link></li>
          <li class="ms-lg-2 ms-md-1 nav-item"><router-link class="nav-link" to="">메뉴3</router-link></li>
        </ul>
      </div>
      <div class="d-flex align-items-center">
        <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    Dropdown button
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="#">Action</a></li>
    <li><a class="dropdown-item" href="#">Another action</a></li>
    <li><a class="dropdown-item" href="#">Something else here</a></li>
  </ul>
</div>
        <!-- <div class="d-flex profile-dropdown">
          <div class="dropdown">
            <a href="javascript:void(0);" role="button" data-bs-toggle="dropdown" >
              <img alt="Image" :src="profileImg" class="avatar rounded-circle">
            </a>
            <ul class="dropdown-menu dropdown-menu-end shadow border-0 m-0 p-3">
              <li><a class="dropdown-item py-2 rounded" href="#"><i class="fa fa-cog me-3"></i>내 정보</a></li>
              <li><a class="dropdown-item py-2 rounded" href="#"><i class="fa fa-info-circle me-3"></i>로그아웃</a></li>
              <li><a class="dropdown-item py-2 rounded" href="#"><i class="fa fa-user me-3"></i>메뉴</a></li>
              <li><a class="dropdown-item py-2 rounded" href="#"><i class="fa fa-sign-out me-3"></i>메뉴</a></li>
            </ul>
          </div>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  computed: {
    profileImg: function() {
      return require('../assets/images/noProfile.png')
    }
  }
}
</script>

<style>

</style>