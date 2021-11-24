<template>
  <div class="container my-5">
    <div class="row"> <!--v-for="(card, index) in getOnGoingCard" v-bind:key="index"-->
      <div class="col-md-3 mb-5" v-for="(item, index) in getOnGoingCard" v-bind:key="index">
        <div class="card h-100" style="width: 18rem;">
          <img v-if="!item.fileList" src="../../assets/images/apt.jpg" class="card-img-top">
          <img v-else :src="item.fileList[0].fileUrl" class="card-img-top" >
          <div class="card-body">
            <div class="card-body">
              <div class="col-md-3 mb-3"> <span class="badge bg-success">{{ item.ongoingId }}</span> </div>
              <div class="col-mb-3"> <h4 class="card-title">{{ item.AptName }}</h4> </div>
              <div class="col-mt-3"> <h6 class="card-text">{{ item.compName }}</h6> </div>
              <p class="mb-4">{{ item.title }}</p>
              <!-- <p v-if="!item.fileList" class="card-text">없음</p> -->
              <!-- <p v-else class="card-text">{{ item.fileList[0].fileUrl }}</p> -->
            </div>
            <button class="btn btn-block btn-secondary lift fs-6 text-uppercase" @click="onGoingDetail(item.ongoingId)"> 매물 보기 </button>
            <!-- <a @click="onGoingDetail(item.ongoingId)" class="btn btn-block btn-secondary lift fs-6 text-uppercase">매물 보기</a>  -->
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
import { mapActions, mapGetters, mapMutations } from 'vuex';

export default {
  name: 'HouseOnGoingCard',
  components: {
    Pagination
  },
  computed :{
    ...mapGetters('houseOnGoingStore', ['getOnGoingCard']),
  },
  methods : {
    ...mapActions('houseOnGoingStore', ['onGoingCard', 'onGoingDetail']),
    ...mapMutations('houseOnGoingStore', ['SET_BOARD_MOVE_PAGE']),

    // pagination
    movePage(pageIndex){
      console.log("HouseOnGoingVue : movePage : pageIndex : " + pageIndex );
      this.SET_BOARD_MOVE_PAGE(pageIndex);
      this.onGoingCard();
      console.log(this.$store._modules.root.state.houseOnGoingStore)
    },
    
    makeDateStr : util.makeDateStr,

  },
  created() {
    this.onGoingCard();
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