<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h4 class="mb-3">{{ title }}</h4>
            <div v-if="fileList.length" class="border-top">
              <img class="max-length" v-for="(file, index) in fileList" :key="index" :src="file.fileUrl" alt="매물 이미지">
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
        <router-link class="btn btn-sm btn-primary mt-2" to="/house/ongoing/card">목록</router-link>
        <button @click="deleteOngoingDetail(ongoingId)" class="btn btn-sm btn-danger float-end ms-2 mt-2">삭제</button>
        <router-link class="btn btn-sm btn-primary float-end mt-2" to="/house/ongoing/modify">수정</router-link>
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
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/common/axios.js';
import { mapState } from 'vuex';

const storeName = 'houseOnGoingStore';

export default {
  name: 'HouseOnGoingDetail',
  computed: {
    ...mapState(storeName, ['sameUser', 'ongoingId', 'title', 'AptName', 'fileList', 'type', 'dealAmount', 'area', 'floor', 'fee', 'direction', 'room', 'bathroom', 'content', 'compName', 'compAddress']),
  },
  methods: {
    deleteOngoingDetail(ongoingId){
      this.$swal({
        title: '삭제하시겠습니까?',
        icon: 'warning',
        dangerMode: true,
        buttons: true
      }).then(value => {
        if (value) {
          http.delete(
            `/house/deal/ongoing/${ongoingId}`
          )
            .then(({ data }) => {
              console.log('DeleteVue: data :');
              console.log(data);
              // 현재 route를 /list로 변경.
              this.$router.push('/house/ongoing/card');
            })
            .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
        }
      })
    }
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