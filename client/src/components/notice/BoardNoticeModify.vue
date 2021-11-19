<template>
  <div class="container">
    <Header name="공지사항 수정" desc="Welcome back to your dashboard, if need a help Contact us." />
    <div class="mb-3">
    <input v-model="$store.state.board.title" type="text" class="form-control" placeholder="제목">
    </div>
    <div class="col-md-12">
        <label class="form-label">글 작성</label>
        <textarea v-model="content" class="form-control" rows="5" cols="30" required></textarea>
    </div>
    <div class="mb-3" v-show="attachFile" id="imgFileUploadUpdateWrapper">
      <input @change="changeFile" type="file" id="inputFileUploadUpdate" multiple>
      <div id="imgFileUploadUpdateThumbnail" class="thumbnail-wrapper">
        <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index">
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import VueAlertify from 'vue-alertify'; 

import Header from '@/components/Header.vue';

Vue.use(CKEditor).use(VueAlertify);

export default {
  name: 'BoardNoticeUpdate',
  components: {Header},
  data() {
    return {
      CKEditor: '',
      attachFile: false,
      fileList: [], 
    }
  },
  mounted() {      
    ClassicEditor
    .create(document.querySelector('#divEditorUpdate'))
    .then(editor => {
        this.CKEditor = editor;
    })
    .catch(err => {
        console.error(err.stack);
    });
  },
}
</script>

<style>

</style>