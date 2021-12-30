<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <div class="d-flex justify-content-between align-items-center">
            <h1 class="h3 m-4">팔로워 (Follower)</h1>
            <i @click="showFriendInsertModal" class="bi bi-person-plus-fill float-end cursor-pointer me-2"></i>
          </div>
          <div class="row row-cols-xl-6 row-cols-lg-3 row-cols-md-3 row-cols-sm-2 row-cols-2 g-1 mb-4 row-deck">
            <div v-for="(item, index) in followingList" :key="index" class="col">
              <div class="card text-center">
                <div class="btn-group position-absolute top-0 right-0">
                  <a href="#" class="nav-link py-2 px-3 text-muted" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end border-0 shadow">
                    <li><a @click="showFriendBookmarkModal(item.userId, item.userName)" class="dropdown-item">관심 매물 보기</a></li>
                    <li><a @click="deleteFriend(item.userId, item.userName)" class="dropdown-item">삭제</a></li>
                  </ul>
                </div>
                <div class="card-body d-flex align-items-center justify-content-between flex-column">
                  <div class="me-auto ms-auto py-4">
                    <img v-if="item.userProfileImage" :src="item.userProfileImage" :alt="item.userName" style="width: 140px; height: 140px;" class="rounded-circle">
                    <img v-else src="http://localhost:8080/images/profile_av.png" :alt="item.userName" class="rounded-circle">
                  </div>
                  <div class="mt-2">
                      <h6 class="mb-0">{{ item.userName }}</h6>
                      <small class="text-muted">{{ item.userEmail }}</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
        <h1 class="h3 m-4">팔로잉 (Following)</h1>
          <div class="row row-cols-xl-6 row-cols-lg-3 row-cols-md-3 row-cols-sm-2 row-cols-2 g-1 mb-4 row-deck">
            <div v-for="(item, index) in followerList" :key="index+50" class="col">
              <div class="card text-center">
                <!--<div class="btn-group position-absolute top-0 right-0">
                  <a href="#" class="nav-link py-2 px-3 text-muted" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                  <ul class="dropdown-menu dropdown-menu-end border-0 shadow">
                    <li><a class="dropdown-item" href="#">관심 매물 보기</a></li>
                    <li><a class="dropdown-item" href="#">삭제</a></li>
                  </ul>
                </div>-->
                <div class="card-body d-flex align-items-center justify-content-between flex-column">
                  <div class="me-auto ms-auto py-4">
                    <img v-if="item.userProfileImage" :src="item.userProfileImage" :alt="item.userName" style="width: 140px; height: 140px;" class="rounded-circle">
                    <img v-else src="http://localhost:8080/images/profile_av.png" :alt="item.userName" class="rounded-circle">
                  </div>
                  <div class="mt-2">
                      <h6 class="mb-0">{{ item.userName }}</h6>
                      <small class="text-muted">{{ item.userEmail }}</small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <friend-insert-modal @modal-close="closeAfterInsert" />
    <friend-bookmark-modal @modal-close="closeAfterFriendBookmark" :friendId="selectFriendId" :friendName="setectFriendName"/>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import http from '@/common/axios.js';
import FriendInsertModal from './modal/FriendInsertModal.vue';
import FriendBookmarkModal from './modal/FriendBookmarkModal.vue';
import { Modal } from 'bootstrap';

export default {
  name: 'Friends',
  data() {
    return {
      items: [1, 2, 3, 4, 5, 6, 1],
      followingList: null,
      followerList: null,
      friendInsertModal: null,
      friendBookmarkModal: null,
      selectFriendId: null,
      setectFriendName: null,
    }
  },
  computed: {
    ...mapGetters('userStore', ['profileImgUrl'])
  },
  components: {
    FriendInsertModal,
    FriendBookmarkModal,
  },
  created() {
    this.getFollowingList();
    this.getFollowerList();
  },
  methods: {
    getFollowingList() {
      http.get('/friend/following')
        .then(({ data }) => {
          this.followingList = data.list;
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
    },
    getFollowerList() {
      http.get('/friend/follower')
        .then(({ data }) => {
          this.followerList = data.list;
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))

    },
    deleteFriend(toId, userName) {

      this.$swal({
        title: `친구 ${userName}을(를) 삭제하시겠습니까?`,
        icon: 'warning',
        dangerMode: true,
        buttons: true
      }).then(value => {
        if (value) {
          http.delete(`/friend/${toId}`)
            .then(({ data }) => {
              if (data.result == 'login') {
                this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
                  .then(() => {
                    this.SET_USER_LOGOUT();
                    this.$router.push('/user/login');
                  })
              } else {
                this.$swal('삭제 완료되었습니다.', { icon: 'success' })
                  .then((value) => {
                    this.getFollowingList();
                  }
                );
              }
            })
            .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
        }
      })
    },
    showFriendInsertModal() {
      this.friendInsertModal.show();
    },
    showFriendBookmarkModal(id, name) {
      console.log(id)
      console.log(name)
      this.selectFriendId = id;
      this.setectFriendName = name;
      this.friendBookmarkModal.show();
    },
    closeAfterInsert() {
      this.friendInsertModal.hide();
      this.getFollowingList();
      // console.log('모달 종료 후 동작!');
    },
    closeAfterFriendBookmark() {
      this.friendBookmarkModal.hide();
      // console.log('북마크모달종료')
    }
  },
  mounted() {
    this.friendInsertModal = new Modal(document.getElementById('friendInsertModal'));
    this.friendBookmarkModal = new Modal(document.getElementById('friendBookmarkModal'));
  }
}
</script>

<style>
  .bi-person-plus-fill{
    font-size: 1.7rem;

  }
  .bi-person-plus-fill:hover {
    color: dodgerblue;
  }
</style>