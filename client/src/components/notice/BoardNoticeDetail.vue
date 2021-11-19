<template>
  <div class="container">
    <Header name="공지사항 보기" desc="Welcome back to your dashboard, if need a help Contact us." />
    <div class="body">
        <table class="table">
          <tbody>
            <tr><td>조회수</td><td>{{ $store.state.board.readCount }}</td></tr>
            <tr><td>글번호</td><td>{{ $store.state.board.noticeId }}</td></tr>
            <tr><td>제목</td><td>{{ $store.state.board.title }}</td></tr>
            <tr><td>내용</td><td v-html="$store.state.board.content"></td></tr>
            <tr><td>작성자</td><td>{{ $store.state.board.userName }}</td></tr>
            <tr><td>작성일시</td><td>{{ $store.state.board.regDt }}</td></tr>
            
            <tr><td colspan="2">첨부파일</td></tr>
            <tr>
              <td colspan="2">
                <div v-for="(file, index) in $store.state.board.fileList" :key="index">
                  <span class="fileName">{{ file.fileName }}</span>
                  &nbsp;&nbsp;
                  <a type="button" class="btn btn-outline btn-default btn-xs" v-bind:href="file.fileUrl" download>내려받기</a>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="footer">
        <router-link class="btn btn-sm btn-primary" to="/board/notice/modify">글 수정</router-link>
        <button class="btn btn-sm btn-danger" @click="deleteNotice($store.state.board.noticeId)">글삭제</button>
      </div>
  </div>
</template>

<script>
import Header from '@/components/Header.vue';

import http from "@/common/axios.js";

export default {
  name: 'BoardNoticeDetail',
  components: {Header},
  methods: {
    deleteNotice(noticeId){
      http.delete(
        '/notices/' + noticeId
      )
      .then(({ data }) => {
      console.log('DeleteVue: data :');
      console.log(data);
      if (data === "success") {
        // 이거 왜 안 찍히지
        alert("삭제 완료")
      }
      // 현재 route를 /list로 변경.
      this.$router.push('/board/notice');
    });
    }
  }
}
</script>

<style>

</style>