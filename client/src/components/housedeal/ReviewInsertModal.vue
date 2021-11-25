<template>
  <div class="modal" tabindex="-1" id="reviewInsertModal">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <i class="bi bi-card-text ms-2 me-2" style="font-size: 1.5rem;"></i>
          <h5 class="modal-title">리뷰 등록</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <div class="border-bottom d-flex pb-2">
              <h5>{{ houseList[index].aptName }}</h5>
            </div>
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">교통요건</div>
              <div>
                <StarRating v-model="trafficScore" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
              </div>
            </div>
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">거주환경</div>
              <div>
                <StarRating v-model="livingScore" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
              </div>
            </div>
            <div class="border-bottom d-flex py-2">
              <div class="text-secondary w-25">주변환경</div>
              <div>
                <StarRating v-model="surroundingScore" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>

              </div>
            </div>
            <div class="border-bottom d-flex py-2 text-danger">
              <div class="w-25">추천점수</div>
              <div>
                <StarRating v-model="recommendScore" active-color="#dc3545" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
              </div>
            </div>
          </div>
          <div class="pt-2 form-group">
            <label class="form-label">종합의견</label>
            <textarea @keyup.enter="insertReview" v-model="content" class="form-control" rows="4" placeholder="사용자에게 도움이 되는 의견을 남겨주세요~!" aria-label="With textarea"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button @click="closeModal" class="btn btn-sm btn-secondary btn-outline" data-dismiss="modal" type="button">취소</button>
          <button @click="insertReview" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/common/axios.js";
import StarRating from 'vue-star-rating';

const storeName = 'dealInfoStore';

export default {
  name: 'ReviewInsertModal',
  props: ['buildingName', 'index'],
  data() {
    return {
      trafficScore: 0,
      surroundingScore: 0,
      livingScore: 0,
      recommendScore: 0,
      content: '',
    }
  },
  components: {
    StarRating
  },
  methods: {
    insertReview() {
      const houseNo = this.houseList[this.index].houseNo;

      http.post('/house/review', {
        userSeq: 2,
        houseNo, 
        trafficScore: this.trafficScore,
        surroundingScore: this.surroundingScore,
        recommendScore: this.recommendScore,
        livingScore: this.livingScore,
        content: this.content,
      })
        .then(() => {
          this.$swal('리뷰가 등록되었습니다.', '참여해 주셔서 감사합니다.', { icon: 'success' })
              .then(() => this.closeModal());
        })
        .catch(error => {
              console.log(error);
              this.$swal('서버에 문제가 발생하였습니다.' , { icon: 'error' });
        })
    },
    initModal() {
      this.trafficScore = 0;
      this.surroundingScore = 0
      this.livingScore = 0;
      this.recommendScore = 0;
      this.content = '';
    },
    closeModal() {
      this.$emit('parent-modal-close', this.houseList[this.index].houseNo);
    }
  },
  computed: {
    ...mapState(storeName, ['houseList']),
  },
  mounted() {
    let $this = this;
    this.$el.addEventListener('show.bs.modal', function () {
      $this.initModal();
    })
  }
}
</script>

<style>

</style>