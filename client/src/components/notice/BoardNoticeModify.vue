<template>
<div class="container my-5">
  <form class="row g-3 basic-form">
    <div class="col-md-12">
      <label class="form-label ms-3">제목 <sup class="text-danger">*</sup></label>
      <input v-model="title" type="text" class="form-control" required="">
    </div>
    <div class="col-12">
      <div class="my-3">
        <label class="form-label ms-3">내용 <sup class="text-danger">*</sup></label>
        <div id=divEditorUpdate></div>
      </div>
      <div v-if="storeFileList.length > 0" class="mb-3">
        첨부파일 : <span><div v-for="(file, index) in storeFileList" class="fileName" :key="index">{{file.fileName}}</div></span>
      </div>
      <div class="form-check mb-3">
        <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadUpdate">
        <label class="form-check-label" for="chkFileUploadUpdate">파일 추가</label>
      </div>
      <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
        <input @change="changeFile" type="file" id="inputFileUploadUpdate" class="form-control" multiple>
        <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
          <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" class="m-2" style="max-width: 200px; max-height: 200px;">
        </div>
      </div>
    </div>
    <div class="col-12">
      <button @click.prevent="boardUpdate" class="btn btn-primary float-end">수정완료</button>
      <router-link to="/board/notice" class="me-2 btn btn-secondary float-end">취소</router-link>
    </div>
  </form>
</div>
</template>

<script>
import Vue from 'vue';
import { mapActions, mapState, mapMutations } from 'vuex';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

Vue.use(CKEditor);

import http from "@/common/axios.js";

const storeName = 'boardNoticeStore';

export default {
  name: 'BoardNoticeModify',

  data() {
    return {
      title: '',
      CKEditor: '',
      attachFile: false,
      fileList: []
    };
  },
  computed: {
    ...mapState(storeName, {
      storeTitle: 'title',
      storeBoardId: 'noticeId',
      storeContent: 'content',
      storeFileList: 'fileList'
    }),
  },
  methods: {
    ...mapActions(storeName, ['boardDetail']),
    ...mapMutations(storeName, ['SET_BOARD_TITLE']),
    changeFile(e) {
      if (e.target.files && e.target.files.length > 0){
        for (let i = 0; i < e.target.files.length; i++) {
          const file = e.target.files[i];
          this.fileList.push(URL.createObjectURL(file));
        }
      }
    },
    boardUpdate(){
      let formData = new FormData();
      formData.append("boardId", this.storeBoardId);
      formData.append("title", this.title);
      formData.append("content", this.CKEditor.getData());
      
      let attachFiles = document.querySelector("#inputFileUploadUpdate");
      let cnt = attachFiles.files.length;

      for (let i = 0; i < cnt; i++) {
        formData.append("file", attachFiles.files[i]);
      }
      
      http.post(`/notices/${this.storeBoardId}`,
        formData,
        { headers: { 'Content-Type': 'multipart/form-data' } })
        .then(({ data }) => {
          if (data.result == 'login'){
            this.$router.push("/login");
          } else {
            this.boardDetail(this.storeBoardId);
          }
        })
        .catch(error => {
          console.log("noticeModifyVue: error ");
          console.log(error);
          this.$swal('서버에 문제가 발생하였습니다.' , { icon: 'error' });
        })
    },
  },
  mounted() {
    ClassicEditor
      .create(document.querySelector('#divEditorUpdate'))
      .then(editor => {
        editor.setData(this.storeContent);
        this.CKEditor = editor;
      })
      .catch(err => {
          console.error(err.stack);
      });
    this.title = this.storeTitle;
  }
}
</script>

<style>

</style>