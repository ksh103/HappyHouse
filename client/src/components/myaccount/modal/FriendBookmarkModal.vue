<template>
  <div class="modal" tabindex="-1" id="friendBookmarkModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">리뷰 등록 {{ friendId }}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <div class="input-group w-75 d-flex pb-2">
              <input type="text" v-model="inputKeyword" class="form-control d-inline-block" placeholder="원하시는 아파트, 동명을 입력해주세요">
              <button @click="search" class="btn btn-primary d-inline-block" type="button">검색</button>
            </div>
            sdfafdsfdsfadsfadsfdsfdafdsafsdafsdfdas
          </div>
          <!-- <div class="pt-2 text-secondary">종합의견</div> -->

        </div>
        <div class="modal-footer">
          <button @click="closeModal" class="btn btn-sm btn-secondary btn-outline" data-dismiss="modal" type="button">취소</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";

export default {
  name: 'FriendBookmarkModal',
  props: ['friendId'],
  data() {
    return {
      inputKeyword: '',
      houseList: null,
      houseOngoingList: null,
    }
  },
  methods: {
    search() {
      // this.findUser();
      var a =1 ;
    },
    initModal() {
      this.inputKeyword = '';
      this.userList = null;
    },
    getBookmarkList() {
      // 친구의 관심 매물이 없을 때 예외 처리
      http.get(`/bookmark/friend/${this.friendId}`)
        .then(({ data }) => {
          console.log(data);
          this.houseList = data.houseList;
          this.houseOngoingList = data.houseOngoingList;
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }));
    },
    closeModal() {
      this.$emit('modal-close');
    }
  },
  created() {
    console.log('ccccccccc')
  },
  mounted() {
    console.log('modal mounted!!');
    let $this = this;
    this.$el.addEventListener('show.bs.modal', function () {
      $this.initModal();
    })
  },
  watch: {
    friendId(val) {
      if (val) {
        this.getBookmarkList();
      } else {
        console.log('first')
      }
    }
  }
}
</script>

<style>

</style>