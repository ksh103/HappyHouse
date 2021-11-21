<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">북마크</h1>
          <div class="card">
            <div class="card-body">
              <table class="table">
                <thead>
                  <tr class="fw-bold">
                    <td>아파트(주택)명</td>
                    <td>주소</td>
                    <td>건축년도</td>
                    <td>삭제</td>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(item, index) in test" :key="index">
                    <td>
                      <h6 class="mb-0">주공아파트</h6>
                    </td>
                    <td class="amount">부산시 부산진구 부전동 112-189, 1층 유플러스</td>
                    <td class="action">1995</td>
                  </tr>
                  <tr>
                    <td colspan="4">등록된 데이터가 없습니다</td>
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
export default {
  name: 'Management',
  data() {
    return {
      test: [1,1,1,1,1]
    }
  }
}
</script>

<style>

</style>