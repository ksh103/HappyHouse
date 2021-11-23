<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="d-flex justify-content-between align-items-center">
            <h1 class="h3 m-4">나의 친구</h1>
            <button style="font-size: 14px;" class="btn btn-primary mt-4 mb-4 px-2 py-1 lift float-end">친구 추가</button>
          </div>
          <div class="row row-cols-xl-6 row-cols-lg-3 row-cols-md-3 row-cols-sm-2 row-cols-2 g-1 mb-4 row-deck">
            <div v-for="(item, index) in items" :key="index" class="col">
              <div class="card text-center">
                  <!-- Group Hover action -->
                <div class="btn-group position-absolute top-0 right-0">
                  <a href="#" class="nav-link py-2 px-3 text-muted" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end border-0 shadow">
                    <li><a class="dropdown-item" href="#">관심 매물 보기</a></li>
                    <li><a class="dropdown-item" href="#">삭제</a></li>
                  </ul>
                </div>                                            
                <div class="card-body d-flex align-items-center justify-content-between flex-column">
                  <div class="me-auto ms-auto py-4">
                    <img src="../../assets/images/profile_av.png" alt="" class="rounded-circle">
                  </div>
                  <div class="mt-2">
                      <h6 class="mb-0">김개똥</h6>
                      <small class="text-muted">ssafy@ssafy.com</small>
                  </div>
                </div>
              </div>
            </div>
            <!-- <div v-for="(item, index) in items" :key="index" class="col">
              <div class="card text-center">
                <div class="btn-group position-absolute top-0 right-0">
                  <a href="#" class="nav-link py-2 px-3 text-muted" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end border-0 shadow">
                    <li><a class="dropdown-item" href="#">관심 매물 보기</a></li>
                    <li><a class="dropdown-item" href="#">삭제</a></li>
                  </ul>
                </div>                                            
                <div class="card-body d-flex align-items-center justify-content-between flex-column">
                  <div class="me-auto ms-auto py-4">
                    <img src="../../assets/images/profile_av.png" alt="" class="rounded-circle">
                  </div>
                  <div class="mt-2">
                      <h6 class="mb-0">김개똥</h6>
                      <small class="text-muted">ssafy@ssafy.com</small>
                  </div>
                </div>
              </div>
            </div> -->
          </div> <!-- .row end-->
        </div>
      </div> <!-- .row end-->
      <div class="row">
        <div class="col-12">
          <div class="d-flex justify-content-between align-items-center">
            <h1 class="h3 m-4">나의 친구</h1>
            <button style="font-size: 14px;" class="btn btn-primary mt-4 mb-4 px-2 py-1 lift float-end">친구 추가</button>
          </div>
          <div class="row row-cols-xl-6 row-cols-lg-3 row-cols-md-3 row-cols-sm-2 row-cols-2 g-1 mb-4 row-deck">
            <div v-for="(item, index) in items2" :key="index" class="col">
              <div class="card text-center">
                  <!-- Group Hover action -->
                <div class="btn-group position-absolute top-0 right-0">
                  <a href="#" class="nav-link py-2 px-3 text-muted" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end border-0 shadow">
                    <li><a class="dropdown-item" href="#">관심 매물 보기</a></li>
                    <li><a class="dropdown-item" href="#">삭제</a></li>
                  </ul>
                </div>                                            
                <div class="card-body d-flex align-items-center justify-content-between flex-column">
                  <div class="me-auto ms-auto py-4">
                    <img src="../../assets/images/profile_av.png" alt="" class="rounded-circle">
                  </div>
                  <div class="mt-2">
                      <h6 class="mb-0">김개똥</h6>
                      <small class="text-muted">ssafy@ssafy.com</small>
                  </div>
                </div>
              </div>
            </div>
          </div> <!-- .row end-->
        </div>
      </div> <!-- .row end-->
    </div>
  </div>
</template>

<script>
import http from '@/common/axios.js';

export default {
  name: 'Friends',
  data() {
    return {
      items: [1, 2, 3, 4, 5, 6, 1],
      items2: [1, 2, 3, 4, 5, 6, 1],
      followingList: null,
      followerList: null,
    }
  },
  created() {
    this.getFollowingList();
    this.getFollowerList();
  },
  methods: {
    getFollowingList() {
      http.get('/friend/following')
        .then(({ data }) => {
          console.log(data);
          this.followingList = data.list;
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
    },
    getFollowerList() {
      http.get('/friend/follower')
        .then(({ data }) => {
          console.log(data);
          this.followerList = data.list;
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))

    }
  }
}
</script>

<style>

</style>