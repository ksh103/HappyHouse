<template>
  <div>
    <div class="block-header pt-4 pt-lg-5 pb-0">
      <div class="container">
        <div class="row mb-4">
          <div class="col">
            <ul class="breadcrumb bg-transparent mb-0">
              <li class="breadcrumb-item"><a href="index.html">Home</a></li>
              <li class="breadcrumb-item active">마이 페이지</li>
            </ul>
          </div>
          <!-- <div class="col">
            <ul class="list-unstyled d-sm-flex justify-content-end mb-0 d-none">
              <li><a class="color-600" href="../../documentation/index.html">Documentation</a></li>
              <li><a class="color-600 ms-4" href="calendar.html">Calendar</a></li>
            </ul>
          </div> -->
        </div>
        <!-- .row end-->
        <div class="row">
          <div class="col-12">
            <!-- card: Calendar -->
            <div class="card">
              <div class="card-body">
                <div class="d-flex align-items-center flex-column flex-md-row">
                    <img src="../assets/images/profile_av.png" alt="" class="rounded-circle">
                    <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
                        <h5 class="font-weight-bold d-inline-block me-2">{{ name }} </h5>님
                        <div class="text-muted mb-4"><span class="text-dark">가입일</span> : {{ regdate }}</div>
                        <a @click="showFollowers" style="cursor: pointer;" class="text-decoration-none d-inline-block text-primary"> <strong>{{ followers }}</strong> <span class="text-muted">followers</span> </a>
                        <a @click="showFollowing" style="cursor: pointer;" class="text-decoration-none d-inline-block text-primary ms-3"> <strong>{{ following }}</strong> <span class="text-muted">following</span> </a>
                    </div>
                </div>
              </div>
            </div> 
            <!-- .Card End -->
          </div>
          <div class="col-12">
            <ul class="nav nav-tabs tab-card mt-3 border-bottom-0">
                <li class="nav-item"><a id="profile" @click="moveTo('Profile')" class="cursor-pointer nav-link active">프로필</a></li>
                <li class="nav-item"><a id="management" @click="moveTo('Management')" class="cursor-pointer nav-link">북마크 / 리뷰 관리</a></li>
                <li class="nav-item"><a id="friends" @click="moveTo('Friends')" class="cursor-pointer nav-link">친구</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const storeName = 'userStore';

export default {
  name: 'MyAccount',
  data() {
    return {
      // 추후 스토어에어 받은 가입일 데이터로 수정할 것
      regdate: '2020-10-11',
      followers: 22,
      following: 999,
    }
  },
  watch: {
    $route(to) {
      document.querySelectorAll('.cursor-pointer').forEach(el => {
        if (el.classList.contains('active')) el.classList.remove('active')
      });
      if (to.name === 'Management') {
        // console.log('m')
        document.querySelector('#management').classList.add('active');
      } else if (to.name === 'Profile') {
        // console.log('p')
        document.querySelector('#profile').classList.add('active');
      } else if (to.name === 'Friends') {
        // console.log('f')
        document.querySelector('#friends').classList.add('active');
      }
    }
  },
  computed: {
    ...mapState(storeName, ['name', 'id'])
  },
  methods: {
    showFollowers() {
      alert('showFollowers')
    },
    showFollowing() {
      alert('showFolloweing')

    },
    moveTo(name) {
      this.$router.push({ name });
    }
  }
}
</script>

<style>
  .cursor-pointer {
    cursor: pointer;
  }
</style>