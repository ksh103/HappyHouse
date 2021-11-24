<template>
  <div>
    <div class="block-header pt-4 pt-lg-5 pb-0">
      <div class="container">
        <div class="row mb-3">
          <div class="col">
            <ul class="breadcrumb bg-transparent mb-0">
              <li class="breadcrumb-item"><a href="index.html">Home</a></li>
              <li class="breadcrumb-item active">마이 페이지</li>
            </ul>
          </div>
          <div class="col">
            <ul class="list-unstyled d-sm-flex justify-content-end mb-0 d-none">
              <li>
                <input @change="uploadProfileImg" type="file" class="form-control d-none" id="avatar">
                <label class="position-relative" for="avatar">
                  <span class="btn btn-primary btn-sm">
                    Upload avatar
                  </span>
                </label>
              </li>
            </ul>
          </div>
        </div>
        <!-- .row end-->
        <div class="row">
          <div class="col-12">
            <!-- card: Calendar -->
            <div class="card">
              <div class="card-body">
                <div class="d-flex align-items-center flex-column flex-md-row">
                    <img v-if="profileImgUrl" :src="profileImgUrl" alt="프로필 이미지" style="width: 140px; height: 140px;" class="rounded-circle">
                    <img v-else src="../assets/images/profile_av.png" alt="프로필 이미지" class="rounded-circle">
                    <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
                        <h5 class="font-weight-bold d-inline-block me-2">{{ name }} </h5>님
                        <div class="text-muted mb-4"><span class="text-dark">가입일</span> : {{ regDt.year }}-{{ regDt.month }}-{{ regDt.day }}</div>
                        <a v-if="level != '3'" @click="showFollowers" style="cursor: pointer;" class="text-decoration-none d-inline-block text-primary"> <strong>{{ followers }}</strong> <span class="text-muted">followers</span> </a>
                        <a v-if="level != '3'" @click="showFollowing" style="cursor: pointer;" class="text-decoration-none d-inline-block text-primary ms-3"> <strong>{{ following }}</strong> <span class="text-muted">following</span> </a>
                    </div>
                </div>
              </div>
            </div> 
            <!-- .Card End -->
          </div>
          <div class="col-12">
            <ul class="nav nav-tabs tab-card mt-3 border-bottom-0">
                <li class="nav-item"><a id="profile" @click="moveTo('Profile')" class="cursor-pointer nav-link active">프로필</a></li>
                <li v-if="level != '3'" class="nav-item"><a id="management" @click="moveTo('Management')" class="cursor-pointer nav-link">북마크 / 리뷰 관리</a></li>
                <li v-if="level != '3'" class="nav-item"><a id="friends" @click="moveTo('Friends')" class="cursor-pointer nav-link">친구</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState, mapGetters, mapMutations } from "vuex";
import http from "@/common/axios.js";

const storeName = 'userStore';

export default {
  name: 'MyAccount',
  data() {
    return {
      // 추후 스토어에서 받은 가입일 데이터로 수정할 것
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
    ...mapState(storeName, ['name', 'id', 'regDt', 'level']),
    ...mapGetters(storeName, ['profileImgUrl'])
  },
  methods: {
    ...mapMutations('userStore', ['SET_USER_LOGOUT', 'SET_PROFILE_IMG']),
    showFollowers() {
      alert('showFollowers')
    },
    showFollowing() {
      alert('showFolloweing')

    },
    moveTo(name) {
      this.$router.push({ name });
    },
    uploadProfileImg(e) {
      // single file img upload
      let attachFile = e.target.files[0];
      console.log(attachFile)
      if (attachFile) {
        let formData = new FormData();
        formData.append("profileImg", attachFile);
        // alert('전송')
        http.post('/user/profileImg', formData, {
          headers: { 'Content-Type': 'multipart/form-data' }
        })
          .then(({ data }) => {
            console.log(data);

            if (data.result == 'login') {
              this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
                .then(() => {
                  this.SET_USER_LOGOUT();
                  this.$router.push('/user/login');
                  // 리렌더링!!
                })
            } else {
              
              this.$swal('프로필 이미지 변경이 완료되었습니다.', { icon: 'success' })
                .then((value) => {
                  this.SET_PROFILE_IMG(`http://localhost:8080${data.uploadProfileImgUrl}`);
                });
            }
          })
          .catch(error => this.$swal('aa'))
      }
    },
    getFriendCount() {
      if(this.level == '2'){
        http.get('/friend/count')
        .then(({ data }) => {
          if (data.result == 'login') {
            this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
              .then(() => {
                this.SET_USER_LOGOUT();
                this.$router.push('/user/login');
              })
          } else {
            this.followers = data.followerCount;
            this.following = data.followingCount;
          }
        })
      }
    }
  },
  created() {
    this.getFriendCount();
  }
}
</script>

<style>
  .cursor-pointer {
    cursor: pointer;
  }
  #proflieImg {
    width: 140px;
    height: 140px;
  }
</style>