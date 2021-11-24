<template>
  <div class="modal" tabindex="-1" id="friendInsertModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <i class="bi bi-person-plus-fill ms-2 me-2" style="font-size: 1.5rem;"></i>
          <h5 class="modal-title">친구 추가</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <h6 class="mx-2 mb-3">찾을 친구의 이름 또는 아이디를 입력하세요.</h6>
            <div class="input-group pb-3">
              <input type="text" v-model="inputKeyword" class="form-control d-inline-block" placeholder="아이디 또는 이름">
              <button @click="search" class="btn btn-primary d-inline-block" type="button">검색</button>
            </div>
            <div v-for="(user, index) in userList" :key="index+11">
              {{ user.userName }} : {{ user.userEmail }} <button @click="addFriend(user.userId)"></button>
            </div>
          </div>
          <ul v-if="userList" class="list-unstyled list-group list-group-custom list-group-flush mb-0 border-top">
            <li v-for="(user, index) in userList" :key="index" class="list-group-item px-md-4 py-3 d-flex justify-content-between">
              <a class="d-flex align-items-center text-decoration-none">
                  <img v-if="user.userProfileimage" class="rounded-circle" src="../../../assets/images/xs/avatar1.jpg" alt="1">
                  <img v-else class="rounded-circle" src="../../../assets/images/profile_av.png" alt="2">
                  <div class="flex-fill ms-3 text-truncate">
                      <h6 class="d-flex justify-content-between mb-0"><span>{{ user.userName }} ({{ user.userId }})</span></h6>
                      <span class="text-muted">{{ user.userEmail }}</span>
                  </div>
              </a>
              <i @click="addFriend(user.userName, user.userId)" class="bi bi-plus cursor-pointer" style="font-size: 2rem;"></i>
            </li>
            <li v-if="userList.length==0" class="list-group-item px-md-4 py-3">
              <a class="d-flex align-items-center text-decoration-none">
                데이터가 존재하지 않습니다.
              </a>
            </li>
          </ul>
        </div>
        <div class="modal-footer">
          <button @click="closeModal" class="btn btn-sm btn-secondary btn-outline" data-dismiss="modal" type="button">취소</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/common/axios.js";

const storeName = 'dealInfoStore';

export default {
  name: 'FriendInsertModal',
  data() {
    return {
      inputKeyword: '',
      userList: null
    }
  },
  methods: {
    search() {
      this.findUser();
    },
    findUser() {
      http.get(`/user/${this.inputKeyword}`)
        .then(({ data }) => {
          let list = data.userDto;
          console.log(list)
          this.userList = list.filter(item => !item.sameUser && !item.friend);
          console.log(this.userList)
        })
        .catch(error => {
          console.log(error);
          this.$swal('서버에 문제가 발생하였습니다.');
        })
    },
    addFriend(name, toId) {
      this.$swal(`${name}님을 친구로 추가하시겠습니까?`, {
        buttons: true
      }).then(value => {
        if(value) {
          http.post('/friend', { toId })
            .then(() => {
              this.$swal('처리되었습니다.', '친구 추가가 완료되었습니다.', { icon: 'success' })
                .then(() => this.closeModal());
            })
            .catch(error => {
              console.log(error);
              this.$swal('서버에 문제가 발생하였습니다.');
            })
        }
      })
    },
    initModal() {
      this.inputKeyword = '';
      this.userList = null;
    },
    closeModal() {
      this.$emit('modal-close');
    }
  },
  computed: {
    ...mapState(storeName, ['houseList']),
  },
  mounted() {
    console.log('modal mounted!!');
    let $this = this;
    this.$el.addEventListener('show.bs.modal', function () {
      $this.initModal();
    })
  }
}
</script>

<style scoped>
  .rounded-circle {
    width: 50px !important;
    height: 50px !important;
  }
</style>