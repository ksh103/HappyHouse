<template>
  <div class="container my-5">
    <form class="row g-3 basic-form">
      <div class="col-md-12">
          <label class="form-label ms-3">제목 <sup class="text-danger">*</sup></label>
          <input v-model="title" type="text" class="form-control" placeholder="제목을 입력하세요" required>
      </div>
      <div class="col-12">
        <div class="my-3">
          <label class="form-label ms-3">내용 <sup class="text-danger">*</sup></label>
          <div id=divEditorInsert></div>
        </div>
        <div class="form-check mb-3">
          <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert">
          <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
        </div>
        <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
          <input @change="changeFile" type="file" id="inputFileUploadInsert" class="form-control" multiple>
          <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper mt-3">
            <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" class="mx-2" style="max-width: 200px; max-height: 200px;">
          </div>
        </div>
      </div>
      <div class="col-12">
        <button @click.prevent="boardInsert" class="btn btn-primary float-end">작성완료</button>
        <router-link to="/board/notice" class="me-2 btn btn-secondary float-end">취소</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

Vue.use(CKEditor);

import http from "@/common/axios.js";

export default {
  name: 'BoardNoticeInsert',
  data() {
    return {
      title: '',
      CKEditor: '',
      attachFile: false,
      fileList: []
    };
  },
  methods: {
    changeFile(e) {
      if (e.target.files && e.target.files.length > 0){
        for (let i = 0; i < e.target.files.length; i++) {
          const file = e.target.files[i];
          this.fileList.push(URL.createObjectURL(file));
        }
      }
    },
    boardInsert() {
      let formData = new FormData();
      
      formData.append("title", this.title);
      formData.append("content", this.CKEditor.getData());

      // file upload
      let attachFiles = document.querySelector("#inputFileUploadInsert");
      let cnt = attachFiles.files.length;

      for (let i = 0; i < cnt; i++) {
        formData.append("file", attachFiles.files[i]);
      }

      http.post("/notices", formData, {
        headers: { 'Content-Type': 'multipart/form-data' } 
      })
        .then(({ data }) => {
          if (data === "login") {
            this.$router.push('/user/login');
          } else{
            this.$router.push('/board/notice');
          }
        })
        .catch(error => {
          console.log("noticeInsertVue: error ");
          console.log(error);
          this.$swal('서버에 문제가 발생하였습니다.' , { icon: 'error' });
        })
    },
  },
  mounted() {
    ClassicEditor
      .create(document.querySelector('#divEditorInsert'))
      .then(editor => {
          this.CKEditor = editor;
      })
      .catch(err => {
          console.error(err.stack);
      });
  }
}
</script>

<style>
.ck-content {
	height: 500px;
}
</style>