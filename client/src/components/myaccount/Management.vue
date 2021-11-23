<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">북마크</h1>
          <div class="card">
            <div class="card-body">
              <table v-if="houseOngoingList" class="table">
                <thead>
                  <tr class="fw-bold">
                    <td>유형</td>
                    <td>아파트(주택)명</td>
                    <td>방</td>
                    <td>층수</td>
                    <td>삭제</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-if="houseOngoingList.length==0">
                    <td colspan="5">등록된 데이터가 없습니다</td>
                  </tr>
                  <tr v-else v-for="(item, index) in houseOngoingList" :key="index">
                    <td>{{ item.type }}</td>
                    <td>
                      <h6 class="mb-0">{{ item.title }}</h6>
                    </td>
                    <td class="amount">{{ item.room }}</td>
                    <td class="action">{{ item.floor }}</td>
                    <td class="action"><button @click="deleteOngoingItem(item.ongoingId)"></button></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-12">
          <h1 class="h3 m-4">관심 건물 (아파트/주택)</h1>
          <div class="card">
            <div class="card-body">
              <table v-if="houseList" class="table">
                <thead>
                  <tr class="fw-bold">
                    <td>아파트(주택)명</td>
                    <td>주소</td>
                    <td>건축년도</td>
                    <td>삭제</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-if="houseList.length==0">
                    <td colspan="4">등록된 데이터가 없습니다</td>
                  </tr>
                  <tr v-else v-for="(item, index) in houseList" :key="index">
                    <td>
                      <h6 class="mb-0">{{ item.aptName }}</h6>
                    </td>
                    <td class="amount">{{ item.dongName }}  {{ item.jiBun }}</td>
                    <td class="action">{{ item.buildYear }}</td>
                    <td><button @click="deleteHouseItem(item.houseNo)"></button></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-12">
          <h1 class="h3 m-4">리뷰 관리</h1>
          <div class="card">
            <div class="card-body">
              <table class="table">
                <thead>
                  <tr class="fw-bold">
                    <td>아파트(주택)명</td>
                    <td>주소</td>
                    <td>건축년도</td>
                    <!-- <td>수정</td> -->
                    <td>삭제</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in test" :key="index">
                    <td>
                      <h6 class="mb-0">주공아파트</h6>
                    </td>
                    <td class="amount">부산시 부산진구 부전동 112-189, 1층 유플러스</td>
                    <td>2000</td>
                    <td>
                      <button style="font-size: 14px;" class="btn btn-danger px-2 py-1 lift">삭제</button>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="4">등록된 데이터가 없습니다</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/common/axios.js';

export default {
  name: 'Management',
  data() {
    return {
      test: [1,1,1,1,1],
      houseList: null,
      houseOngoingList: null,
    }
  },
  created() {
    this.bookmarkList();
  },
  methods: {
    bookmarkList() {
      http.get('/bookmark/user')
        .then(({ data }) => {
          console.log(data);
          this.houseList = data.houseList;
          this.houseOngoingList = data.houseOngoingList;
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
    },
    reviewList() {
      http.get('/bookmark/user')
        .then(({ data }) => {

        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
    },
    deleteOngoingItem(ongoingId) {
      this.$swal({
        title: '삭제하시겠습니까?',
        icon: 'warning',
        dangerMode: true,
        buttons: true
      }).then(value => {
        if (value) {
          http.delete(`/bookmark/houseongoing/${ongoingId}`)
            .then(({ data }) => {
              if (data.result == 'login') {
                this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
                  .then(() => {
                    this.SET_USER_LOGOUT();
                    this.$router.push('/user/login');
                    // 리렌더링!!
                  })
              }
              this.bookmarkList();
            })
            .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
            }
          })
    },
    deleteHouseItem(houseNo) {
      this.$swal({
        title: '삭제하시겠습니까?',
        icon: 'warning',
        dangerMode: true,
        buttons: true
      }).then(value => {
        if (value) {
          http.delete(`/bookmark/house/${houseNo}`)
            .then(({ data }) => {
              if (data.result == 'login') {
                this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
                  .then(() => {
                    this.SET_USER_LOGOUT();
                    this.$router.push('/user/login');
                  })
              }
              this.bookmarkList();
            })
          .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
        }
      })
    }
  }
}
</script>

<style>

</style>