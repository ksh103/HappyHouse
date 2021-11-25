<template>
  <div class="modal" tabindex="-1" id="friendBookmarkModal">
    <div class="modal-dialog modal-lg modal-dialog-scrollable modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <i class="bi bi-bookmark-star-fill ms-2 me-2" style="font-size: 1.5rem;"></i>
          <h5 class="modal-title">{{ friendName }}님의 북마크</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <h5 class="p-3 m-0">관심 매물</h5>
            <div class="bg-white mb-2">
              <div class="card">
                <div class="card-body">
                  <table class="table text-center table-hover">
                    <thead class="fw-bold">
                      <tr>
                        <td>거래유형</td>
                        <td>아파트 · 주택명</td>
                        <td>주소</td>
                        <td>거래금액</td>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-if="houseOngoingList==0" class="border-bottom">
                        <td colspan="4">등록된 관심 매물이 없습니다.</td>
                      </tr>
                      <tr v-else v-for="(item, index) in this.houseOngoingList" v-bind:key="index" class="border-bottom">
                        <td>{{ item.type }}</td>
                        <td>{{ item.AptName }}</td>
                        <td>{{ item.dong }} {{ item.jibun }}</td>
                        <td>{{ item.dealAmount }}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <h5 class="p-3 m-0">관심 건물 정보</h5>
              <div class="card">
                <div class="card-body">
                  <table class="table text-center table-hover">
                    <thead class="fw-bold">
                      <tr>
                        <td>아파트 · 주택명</td>
                        <td>주소</td>
                        <td>건축년도</td>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-if="houseList==0" class="border-bottom">
                        <td colspan="3">등록된 관심 건물 정보가 없습니다.</td>
                      </tr>
                      <tr v-else v-for="(item, index) in this.houseList" v-bind:key="index" class="border-bottom">
                        <td class="text-start">{{ item.aptName }}</td>
                        <td>{{ item.dongName }} {{ item.jiBun }}</td>
                        <td>{{ item.buildYear }}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
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
  props: ['friendId', 'friendName'],
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
          console.log('!!!!!!!')
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
    },
  }
}
</script>

<style>

</style>