<template>
  <div>
    <Header name="공지사항" desc="Welcome back to your dashboard, if need a help Contact us." />
    <div class="container text-center">
    <table class="table table-hover">
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
        <tr style="cursor:pointer" v-for="(board, index) in listGetters" @click="boardDetail(board.noticeId)" v-bind:key="index">
          <td>{{ board.noticeId }}</td>
          <td>{{ board.title }}</td>
          <td>{{ board.userName }}</td>          
          <td>{{ makeDateStr(board.regDt.date.year, board.regDt.date.month, board.regDt.date.day, '.') }}</td>            
          <td>{{ board.readCount }}</td>
        </tr>
      </tbody>
    </table>
    <pagination v-on:call-parent="movePage"></pagination>
    <div id="paginationWrapper"></div>
      <router-link class="btn btn-sm btn-primary" to="/board/insert">글쓰기</router-link>
    </div>
    <div class="col-1"></div>
    </div>
</template>

<script>
import Header from '@/components/Header.vue';
import Pagination from '@/components/Pagination.vue';

import http from "@/common/axios.js";
import util from "@/common/util.js";

export default {
  name: 'BoardNoticeList',
  components: {
    Header, Pagination
  },
  computed :{
    listGetters(){
      return this.$store.getters.getBoardList; 
    },
  },
  methods : {
    boardList(){
      this.$store.dispatch('boardList');      
    },
    // pagination
    movePage(pageIndex){
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex );
      this.$store.commit( 'SET_BOARD_MOVE_PAGE', pageIndex );

      this.boardList();
    },

    makeDateStr : util.makeDateStr,

    boardDetail(noticeId){
      alert("확인")

      http.get(
      '/notices/' + noticeId, // props variable
      )
      .then(({ data }) => {
        console.log("DetailVue: data : ");
        console.log(data);
        
        if( data.result == 'login' ){
          this.$router.push("/login")
        }else{
          this.$store.commit(
            'SET_BOARD_DETAIL',
            { 
              noticeId: data.dto.noticeId,
              title: data.dto.title,
              content: data.dto.content,
              userName: data.dto.userName,
              readCount : data.dto.readCount,
              regDt: this.makeDateStr(data.dto.regDt.date.year, data.dto.regDt.date.month, data.dto.regDt.date.day, '.'),
              fileList: data.dto.fileList,
              sameUser: data.dto.sameUser, // not data.dto.sameUser
            }
          );

          this.$router.push("/board/detail");
        }
      })
      .catch((error) => {
        console.log("DetailVue: error ");
        console.log(error);
      });
    }
  },
  created() {
    this.boardList();
  },
  mounted() {},
}
</script>

<style>

</style>