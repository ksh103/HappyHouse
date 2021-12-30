<template>
  <div class="container my-5">
    <div class="row g-2">
      <div class="col-lg-3 col-md-6">
        <div class="form-floating">
          <input @click="selectHouseNo" v-model="houseName" type="text" class="form-control cursor-pointer" placeholder="실거래가 *">
          <label>건물 정보 <span class="text-danger">*</span></label>
        </div>
      </div>
      <div class="col-lg-3 col-md-6"></div>
      <div class="col-lg-3 col-md-6"></div>
      <div class="col-lg-3 col-md-6"></div>
      <div class="col-lg-3 col-md-6">
          <div class="form-floating">
            <select v-model="type" class="form-select" aria-label="Floating label select example">
                <option selected>매매</option>
                <option>전세</option>
                <option>월세</option>
            </select>
            <label>거래 종류 <span class="text-danger">*</span></label>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="form-floating">
              <input v-model="dealAmount" type="text" class="form-control" placeholder="실거래가">
              <label>실거래가 <span class="text-danger">*</span></label>
            </div>
        </div>
          <div class="col-lg-3 col-md-6">
            <div class="form-floating">
                <input v-model="floor" type="text" class="form-control" placeholder="해당 층  *">
                <label>해당 층 <span class="text-danger">*</span></label>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="form-floating">
                <input v-model="area" type="text" class="form-control" placeholder="면적(평) *">
                <label>면적(평) <span class="text-danger">*</span></label>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="form-floating">
                <input v-model="direction" type="text" class="form-control" placeholder="방향 *">
                <label>방향 <span class="text-danger">*</span></label>
            </div>
        </div>
          <div class="col-lg-3 col-md-6">
            <div class="form-floating">
                <input v-model="fee" type="text" class="form-control" placeholder="관리비 *">
                <label>관리비 <span class="text-danger">*</span></label>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="form-floating">
                <input v-model="room" type="text" class="form-control" placeholder="방 *">
                <label>방 <span class="text-danger">*</span></label>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="form-floating">
                <input v-model="bathroom" type="text" class="form-control" placeholder="욕실 *">
                <label>욕실 <span class="text-danger">*</span></label>
            </div>
        </div>
        <div class="col-12">
            <div class="form-floating">
                <input v-model="title" type="text" class="form-control" placeholder="매물 특징 *">
                <label>매물 특징 <span class="text-danger">*</span></label>
            </div>
        </div>
        <div class="col-12">
          <div class="my-3">
            <label class="form-label ms-3 mb-3">매물 설명 <sup class="text-danger">*</sup></label>
            <div id=divEditorInsert></div>
          </div>
        </div>

        <!-- 파일 첨부 -->
        <div class="form-check mb-3 ms-2">
          <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert">
          <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
        </div>
        <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
          <input @change="changeFile" type="file" id="inputFileUploadInsert" class="form-control" multiple>
          <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper d-flex mt-3">
            <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index" class="mx-2" style="max-width: 200px; max-height: 200px;">
          </div>
        </div>
        <div class="col-12">
          <button @click.prevent="ongoingInsert" class="btn btn-primary float-end lift">작성완료</button>
          <router-link to="/house/ongoing/list" class="me-2 ml-3 btn btn-secondary float-end lift" >취소</router-link>
        </div>
			</div>
      <select-house-no-modal @modal-close="afterClose" />
  </div>
</template>

<script>
import Vue from 'vue';
import CKEditor from '@ckeditor/ckeditor5-vue2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import SelectHouseNoModal from './modal/SelectHouseNoModal.vue';
import { Modal } from 'bootstrap';

Vue.use(CKEditor);

import http from "@/common/axios.js";

export default {
  name: 'HouseOnGoingInsert',
  components: {
    SelectHouseNoModal
  },
  data(){
    return{
      houseNo: '',
      houseName: '',
      type: '매매',
      dealAmount: '',
      floor: '',
      area: '',
      direction: '',
      fee: '',
      room: '',
      bathroom: '',
      title: '',
      CKEditor: '',
      attachFile: false,
      fileList: [],
      selectHouseNoModal: null,
    }
  },
  methods: {
    afterClose(val) {
      if (val) {
        this.houseName = val.houseName;
        this.houseNo = val.houseNo;
      }
      this.selectHouseNoModal.hide();
    },
    selectHouseNo() {
      this.selectHouseNoModal.show();
    },
    changeFile(e) {
      if (e.target.files && e.target.files.length > 0){
        for (let i = 0; i < e.target.files.length; i++) {
          const file = e.target.files[i];
          this.fileList.push(URL.createObjectURL(file));
        }
      }
    },
    ongoingInsert(){
      let formData = new FormData();
      formData.append("houseNo", this.houseNo);
      formData.append("type", this.type);
      formData.append("dealAmount", this.dealAmount);
      formData.append("floor", this.floor);
      formData.append("area", this.area);
      formData.append("direction", this.direction);
      formData.append("fee", this.fee);
      formData.append("bathroom", this.bathroom);
      formData.append("title", this.title);
      formData.append("content", this.CKEditor.getData());

      // file upload
      let attachFiles = document.querySelector("#inputFileUploadInsert");
      let cnt = attachFiles.files.length;

      for (let i = 0; i < cnt; i++) {
        formData.append("file", attachFiles.files[i]);
      }

      http.post("/house/deal/ongoing/register", formData, {
        headers: { 'Content-Type': 'multipart/form-data' } 
      })
        .then(({ data }) => {
          if (data === "login") {
            this.$router.push('/user/login');
          } else{
            setTimeout(() => {
              this.$router.push('/house/ongoing/list');
            }, 1000);
          }
        })
        .catch(error => {
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
    this.selectHouseNoModal = new Modal(document.getElementById('selectHouseNoModal'));
  }
}
</script>

<style scope>
.ck-content {
	height: 350px;
}
</style>
