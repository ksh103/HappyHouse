<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-md-3 my-2" v-for="(card, index) in getOnGoingCard" v-bind:key="index">
        <div class="card h-100" style="width: 18rem;">
          <img src="../../assets/images/apt.jpg" class="card-img-top">
            <div class="card-body">
              <span class="badge bg-success">{{ card.ongoingId }}</span>
              <h4 class="card-title">{{ card.AptName }}</h4>
              <h6 class="card-text">{{ card.compName }}</h6>
              <p class="card-text">{{ card.title }}</p>
              <a @click="onGoingDetail(card.ongoingId)" class="flex-grow-1 btn btn-block btn-secondary lift fs-6 text-uppercase">매물 보기</a>
            </div>
        </div>
       </div>
    </div>
    <pagination class="mt-3" v-on:call-parent="movePage"></pagination>
    <router-link class="btn mx-2 btn-primary btn-animate-1" to="/house/ongoing/insert">
      <span> 매물 등록 </span>
      <i class="fa fa-long-arrow-right"></i>
    </router-link>
  </div>

</template>
<script>
import Pagination from '@/components/Pagination.vue';

import util from "@/common/util.js";
import { mapActions, mapGetters, mapMutations } from 'vuex';

export default {
  name: 'houseOnGoingStore',
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
      // this.$store.commit( 'SET_BOARD_MOVE_PAGE', pageIndex );
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
.card {
  width: 250px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>>
