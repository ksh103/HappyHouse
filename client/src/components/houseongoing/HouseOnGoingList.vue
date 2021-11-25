<template>
  <div class="container my-5">
    <!-- 검색 / 카테고리 선택 영역 -->
    <div class="row mb-5">
      <div class="col">
        <ul class="nav nav-tabs tab-body-header rounded d-inline-flex">
          <li class="nav-item">
            <input value="전체" v-model="dealType" class="d-none" type="radio" id="all">
            <label :class="[searchDealType=='전체' ? 'active' : '']" class="nav-link cursor-pointer" for="all">전체</label>
          </li>
          <li class="nav-item">
            <input value="매매" v-model="dealType" class="d-none" type="radio" id="lease">
            <label :class="[searchDealType=='매매' ? 'active' : '']" class="nav-link cursor-pointer" for="lease">매매</label>
          </li>
          <li class="nav-item">
            <input value="전세" v-model="dealType" class="d-none" type="radio" id="sell">
            <label :class="[searchDealType=='전세' ? 'active' : '']" class="nav-link cursor-pointer" for="sell">전세</label>
          </li>
          <li class="nav-item">
            <input value="월세" v-model="dealType" class="d-none" type="radio" id="month">
            <label :class="[searchDealType=='월세' ? 'active' : '']" class="nav-link cursor-pointer" for="month">월세</label>
          </li>
        </ul>
      </div>
      <div class="col d-flex justify-content-end align-items-center">
        <div style="width: 20%;">
          <select v-model="keywordType" class="form-control form-select" aria-label="example">
            <option value="all">제목+내용</option>
            <option value="houseName">건물명</option>
            <option value="compName">업체명</option>
            <option value="area">면적</option>
          </select>
        </div>
        <div class="ms-3 input-group d-flex w-50">
          <input @enter="search" type="text" v-model="keyword" class="form-control d-inline-block" placeholder="키워드를 입력해주세요">
          <button @click="search" class="btn btn-primary d-inline-block" type="button"><i class="bi bi-search"></i></button>
        </div>
      </div>
    </div>
    <!-- 게시글 리스트 -->
    <div class="row">
      <div class="col-md-3 mb-5" v-for="(item, index) in getOnGoingCard" v-bind:key="index">
        <div class="card h-100">
          <div @click="onGoingDetail(item.ongoingId)" class="cursor-pointer hover-show position-relative">
            <img v-if="!item.fileList" src="http://localhost:8080/images/aptDefault.jpg" class="card-img-top">
            <img v-else :src="item.fileList[0].fileUrl" class="card-img-top">
            <span v-if="item.type=='월세'" class="position-absolute badge bg-success"><h5 class="m-0">{{ item.type }}</h5></span>
            <span v-if="item.type=='전세'" class="position-absolute badge bg-primary"><h5 class="m-0">{{ item.type }}</h5></span>
            <span v-if="item.type=='매매'" class="position-absolute badge bg-warning"><h5 class="m-0">{{ item.type }}</h5></span>
          </div>
          <div class="card-body">
            <div class="col-mb-2">
              <h4 class="card-title">{{ item.AptName }}</h4>
            </div>
            <div class="col-mt-2"> <h6 class="card-text">{{ item.compName }}</h6> </div>
            <p class="mt-2 mb-0">{{ item.title }}</p>
          </div>
        </div>
      </div>
      <pagination class="mt-3" v-on:call-parent="movePage"></pagination>
    </div>
    <div v-if="isAuth&&level==3" class="my-2 d-flex justify-content-end">
      <router-link class="btn btn-primary lift" to="/house/ongoing/insert">매물 등록</router-link>
    </div>
  </div>
</template>
<script>
import Pagination from './Pagination.vue';
import util from "@/common/util.js";
import { mapActions, mapState, mapGetters, mapMutations } from 'vuex';

const storeName = 'houseOnGoingStore';

export default {
  name: 'HouseOnGoingList',
  data() {
    return {
      keyword: '',
      dealType: '전체',
      keywordType: 'all'
    }
  },
  components: {
    Pagination,
  },
  watch: {
    dealType(val) {
      this.SET_DT(val);
      this.onGoingCard();
    }
  },
  computed: {
    ...mapState('userStore', ['isAuth', 'level']),
    ...mapState(storeName, ['searchKeyword', 'searchKeywordType', 'searchDealType']),
    ...mapGetters(storeName, ['getOnGoingCard']),
  },
  methods : {
    ...mapActions(storeName, ['onGoingCard', 'onGoingDetail', 'setBookmark']),
    ...mapMutations(storeName, ['SET_BOARD_MOVE_PAGE', 'SET_K', 'SET_KT', 'SET_DT', 'SET_BOOKMARK']),
    search() {
      this.SET_K({
        keyword: this.keyword,
        keywordType: this.keywordType
      });
      // api 호출
      this.onGoingCard();

    },
    movePage(pageIndex){
      this.SET_BOARD_MOVE_PAGE(pageIndex);
      this.onGoingCard();
    },
    
    makeDateStr : util.makeDateStr,
  },
  created() {
    // console.log("created list!!!!!!!!!!!!!!")
    this.onGoingCard();
    this.keyword = this.searchKeyword;
    this.keywordType = this.searchKeywordType;
    this.dealType = this.searchDealType;
  },
  mounted() {},
}
</script>

<style scoped>
  .card-img-top {
    display: inline-block;
    width: 100%;
    height: 300px;
    overflow: hidden;
    object-fit: cover;
    border-radius: 5px;
  }
  .card{
    margin-bottom: 10px;
  }
  .card-body {
    display: flex;
    flex-direction: column;
    
  }
  .position-absolute {
    left: 5%;
    top: 5%;
  }
</style>