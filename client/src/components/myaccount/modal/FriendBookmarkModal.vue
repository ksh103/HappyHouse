<template>
  <div class="modal" tabindex="-1" id="friendBookmarkModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">{{ friendName }}님의 북마크</h5>
          <!-- <h5 class="modal-title">{{ friendId }}님의 북마크</h5> -->
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <!-- <div v-for="(item, index) in this.houseList" v-bind:key="index" class="input-group w-75 d-flex pb-2">
              테스트{{  item.aptName }}
            </div> -->
            <div class="bg-white mb-2">
                <div class="border-bottom"><h5 class="p-3 m-0">관심 건물</h5></div>
                <div>
                  <table class="w-100">
                    <thead class="bg-secondary text-white">
                      <tr>
                      <td class="w-40">건물명</td>
                      <td class="ps-3 py-1">동</td>
                      <td class="w-20">설립년도</td>
                      </tr>
                    </thead>
                    <tbody class="px-2">
                      <tr v-if="this.houseList==0" class="border-bottom">
                      <td colspan="3" class="ps-3 py-2">등록된 매물이 없습니다.</td>
                      </tr>
                      <tr v-else v-for="(item, index) in this.houseList" v-bind:key="index" class="border-bottom">
                      <!-- <tr class="border-bottom"> -->
                      <!-- <td class="ps-3 py-2">{{ this.houseList[0].aptName }}</td> -->
                      <td>{{ item.aptName }}</td>
                      <td>{{ item.dongName }}</td>
                      <td>{{ item.buildYear }}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                <div class="border-bottom"><h5 class="p-3 m-0">관심 매물</h5></div>
                <div>
                  <table class="w-100">
                    <thead class="bg-secondary text-white">
                      <tr>
                      <td class="w-20">거래유형</td>
                      <td class="w-40">건물명</td>
                      </tr>
                    </thead>
                  </table>
                  <tbody class="px-2">
                    <tr v-if="this.houseOngoingList==0" class="border-bottom">
                    <td colspan="2" class="ps-3 py-2">등록된 매물이 없습니다.</td>
                    </tr>
                    <tr v-else v-for="(item, index) in this.houseOngoingList" v-bind:key="index" class="border-bottom">
                    <!-- <tr class="border-bottom"> -->
                    <!-- <td class="ps-3 py-2">{{ this.houseList[0].aptName }}</td> -->
                    <td >{{ item.type }}</td>
                    <td >{{ item.title }}</td>
                    </tr>
                </tbody>
                </div>
            </div>
       
            
          <!-- <div class="pt-2 text-secondary">종합의견</div> -->
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