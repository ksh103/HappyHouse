<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">관심 매물</h1>
          <div class="card">
            <div class="card-body">
              <table v-if="houseOngoingList" class="table text-center">
                <thead>
                  <tr class="fw-bold">
                    <td>유형</td>
                    <td>아파트 · 주택명</td>
                    <td>거래금액</td>
                    <td>방향</td>
                    <td>면적</td>
                    <td>방</td>
                    <td>층수</td>
                    <td>화장실</td>
                    <td>삭제</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-if="houseOngoingList.length==0">
                    <td colspan="9">등록된 데이터가 없습니다</td>
                  </tr>
                  <tr v-else v-for="(item, index) in houseOngoingList" :key="index">
                    <td>{{ item.type }}</td>
                    <td>
                      <h6 class="mb-0">{{ item.title }}</h6>
                    </td>
                    <td>{{ item.dealAmount }}</td>
                    <td>{{ item.direction }}</td>
                    <td>{{ item.area }}</td>
                    <td>{{ item.room }}</td>
                    <td>{{ item.floor }}</td>
                    <td class="action">{{ item.bathroom }}</td>
                    <td class="action"><i @click="deleteOngoingItem(item.ongoingId)" class="cursor-pointer bi bi-trash-fill"></i></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-12">
          <h1 class="h3 m-4">관심 건물 정보</h1>
          <div class="card">
            <div class="card-body">
              <table v-if="houseList" class="table text-center">
                <thead>
                  <tr class="fw-bold">
                    <td>아파트 · 주택명</td>
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
                    <td class="text-start">
                      <h6 class="mb-0">{{ item.aptName }}</h6>
                    </td>
                    <td class="amount">{{ item.dongName }}  {{ item.jiBun }}</td>
                    <td class="action">{{ item.buildYear }}</td>
                    <td><i @click="deleteHouseItem(item.houseNo)" class="cursor-pointer bi bi-trash-fill"></i></td>
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
              <table v-if="myReviewList" class="table text-center">
                <thead>
                  <tr class="fw-bold">
                    <td>건물명</td>
                    <td>추천점수</td>
                    <td>교통요건</td>
                    <td>거주환경</td>
                    <td>주변환경</td>
                    <td>종합의견</td>
                    <td class="text-break">삭제</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-if="myReviewList.length==0">
                    <td colspan="7">등록한 리뷰가 없습니다</td>
                  </tr>
                  <tr v-else v-for="(item, index) in myReviewList" :key="index">
                    <td>
                      <h6 class="mb-0 text-start">{{ item.aptName }}</h6>
                    </td>
                    <td>
                      <StarRating v-model="item.recommendScore" active-color="#dc3545" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                    </td>
                    <td>
                      <StarRating v-model="item.trafficScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                    </td>
                    <td>
                      <StarRating v-model="item.livingScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                    </td>
                    <td>
                      <StarRating v-model="item.surroundingScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                    </td>
                    <td class="text-start">{{ item.content }}</td>
                    <td>
                      <i @click="deleteReviewItem(item.reviewId)" class="cursor-pointer bi bi-trash-fill"></i>
                    </td>
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
import { mapMutations } from 'vuex';
import http from '@/common/axios.js';
import StarRating from 'vue-star-rating';

export default {
  name: 'Management',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    StarRating,
  },
  data() {
    return {
      test: [1,1,1,1,1],
      houseList: null,
      houseOngoingList: null,
      myReviewList: null,
    }
  },
  created() {
    this.bookmarkList();
    this.reviewList();
  },
  methods: {
    ...mapMutations('userStore', ['SET_USER_LOGOUT']),
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
      http.get('/house/review/user')
        .then(({ data }) => {
          console.log(data);
          this.myReviewList = data.list;
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
    },
    deleteReviewItem(reviewId) {
      this.$swal({
        title: `삭제하시겠습니까? ${reviewId}`,
        icon: 'warning',
        dangerMode: true,
        buttons: true
      }).then(value => {
        if (value) {
          http.delete(`/house/review/${reviewId}`)
            .then(({ data }) => {
              if (data.result == 'login') {
                this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
                  .then(() => {
                    this.SET_USER_LOGOUT();
                    this.$router.push('/user/login');
                  })
              }
              this.reviewList();
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