<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h4 class="mb-3">{{ title }}</h4>
            <hr>
            <p class="fs-6">Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature
            from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia,
            looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of
            the word in classical literature, discovered the undoubtable source.</p>
            <p class="fs-6">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form,
            by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of
            Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum
            generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the
            Internet.</p>
            <h5 class="mt-4 mb-3">Project point</h5>
            <ul class="list-unstyled list-style lh-lg">
              <li><i class="fa fa-angle-double-right me-3"></i>It is a long established fact that a reader will be distracted</li>
              <li><i class="fa fa-angle-double-right me-3"></i>Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text</li>
              <li><i class="fa fa-angle-double-right me-3"></i>There are many variations of passages of Lorem Ipsum available</li>
              <li><i class="fa fa-angle-double-right me-3"></i>The generated Lorem Ipsum is therefore always free from repetition</li>
              <li><i class="fa fa-angle-double-right me-3"></i>Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum"</li>
              <li><i class="fa fa-angle-double-right me-3"></i>It has survived not only five centuries, but also the leap into electronic</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <ul class="list-group">
          <li class="list-group-item d-flex justify-content-between align-items-center">
            글번호 :
            <span>{{ noticeId }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            작성자 :
            <span>{{ userName }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            조회수 :
            <span>{{ readCount }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            작성일시 :
            <span>{{ regDt }}</span>
          </li>
          <li v-for="(file, index) in fileList" :key="index" class="list-group-item">
            <a type="button" class="p-0 m-0 btn btn-outline btn-default btn-xs" v-bind:href="file.fileUrl" v-bind:download="file.fileName">
              <img style="width: 20px;" src="../../assets/images/download_icon.png">
              <span class="ms-2 fileName">{{ file.fileName }}</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
    <div class="footer">
      <router-link class="btn btn-sm btn-primary" to="/board/notice/modify">글 수정</router-link>
      <button class="btn btn-sm btn-danger" @click="deleteNotice(noticeId)">글삭제</button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import http from "@/common/axios.js";

const storeName = 'boardNoticeStore';

export default {
  name: 'BoardNoticeDetail',
  computed: {
    ...mapState(storeName, ['readCount', 'noticeId', 'title', 'content', 'userName', 'regDt', 'fileList']),
  },
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
.fileName:hover {
    color: #0a58ca;
}
</style>