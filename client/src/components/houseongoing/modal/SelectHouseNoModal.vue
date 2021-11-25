<template>
  <div class="modal" tabindex="-1" id="selectHouseNoModal">
    <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <i class="bi bi-building ms-2 me-2" style="font-size: 1.5rem;"></i>
          <h5 class="modal-title">건물 선택</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <h6 class="mx-2 mb-3">건물명 또는 동을 입력하세요.</h6>
            <div class="input-group pb-3">
              <input @keyup.enter="onBtnClick" type="text" v-model="inputKeyword" class="form-control d-inline-block" placeholder="건물명 / 동">
              <button @click="onBtnClick" class="btn btn-primary d-inline-block" type="button"><i class="bi bi-search"></i></button>
            </div>
          </div>
          <ul v-if="houseList" class="list-unstyled list-group list-group-custom list-group-flush mb-0">
            <li v-for="(house, index) in houseList" :key="index" class="list-group-item d-flex align-items-center py-3">
                <div class="flex-grow-1">
                    <h6 class="mb-0">{{ house.aptName }}</h6>
                    <small>{{ house.dongName }} {{ house.jiBun }}</small>
                </div>
                <i @click="selectHouse(house.houseNo, house.aptName)" class="bi bi-check-circle cursor-pointer" style="font-size: 2rem;"></i>
            </li>
            <li v-if="houseList.length==0" class="list-group-item d-flex align-items-center py-3">
              <a class="d-flex align-items-center text-decoration-none">
                데이터가 존재하지 않습니다.
              </a>
            </li>
          </ul>
        </div>
        <div class="modal-footer">
          <button @click="closeModal" class="btn btn-sm btn-secondary btn-outline" data-dismiss="modal" type="button">취소</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";

export default {
  name: 'SelectHouseNoModal',
  data() {
    return {
      inputKeyword: '',
      houseList: null
    }
  },
  methods: {
    onBtnClick() {
      // validataion
      this.searchHouse();
    },
    searchHouse() {
      http.get(`/house/detail/keyword/${this.inputKeyword}`)
        .then(({ data }) => {
          console.log(data);
          if (data.houseDetailDto) {
            this.houseList = data.houseDetailDto;
          }
        })
        .catch(error => {
          console.log(error)
          this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        })
    },
    selectHouse(houseNo, houseName) {
      this.$emit('modal-close', {
        houseNo, houseName
      })
    },
    initModal() {
      this.inputKeyword = '';
      this.houseList = null;
    },
    closeModal() {
      this.$emit('modal-close');
    }
  },
  mounted() {
    console.log('modal mounted!!');
    let $this = this;
    this.$el.addEventListener('show.bs.modal', function () {
      $this.initModal();
    })
  }
}
</script>

<style scoped>
  .rounded-circle {
    width: 50px !important;
    height: 50px !important;
  }
</style>