<template>
   <div class="container my-5">
    <div class="row">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h4 class="mb-3">{{ title }}</h4>
            <div class="border-top">
              이미지
            </div>
            <div class="border-top">
              <h4 class="my-3">[{{ AptName }}] {{ dealAmount }} {{ type }}</h4>
              
            </div>
            <div class="border-top d-flex py-2">
              <div class="d-flex flex-column d-inline-block w-20">
                <h5>면적</h5>
                <span>{{ area }}</span>
              </div>
              <div class="d-flex flex-column d-inline-block w-20">
                <h5>층</h5>
                <span>{{ floor }}</span>
              </div>
              <div class="d-flex flex-column d-inline-block w-20">
                <h5>관리비</h5>
                <span>{{ fee }}</span>
              </div>
              <div class="d-flex flex-column d-inline-block w-20">
                <h5>방향</h5>
                <span>{{ direction }}</span>
              </div>
              <div class="d-flex flex-column d-inline-block w-20">
                <h5>방/화장실</h5>
                <span>{{ room }} / {{ bathroom }}</span>
              </div>
            </div>
            <div class="border-top py-2 content" v-html="content"></div>
            <!-- <h5 style="white-space: pre-line"></h5> -->
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <ul class="list-group">
          <li class="list-group-item d-flex justify-content-between align-items-center">
            글번호 :
            <span>{{ ongoingId }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            작성자 :
            <span>{{ compName }}</span>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-center">
            오시는 길 : 
            <span>{{ compAddress }}</span>
          </li>
          <li v-for="(file, index) in fileList" :key="index" class="list-group-item">
            <a type="button" class="p-0 m-0 btn btn-outline btn-default btn-xs" v-bind:href="file.fileUrl" v-bind:download="file.fileName">
              <img style="width: 20px;" src="../../assets/images/download_icon.png">
              <span class="ms-2 fileName">{{ file.fileName }}</span>
            </a>
          </li>
        </ul>
      </div>
      <div class="footer">
        <router-link class="btn btn-sm btn-primary" to="/house/ongoing/card">목록으로</router-link>
      </div>
    </div>
  </div>
</template>

<script>

import { mapState } from 'vuex';

const storeName = 'houseOnGoingStore';

export default {
  name: 'HouseOnGoingDetail',
  computed: {
    ...mapState(storeName, ['ongoingId', 'title', 'AptName', 'fileList', 'type', 'dealAmount', 'area', 'floor', 'fee', 'direction', 'room', 'bathroom', 'content', 'compName', 'compAddress']),
  }
}
</script>

<style scoped>
.fileName:hover {
    color: #0a58ca;
}
.w-20 {
  width: 20%;
}
.content {
  min-height: 200px;
}
</style>