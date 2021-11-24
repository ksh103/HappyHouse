<template>
  <div class="container my-5">
    <div class="card bg-transparent border-0 mt-4">
      <table id="dataTable" class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable text-center w-100">
        <thead>
          <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(board, index) in getBoardList" v-bind:key="index">
            <td>
              <span class="badge bg-success">{{ board.noticeId }}</span>
            </td>
            <td class="w-50 text-start">
              <h6 class="mb-0"><a @click="boardDetail(board.noticeId)" class="color-700 text-decoration-none">{{ board.title }}</a></h6>
            </td>
            <td>
              <div class="dropdown">
                <button class="btn btn-sm text-decoration-none" role="button" data-bs-toggle="dropdown">{{ board.userName }}</button>
                <ul class="dropdown-menu border-0 shadow p-3">
                  <li><a class="dropdown-item py-2 rounded" href="#">친구 추가</a></li>
                  <li><a class="dropdown-item py-2 rounded" href="#">Another action</a></li>
                  <li><a class="dropdown-item py-2 rounded" href="#">Something else here</a></li>
                </ul>
              </div>
            </td>
            <td>
              <span class="d-block">{{ makeDateStr(board.regDt.date.year, board.regDt.date.month, board.regDt.date.day, '.') }}</span>
            </td>
            <td>
              <span class="d-block">{{ board.readCount }}</span>
            </td>
          </tr>
        </tbody>
      </table>
      <pagination class="mt-3" v-on:call-parent="movePage"></pagination>
    </div>
    <div class="my-3 d-flex justify-content-end">
      <router-link class="btn btn-primary float-end" to="/board/notice/insert">글쓰기</router-link>
    </div>
      
  </div>
</template>

<script>
import Pagination from '@/components/Pagination.vue';

import util from "@/common/util.js";
import { mapActions, mapGetters, mapMutations } from 'vuex';

export default {
  name: 'BoardNoticeList',
  components: {
    Pagination
  },
  computed :{
    ...mapGetters('boardNoticeStore', ['getBoardList']),
  },
  methods : {
    ...mapActions('boardNoticeStore', ['boardList', 'boardDetail']),
    ...mapMutations('boardNoticeStore', ['SET_BOARD_MOVE_PAGE']),
    // pagination
    movePage(pageIndex){
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex );
      // this.$store.commit( 'SET_BOARD_MOVE_PAGE', pageIndex );
      this.SET_BOARD_MOVE_PAGE(pageIndex);
      this.boardList();
      console.log(this.$store._modules.root.state.userStore)
    },

    makeDateStr : util.makeDateStr,

  },
  created() {
    this.boardList();
  },
  mounted() {},
}
</script>

<style>

</style>