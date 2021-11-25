<template>
  <div class="container my-5">
    <div class="row">
      <div class="form-check pe-3">
          <input value="전체" v-model="dealType" class="form-check-input" type="radio" id="all">
          <label class="form-check-label" for="all">전체</label>
      </div>
      <div class="form-check">
          <input value="매매" v-model="dealType" class="form-check-input" type="radio">
          <label class="form-check-label">매매</label>
      </div>
      <div class="form-check">
          <input value="전세" v-model="dealType" class="form-check-input" type="radio">
          <label class="form-check-label">전세</label>
      </div>
      <div class="form-check">
          <input value="월세" v-model="dealType" class="form-check-input" type="radio">
          <label class="form-check-label">월세</label>
      </div>
      {{ dealType }}
      <select v-model="keywordType" class="array-select form-control form-select" aria-label="example">
        <option value="all">제목+내용</option>
        <option value="houseName">건물명</option>
        <option value="compName">업체명</option>
        <option value="area">면적</option>
      </select>
      <input type="text" v-model="keyword" class="form-control d-inline-block" placeholder="원하시는 아파트, 동명을 입력해주세요">
    </div>
      <button type="text" @click="test">aaaa</button>
    <div class="row"> <!--v-for="(card, index) in getOnGoingCard" v-bind:key="index"-->
      <div class="col-md-3 mb-5" v-for="(item, index) in getOnGoingCard" v-bind:key="index">
        <div class="card h-100" style="width: 18rem;">
          <div @click="onGoingDetail(item.ongoingId)" class="cursor-pointer hover-show">
            <img v-if="!item.fileList" src="../../assets/images/apt.jpg" class="card-img-top">
            <img v-else :src="item.fileList[0].fileUrl" class="card-img-top" >
          </div>
          <div class="card-body">
            <div class="col-mb-2"> <h4 class="card-title">{{ item.AptName }}</h4> </div>
            <div class="col-mt-2"> <h6 class="card-text">{{ item.compName }}</h6> </div>
            <p class="mt-2 mb-0">{{ item.title }}</p>
            <!-- <p v-if="!item.fileList" class="card-text">없음</p> -->
            <!-- <p v-else class="card-text">{{ item.fileList[0].fileUrl }}</p> -->
          </div>
        </div>
      </div>
      <pagination class="mt-3" v-on:call-parent="movePage"></pagination>
      <router-link class="btn mx-2 px-4 py-2 btn-outline-primary btn-animate-3" to="/house/ongoing/insert">매물 등록</router-link>
    </div>
  </div>
</template>
<script>
import Pagination from './Pagination.vue';

import util from "@/common/util.js";
import { mapActions, mapState, mapGetters, mapMutations } from 'vuex';

const storeName = 'houseOnGoingStore';

export default {
  name: 'HouseOnGoingCard',
  data() {
    return {
      keyword: '',
      dealType: 'all',
      keywordType: 'all',
    }
  },
  watch: {
    dealType(val) {
      console.log("watch dealType !!!!!!!!!!!!!!!")
      this.SET_DT(val);
      this.onGoingCard();
    }
  },
  components: {
    Pagination,
  },
  computed: {
    ...mapState(storeName, ['searchKeyword', 'searchKeywordType', 'searchDealType']),
    ...mapGetters(storeName, ['getOnGoingCard']),
    // keyword: {
    //   get() {
    //     return this.$store.state.houseOnGoingStore.searchKeyword;
    //     // return this.searchKeyword;
    //   },
    //   set(value) {
    //     this.$store.commit('SET_SK', value);
    //     // this.SET_SK(value);
    //   }
    // },
    // keywordType: {
    //   get() {
    //     return this.$store.state.houseOnGoingStore.searchKeywordType;
    //     // return this.keywordType;
    //   },
    //   set(value) {
    //     this.$store.commit('SET_SKT', value);
    //     // this.SET_SKT(value);
    //   }
    // },
    // houseType: {
    //   get() {
    //     return this.$store.state.houseOnGoingStore.searchHouseType;
    //     // return this.houseType;
    //   },
    //   set(value) {
    //     this.$store.commit('SET_HT', value);
    //     // this.SET_HT(value);
    //   }
    // }
  },
  methods : {
    ...mapActions(storeName, ['onGoingCard', 'onGoingDetail']),
    ...mapMutations(storeName, ['SET_BOARD_MOVE_PAGE', 'SET_K', 'SET_KT', 'SET_DT']),
    onRadioChange() {
      console.log(this.dealType);
      this.SET_DT(this.dealType);
    },
    test() {
      console.log(this.$store);
      console.log(this.$store.state.houseOnGoingStore);
      this.SET_K(this.keyword);
      this.SET_KT(this.keywordType);
      // api 호출
      this.onGoingCard();

    },
    // pagination
    movePage(pageIndex){
      console.log("HouseOnGoingVue : movePage : pageIndex : " + pageIndex );
      this.SET_BOARD_MOVE_PAGE(pageIndex);
      this.onGoingCard();
      console.log(this.$store._modules.root.state.houseOnGoingStore)
      console.log(this.$store._modules.root.context.commit);
    },
    
    makeDateStr : util.makeDateStr,

  },
  created() {
    console.log("created list!!!!!!!!!!!!!!")
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
  button.btn {
    margin-top: auto;
  }
</style>