<template>
  <div>
    <div id="wrapper">
      <div id="map" style="width: 100%; height: 90vh;"></div>
      <div id="searchBox" class="card">
        <div>
          <div class="p-2">
            <div class="d-flex">
              <i class="fa fa-search"></i>
              <h6 class="ps-2">검색 방법을 선택하세요 {{heart}}</h6>
            </div>
            <div class="py-1 px-2 d-flex">
              <div class="form-check pe-3">
                  <input value="D" v-model="searchType" class="form-check-input" type="radio" id="searchByDong">
                  <label class="form-check-label" for="searchByDong">동 검색</label>
              </div>
              <div class="form-check">
                  <input value="K" v-model="searchType" class="form-check-input" type="radio" id="searchByKeyword">
                  <label class="form-check-label" for="searchByKeyword">키워드 검색{{ listVisible }}</label>
              </div>
            </div>
          </div>
          <div v-if="searchType == 'D'" class="pb-2 d-flex justify-content-evenly">
            <div class="btn-group">
              <button type="button" class="btn btn-primary">서울시</button>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <fieldset>
                  <select v-model="selectGuName" @change="onGuMenuChange" class="array-select form-control form-select" aria-label="example">
                      <option value="default" selected>구 선택</option>
                      <option v-for="(item, index) in gu" :key="index" :value="item.guCode">{{ item.guName }}</option>
                  </select>
              </fieldset>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <fieldset>
                  <select v-model="selectDongName" @change="onDongMenuChange" class="array-select form-control form-select" aria-label="example">
                      <option value="default" selected>동 선택</option>
                      <option v-for="(item, index) in dong" :key="index" :value="item.dongName">{{ item.dongName }}</option>
                  </select>
              </fieldset>
            </div>
          </div>
          <div v-if="searchType == 'K'" class="input-group w-75 d-flex pb-2">
            <input type="text" v-model="inputKeyword" class="form-control d-inline-block" placeholder="원하시는 아파트, 동명을 입력해주세요">
            <button @click="onKeywordSearch" class="btn btn-primary d-inline-block" type="button"><i class="bi bi-search"></i></button>
          </div>
        </div>
      </div>
      <div v-if="listVisible" id="showList" class="card p-0 bg-secondary">
        <div class="text-center text-white py-2"><h5>현재 매물 개수 : 0개</h5></div>
        <div class="text-center text-primary py-2 bg-warning" style="cursor: pointer;">
          <h5>현재 매물 개수 : 4개</h5>
          <h5>(보러 가기)</h5>
        </div>
        <!-- 아파트 정보 요약 -->
        <div class="bg-white mb-2">
          <div class="p-3 border-bottom d-flex justify-content-between align-items-center">
            <h4 class="m-0">{{ houseList[curIndex].aptName }}</h4>
            <HeartBtn class="px-1" :enabled="houseList[curIndex].bookmark" v-if="isAuth" @changeHeartBtn="onBookmarkHouse" />
          </div>
          <!-- contents -->
          <div class="px-3">
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">주소</div>
              <div>{{ houseList[curIndex].dongName }} {{ houseList[curIndex].jiBun }}</div>
            </div>
            <div class="d-flex py-2">
              <div class="text-secondary w-25">건축년도</div>
              <div>{{ houseList[curIndex].buildYear}}</div>
            </div>
          </div>
        </div>
        <!-- 거주민 리뷰 -->
        <div class="bg-white mb-2">
          <div class="d-flex justify-content-between align-items-center">
            <h5 class="p-3 m-0">거주민 리뷰</h5>
            <!-- <button @click="showReviewInsertModal" style="font-size: 14px;" class="btn px-2 py-1 btn-animate-2 fill">리뷰 남기기</button> -->
            <i @click="showReviewInsertModal" class="bi bi-plus-circle px-3 cursor-pointer"></i>
          </div>

          <div v-if="reviewList.length==0" class="p-3 border-top">
            <div>등록된 리뷰가 없습니다.</div>
          </div>
          <!-- 등록 리뷰 있을 때 v-for 속성 추가-->
          <div v-else v-for="(review, index) in reviewList" :key="index">
            <div class="border-top border-bottom d-flex align-items-center p-2">
              <div class="text-secondary ps-2 pe-3"><img class="avatar rounded-circle" width=25px src="../assets/images/profile_av.png"></div>
              <div class="d-flex flex-column">
                <h6 class="m-0">{{ review.userName }}</h6>
                <div class="text-secondary" style="font-size: 0.9rem;">{{ review.regDt }} 가입</div>
              </div>
            </div>
            <div class="px-3">
              <div class="border-bottom d-flex py-2 text-danger">
                <div class="w-25">추천점수</div>
                <div>
                  <StarRating v-model="review.recommendScore" active-color="#dc3545" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">교통요건</div>
                <div>
                  <StarRating v-model="review.trafficScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">거주환경</div>
                <div>
                  <StarRating v-model="review.livingScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">주변환경</div>
                <div>
                  <StarRating v-model="review.surroundingScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="pt-2 text-secondary">종합의견</div>
              <div class="py-2"><h6>{{ review.content }}</h6></div>
            </div>
          </div>
        </div> 
        <!-- 실거래가 -->
        <div class="bg-white mb-2">
          <div class="border-bottom"><h5 class="p-3 m-0">실거래가</h5></div>
          <div>
            <table class="w-100">
              <thead class="bg-secondary text-white">
                <tr>
                  <td class="ps-3 py-1">거래일</td>
                  <td>거래가격</td>
                  <td>면적</td>
                  <td>층수</td>
                </tr>
              </thead>
              <tbody class="px-2">
                <tr v-for="(item, index) in dealInfo" :key="index" class="border-bottom">
                  <td class="ps-3 py-2">{{item.dealYear}}</td>
                  <td>{{item.dealAmount}}</td>
                  <td>{{item.area}}</td>
                  <td>{{item.floor}}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!-- 매물정보 -->
        <div class="bg-white mb-2">
          <div class="border-bottom"><h5 class="p-3 m-0">매물 정보</h5></div>
          <div>
            <table class="w-100">
              <thead class="bg-secondary text-white">
                <tr>
                  <td class="ps-3 py-1">타입</td>
                  <td class="w-50">제목</td>
                  <td>거래가격</td>
                  <td></td>
                </tr>
              </thead>
              <tbody class="px-2">
                <tr v-if="ongoingList.length==0" class="border-bottom">
                  <td colspan="3" class="ps-3 py-2">등록된 매물이 없습니다.</td>
                </tr>
                <tr v-else v-for="(item, index) in ongoingList" :key="index" class="border-bottom">
                  <td class="ps-3 py-2">{{ item.type }}</td>
                  <td>{{ item.title }}</td>
                  <td>{{ item.dealAmount }}</td>
                  <td><HeartBtn :enabled="item.bookmark" :index="index" @changeHeartBtn="onBookmarkOngoing" /></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <review-insert-modal v-if="listVisible" v-on:parent-modal-close="reviewInsertModalClose" :index="curIndex"/>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/common/axios.js";
import StarRating from 'vue-star-rating';
import HeartBtn from '@/components/icon/HeartBtn.vue';
import ReviewInsertModal from '@/components/housedeal/ReviewInsertModal.vue';
import { Modal } from 'bootstrap';

const storeName = 'dealInfoStore';

export default {
  name: 'DealInfo',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    StarRating,
    HeartBtn,
    ReviewInsertModal
  },
  data() {
    return {
      map: '',
      searchType: 'D',
      selectGuName: 'default',
      selectDongName: 'default',
      inputKeyword: '',
      listVisible: false,
      markers: [],
      eventFrom: '',
      // curInfoWindow: {},
      infoWindows: [],
      curInfoWindow: null,
      curIndex: -1,
      dealInfo: [],
      reviewList: [],
      ongoingList: [],
      heart: true,
      reviewInsertModal: null,

      prevRoute: null,
    }
  },
  beforeRouteEnter (to, from, next) {
    next(vm => {
      vm.prevRoute = from
    })
  },
  computed: {
    ...mapState(storeName, ['gu', 'dong', 'houseList', 'fromMainKeyword']),
    ...mapState('userStore', ['isAuth']),
  },
  created() {
    this.initSearchByDongBox();
    
    if (this.fromMainKeyword) {
      console.log('yes')
      this.inputKeyword = this.fromMainKeyword;
      this.CLEAR_KEYWORD();
      this.searchType = 'K';
      this.onKeywordSearch();
    }
  },
  watch: {
    searchType: function(val) {
      this.listVisible = false;
      if (val === 'D') {
        console.log('watch D');
        this.initSearchByDongBox();
      } else if (val === 'K') {
        if (this.listVisible) this.listVisible = false;
        console.log('watch K');
      }
    },
    houseList: function() {
      console.log('houseList change');
      this.removeMarkers();
      this.listVisible = false;
      if (this.houseList.length) {
        this.addMarkers(this.houseList);
        console.log(this.houseList)
        this.$swal(`총 ${this.houseList.length}건의 데이터가 검색되었습니다.`, { icon: 'success'});
      } else {
        if (this.eventFrom === 'dong')
          this.$swal(`${this.selectDongName}에 등록된 건물 정보가 없습니다.`, { icon: 'error'});
        else if (this.eventFrom === 'keyword')
          this.$swal(`키워드 '${this.inputKeyword}'에 대한 정보가 없습니다.`, { icon: 'error'});
      }
    },
  },
  methods: {
    ...mapActions(storeName, ['getGu', 'getDong', 'getHouseListByDong', 'getHouseListByKeyword']),
    ...mapMutations(storeName, ['CLEAR_KEYWORD']),
    ...mapMutations('userStore', ['SET_USER_LOGOUT']),
    initMap() {
      const mapContainer = document.getElementById('map');
      const mapOption = {
        center: new kakao.maps.LatLng(37.56666, 126.97800),
        level: 3,
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
    },
    onBookmarkOngoing({ index, enabled }) {
      this.ongoingList[index].bookmark = enabled;
      if (enabled) {
        this.insertOngoingBookmark(this.ongoingList[index].ongoingId);
      } else {
        this.removeOngoingBookmark(this.ongoingList[index].ongoingId);
      }
    },
    onBookmarkHouse({ enabled }) {
      // 정상처리 된다는 가정... 비동기 then 안에 넣는게 더 정확할듯
      this.houseList[this.curIndex].bookmark = enabled;
      
      if (enabled) {
        http.post('/bookmark/house', {
          houseNo: this.houseList[this.curIndex].houseNo
        })
        .then(({ data }) => {
          if (data.result == 'login') {
            this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
              .then(() => {
                this.SET_USER_LOGOUT();
                this.$router.push('/user/login');
              })
          }
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
      } else {
        http.delete(`/bookmark/house/${this.houseList[this.curIndex].houseNo}`)
          .then(({ data }) => {
            if (data.result == 'login') {
              this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
                .then(() => {
                  this.SET_USER_LOGOUT();
                  this.$router.push('/user/login');
                })
            }
          })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
        
      }
    },
    insertOngoingBookmark(ongoingId) {
      http.post(`/bookmark/houseongoing`, {
        ongoingId
      })
        .then(({ data }) => {
          if (data.result == 'login') {
            this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
              .then(() => {
                this.SET_USER_LOGOUT();
                this.$router.push('/user/login');
              })
          }
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
    },
    removeOngoingBookmark(ongoingId) {
      http.delete(`/bookmark/houseongoing/${ongoingId}`)
        .then(({ data }) => {
          if (data.result == 'login') {
            this.$swal('세션이 만료되었거나, 로그인되지 않았습니다. 로그인 페이지로 이동합니다.', { icon: 'warning' })
              .then(() => {
                this.SET_USER_LOGOUT();
                this.$router.push('/user/login');
              })
          }
        })
        .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }))
    },
    initSearchByDongBox() {
      this.getGu(11);
    },
    onGuMenuChange() {
      if (this.selectGuName !== 'default') {
        this.selectDongName = 'default';
        this.getDong(this.selectGuName);
      }
    },
    onDongMenuChange() {
      if (this.selectDongName !== 'default') {
        this.eventFrom = 'dong';
        this.getHouseListByDong(this.selectDongName);
      }
    },
    onKeywordSearch() {
      if (this.inputKeyword == '') {
        this.$swal('키워드를 입력하세요.', { icon: 'error' });
      } else {
        this.eventFrom = 'keyword';
        this.getHouseListByKeyword(this.inputKeyword);
      }
    },
    showHouseDetail(index) {
      this.curIndex = index;
      const houseNo = this.houseList[index].houseNo;
      this.getHouseDeal(houseNo);
      this.getOngoingList(houseNo);
      this.getHouseReview(houseNo);
      if (!this.listVisible) this.listVisible = true;
    },
    getHouseDeal(houseNo) {
      http.get(`/house/deal/${houseNo}`)
        .then(response => {
          this.dealInfo = response.data.houseDealDto;
        })
        .catch(error => {
          this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    getHouseReview(houseNo) {
      http.get(`/house/review/${houseNo}`)
        .then(({ data }) => {
          this.reviewList = data.list;
        })
        .catch(error => {
          this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    getOngoingList(houseNo) {
      console.log('ongoing!')
      http.get(`/house/deal/ongoing/list/${houseNo}`)
        .then(({ data }) => {
          this.ongoingList = data.list;
          console.log(data)
        })
        .catch(error => {
          this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    toggleList() {
      this.listVisible = !this.listVisible;
      // this.addMarker(37.56666, 126.97800);
    },

    // Modal 관련 메소드
    showReviewInsertModal() {
      this.reviewInsertModal.show();
    },
    reviewInsertModalClose() {
      this.reviewInsertModal.hide();
    },

    // 지도 관련 메소드
    addMarkerByOne(markerPosition, index) {
      let marker = new kakao.maps.Marker({
        position: markerPosition
      });
      let $this = this;
      kakao.maps.event.addListener(marker, 'click', function() {
        $this.showHouseDetail(index);
      });
      this.markers.push(marker);
      marker.setMap(this.map);
    },
    addMarker(lat, lng) {
      let markerPosition  = new kakao.maps.LatLng(lat, lng); 
      let marker = new kakao.maps.Marker({
        position: markerPosition
      });
      this.markers.push(marker);
      marker.setMap(this.map);
    },
    addMarkers(list) {
      let bounds = new kakao.maps.LatLngBounds();
      
      list.forEach(({ lat, lng }, index) => {
        console.log(`forEach ${index}`)
        let markerPosition  = new kakao.maps.LatLng(lat, lng); 
        this.addMarkerByOne(markerPosition, index);
        bounds.extend(markerPosition);
      });
      this.addInfoWindow();
      this.map.setBounds(bounds);
    },
    addInfoWindow() {
      console.log('addiw')
      this.markers.forEach((marker, index) => {
        let item = this.houseList[index];
        let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`;

        let infoWindow = new kakao.maps.InfoWindow({
            content: infoContents
        });
        // infoWindow.open(this.map, marker);
        // this.infoWindows.push(infoWindow);
        let $this = this;
        kakao.maps.event.addListener(marker, 'mouseover', function() {
          // console.log(1)
          // console.log(this.curInfoWindow)
          // if ($this.curInfoWindow) {
          //   if ($this.curInfoWindow.name != item.aptName) {
          //     $this.curInfoWindow.close();
          //     infoWindow.open(this.map, marker);
          //   }
          // } else {
          //   console.log('ttt')
            infoWindow.open($this.map, marker);
            // $this.curInfoWindow = infoWindow;
          }
        );
        kakao.maps.event.addListener(marker, 'mouseout', function() {
          infoWindow.close();
        });
      });
    },
    removeMarkers() {
      // if (this.curInfoWindow) {
      //   this.curInfoWindow.close();
      //   this.curInfoWindow = null;
      // }
      
      // this.infoWindows.forEach(i => i.close());
      // this.infoWindows = [];
      this.markers.forEach(m => m.setMap(null));
      this.markers = [];
    },
  },
  mounted() {
    // kakao map 초기화
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8abe79c672729ad9f23c1abc169bb162';
      document.head.appendChild(script);
    }

    console.log(this.prevRoute)
    console.log(this.prevRoute.name);
    // Modal 초기화
    // this.reviewInsertModal = new Modal(document.getElementById('reviewInsertModal'));
  },
  updated() {
    console.log('updated')
    if (this.listVisible) this.reviewInsertModal = new Modal(document.getElementById('reviewInsertModal'));
  }
}
</script>

<style>
  #wrapper {
    position: relative;
  }
  #searchBox {
    position: absolute;

    top: 20px;
    /* bottom : 10px; */
    left: 20px;

    width: 400px;
    /* height: 400px; */
    padding: 10px;

    z-index: 100;
    /* background-color:rgba(255, 244, 244, 0.8); */
    /* opacity: 0.5; */
    
    overflow-y: auto;
  }
  #showList {
    position: absolute;

    top: 20px;
    bottom : 20px;
    right: 20px;

    width: 400px;
    padding: 10px;

    z-index: 100;
    /* background-color:rgba(255, 244, 244, 0.8); */
    /* opacity: 0.5; */
    
    overflow-y: auto;
  }
  .bi-plus-circle {
    font-size: 1.5rem;
  }
  .bi-plus-circle:hover {
    color: dodgerblue;
  }
</style>