<template>
  <div>
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner" style="background-color: rgb(54, 54, 54);">
        <div class="carousel-item active">
          <img src="../assets/images/mainHeaderImg.jpg" style="object-fit: cover" class="d-block w-100" height="10%" alt="header image">
          <div class="carousel-caption d-flex flex-column align-items-center d-md-block text-shadow">
            <div>
            <h1>Happy House</h1>

            </div>
            <div class="input-group w-75">
              <input id="inputSearchWord" type="text" class="form-control form-control-lg" placeholder="원하시는 아파트, 동명을 입력해주세요">
              <button id="btnSearchWord" class="btn btn-warning" type="button">검색</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Main Content -->
    <div class="body_area py-4 py-md-5">
      <div class="container">
        <div class="row g-3 row-deck">
          <div class="col-lg-3 col-md-6 col-sm-6 text-center">
						<div class="card border-0">
							<div class="card-body">
								<h3>25</h3>
								<span>Active Projects</span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-6 text-center">
						<div class="card border-0">
							<div class="card-body">
								<h3>40</h3>
								<span>Today Tasks</span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-6 text-center">
						<div class="card border-0">
							<div class="card-body">
								<h3>05</h3>
								<span>Today Expenses</span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-6 col-sm-6 text-center">
						<div class="card border-0">
							<div class="card-body">
								<h3>03</h3>
								<span>Today Invoices</span>
							</div>
						</div>
					</div>
        </div>
        <div class="row g-3 mt-4">
          <div class="col-lg-4 col-md-12">
            <h4>최근 매물</h4>
            <table class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable" style="width: 100%;">
              <tbody>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
              </tbody>
            </table>
          </div>
          <div class="col-lg-4 col-md-12">
            <h4>부동산 관련 뉴스</h4>
            <table class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable" style="width: 100%;">
              <tbody>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
              </tbody>
            </table>
          </div>
          <div class="col-lg-4 col-md-12">
            <h4>공지사항</h4>
            <table class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable" style="width: 100%;">
              <tbody>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
                  </tr>
                  <tr>
                      <td>Ava Alexander</td>
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
import http from '@/common/axios.js'
// import Header from '@/components/Header.vue';

export default {
  name: 'Main',
  data() {
    return {
      latestTradeData: { },
      news: { },
      notice: { }
    }
  },
  components: {
    // Header,
  },
  created() {
    http.get( "/info/news")
      .then(({ data }) => {
        this.news = data.newsDto;
      })
  }
}
</script>

<style>
.carousel-item {
  height: 500px;
}

.carousel-item img {
  position: absolute;
  top: 0;
  left: 0;
  min-height: 500px;
}

.text-shadow {
  color: white;
  text-shadow: 1px 1px 2px black, 0 0 25px blue, 0 0 5px darkblue;
}

</style>