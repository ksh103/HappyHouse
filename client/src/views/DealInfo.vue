<template>
  <div>
    <BasicHeader name="거래가 조회" />
    <div id="wrapper">
      <div id="map" style="width: 100%; height: 1200px;"></div>
      <div id="searchBox" class="card">
        <div>
          <h6>검색 방법을 선택하세요</h6>
          <!-- <label class="form-label">With OptGroups</label> -->
          <div class="d-flex">
            <div class="form-check">
                <input value="D" v-model="searchType" class="form-check-input" type="radio" id="searchByDong">
                <label class="form-check-label" for="searchByDong">동 검색</label>
            </div>
            <div class="form-check">
                <input value="K" v-model="searchType" class="form-check-input" type="radio" id="searchByKeyword">
                <label class="form-check-label" for="searchByKeyword">키워드 검색</label>
            </div>
          </div>
          <div v-if="searchType == 'D'" class="dropdown mt-4">
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle"
                data-bs-toggle="dropdown">서울시</button>
            </div>
            <div class="btn-group">
              <button type="button" id="guBtn"
                class="btn btn-primary dropdown-toggle"
                data-bs-toggle="dropdown">{{ selectGuNme }}</button>
              <ul id="guMenu" class="dropdown-menu overflow-auto" style="max-height: 500px;">
                <li v-for="(item, index) in gu" :key="index" @click="onGuMenuChange($event, item.guCode)" class="guItem" data-guCode=${elem.guCode}><a class="dropdown-item" href="#">{{ item.guName }}</a></li>
              </ul>
            </div>
            <div class="btn-group">
              <button type="button" id="dongBtn"
                class="btn btn-primary dropdown-toggle"
                data-bs-toggle="dropdown">{{ selectDongName }}</button>
              <ul id="dongMenu" class="dropdown-menu overflow-auto" style="max-height: 500px;">
                <li v-for="(item, index) in dong" :key="index" @click="onDongMenuChange" class="guItem" data-guCode=${elem.guCode}><a class="dropdown-item" href="#">{{ item.dongName }}</a></li>
              </ul>
            </div>
          </div>
          <div v-if="searchType == 'K'" class="input-group w-75">
            <input id="inputSearchWord" type="text" class="form-control" placeholder="원하시는 아파트, 동명을 입력해주세요">
            <button id="btnSearchWord" class="btn btn-primary" type="button">검색</button>
          </div>
        </div>
        <div class="list-group">
          <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1">List group item heading</h5>
              <small>3 days ago</small>
            </div>
            <p class="mb-1">Some placeholder content in a paragraph.</p>
            <small>And some small print.</small>
          </a>
          <a href="#" class="list-group-item list-group-item-action">
            <div class="d-flex w-100 justify-content-between">
              <h5 class="mb-1">List group item heading</h5>
              <small class="text-muted">3 days ago</small>
            </div>
            <p class="mb-1">Some placeholder content in a paragraph.</p>
            <small class="text-muted">And some muted small print.</small>
          </a>
        </div>
        <button @click="toggleList" class="btn btn-primary" type="button">결과 리스트</button>
      </div>
      <div v-if="listVisible" id="showList" class="card p-0 bg-secondary">
        <div class="text-center text-white py-3"><h4>현재 매물 개수 : 0개</h4></div>
        <div class="text-center text-primary py-3 bg-warning" style="cursor: pointer;">
          <h4>현재 매물 개수 : 4개</h4>
          <h5>(보러 가기)</h5>
        </div>
        <!-- 아파트 정보 요약 -->
        <div class="bg-white mb-2">
          <div class="border-bottom"><h2 class="p-3 m-0">{{ houseList[curIndex].aptName }}</h2></div>
          <!-- contents -->
          <div class="px-3">
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">주소</div>
              <div>{{ selectDongName }} {{ houseList[curIndex].jiBun }}</div>
            </div>
            <div class="d-flex py-2">
              <div class="text-secondary w-25">건축년도</div>
              <div>{{ houseList[curIndex].buildYear}}</div>
            </div>
          </div>
        </div>
        <!-- 거주민 리뷰 -->
        <div class="bg-white mb-2">
          <div class="border-bottom"><h4 class="p-3 m-0">거주민 리뷰</h4></div>

          <!-- <div class="p-3">
            <div>등록된 리뷰가 없습니다.</div>
          </div> -->
          <!-- 등록 리뷰 있을 때 v-for 속성 추가-->
          <div>
            <div class="border-bottom d-flex align-items-center p-2">
              <div class="text-secondary ps-2 pe-3"><img class="avatar rounded-circle" width=25px src="../assets/images/profile_av.png"></div>
              <div class="d-flex flex-column">
                <h5 class="m-0">닉네임입니다</h5>
                <div class="text-secondary">2021-09-02 가입</div>
              </div>
            </div>
            <div class="px-3">
              <div class="border-bottom d-flex py-2 text-danger">
                <div class="w-25">추천점수</div>
                <div>
                  <StarRating v-model="trafficScore" active-color="#dc3545" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">교통요건</div>
                <div>
                  <StarRating v-model="trafficScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">거주환경</div>
                <div>
                  <StarRating v-model="trafficScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
                </div>
              </div>
              <div class="border-bottom d-flex py-2">
                <div class="text-secondary w-25">주변환경</div>
                <div>
                  <StarRating v-model="trafficScore" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>

                </div>
              </div>
              <div class="pt-2 text-secondary">종합의견</div>
              <div class="py-2">도산 공원이 가장 큰 장점이고요 인근 맛집. 예쁜 숍. 또한 핫플레이스 압구정 로데오. 가로수길이 인근에 있어 살기 좋아요.</div>
            </div>
          </div>
        </div>
        <!-- 실거래가 -->
        <div class="bg-white mb-2">
          <div class="border-bottom"><h4 class="p-3 m-0">실거래가</h4></div>
          <div>
            <table class="w-100">
              <thead class="bg-secondary text-white">
                <tr>
                  <td class="ps-3 py-2">거래일</td>
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
                <!-- <tr class="border-bottom">
                  <td class="ps-3 py-2">2222</td>
                  <td>2222</td>
                  <td>2222</td>
                  <td>2222</td>
                </tr> -->
              </tbody>
            </table>
          </div>
        </div>
        <!-- 매물정보 -->
        <div class="bg-white mb-2">
          <div class="border-bottom"><h4 class="p-3 m-0">매물 정보</h4></div>
          <div>
            <table class="w-100">
              <thead class="bg-secondary text-white">
                <tr>
                  <td class="ps-3 py-2">타입</td>
                  <td class="w-50">제목</td>
                  <td>거래가격</td>
                </tr>
              </thead>
              <tbody class="px-2">
                <tr class="border-bottom">
                  <td class="ps-3 py-2">2222</td>
                  <td>2222</td>
                  <td>2222</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/common/axios.js";
import BasicHeader from '@/components/layout/BasicHeader.vue';
import StarRating from 'vue-star-rating';

const storeName = 'dealInfoStore';

export default {
  name: 'DealInfo',
  components: {
    BasicHeader,
    StarRating
  },
  data() {
    return {
      map: '',
      searchType: 'D',
      selectGuNme: '동 선택',
      selectDongName: '구 선택',
      listVisible: false,
      markers: [],
      // curInfoWindow: {},
      infoWindows: [],
      curIndex: -1,
      dealInfo: [],
      trafficScore: 3
    }
  },
  computed: {
    ...mapState(storeName, ['gu', 'dong', 'houseList']),
  },
  created() {
    console.log('watch D');
    this.initSearchByDongBox();
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
      } else {
        alert('없습니다')
      }
    }
  },
  methods: {
    ...mapActions(storeName, ['getGu', 'getDong', 'getHouseListByDong']),
    initMap() {
      const mapContainer = document.getElementById('map');
      const mapOption = {
        center: new kakao.maps.LatLng(37.56666, 126.97800),
        level: 3,
      };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
    },
    initSearchByDongBox() {
      this.getGu(11);
    },
    onGuMenuChange(e, value) {
      this.getDong(value)
      this.selectGuNme = e.target.outerText
    },
    onDongMenuChange(e) {
      this.selectDongName = e.target.outerText;
      this.getHouseListByDong(e.target.outerText);
    },
    showHouseDetail(index) {
      this.curIndex = index;
      console.log(index)
      console.log(this.houseList[index]);
      this.getHouseDeal(this.houseList[index].houseNo);
      if (!this.listVisible) this.listVisible = true;
    },
    getHouseDeal(houseNo) {
      http.get(`/house/deal/${houseNo}`)
        .then(response => {
          this.dealInfo = response.data.houseDealDto;
        })
        .catch(error => {
          alert('서버에 에러 발생')
        })
    },
    toggleList() {
      this.listVisible = !this.listVisible;
      // this.addMarker(37.56666, 126.97800);
    },
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
        console.log(index)
        let item = this.houseList[index];
        let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`;

        let infoWindow = new kakao.maps.InfoWindow({
            content: infoContents
        });
        infoWindow.open(this.map, marker);
        // kakao.maps.event.addListener(marker, 'mouseover', function() {
        //   console.log(1)
        //   console.log(this.curInfoWindow)
        //   if (this.curInfoWindow) {
        //     if (this.curInfoWindow.name != item.aptName) {
        //       infoWindow.open(this.map, marker);
        //     }
        //   } else {
        //     console.log('ttt')
        //     infoWindow.open(this.map, marker);
        //   }
        // });
        // kakao.maps.event.addListener(marker, 'mouseout', function() {
        //   console.log(2)
        //   infoWindow.close();
        // });
      });
    },
    removeMarkers() {
      // if (this.curInfoWindow) {
      //   this.curInfoWindow.close();
      //   this.curInfoWindow.name = '';
      // }
      
      this.infoWindows.forEach(i => i.close());
      this.infoWindows = [];
      this.markers.forEach(m => m.setMap(null));
      this.markers = [];
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8abe79c672729ad9f23c1abc169bb162';
      document.head.appendChild(script);
    }
  },
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
    height: 400px;
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
</style>