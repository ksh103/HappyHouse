<template>
  <div>
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner" style="background-color: rgb(54, 54, 54);">
        <div class="carousel-item active">
          <img src="../assets/images/mainHeaderImg.jpg" style="object-fit: cover" class="d-block w-100" height="10%" alt="header image">
          <div class="carousel-caption d-flex flex-column align-items-center">
            <div class="text-re mb-3">
            Happy House
            </div>
            <div class="input-group w-75 ">
                <input @keyup.enter="search" type="text" v-model="keyword" class="form-control form-control-lg"  placeholder="원하시는 아파트, 동명을 입력해주세요">
                <button @click="search" class="btn btn-warning" type="button">검색</button>
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
            <table class="myDataTable w-100 table align-middle table-bordered mb-0 custom-table nowrap dataTable">
              <tbody>
                  <tr class="cursor-pointer" @click="houseOngoingDetail(item.ongoingId)" v-for="(item, index) in latestTradeData" :key="index">
                      <td>{{ item.AptName }} / {{ item.type }} / {{ item.dealAmount }}</td>
                  </tr>
              </tbody>
            </table>
          </div>
          <div class="overflow-hidden col-lg-4 col-md-12">
            <h4>부동산 관련 뉴스</h4>
            <table class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable">
              <tbody>
                <tr v-for="(item, index) in news" :key="index">
                  <td class="text-nowrap"><a class="news-link" :href="item.link" target="_blank" v-html="item.title"></a></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="col-lg-4 col-md-12">
            <h4>공지사항</h4>
            <table class="myDataTable table align-middle table-bordered mb-0 custom-table nowrap dataTable">
              <tbody>
                <tr class="cursor-pointer" @click="noticeBoardDetail(item.noticeId)" v-for="(item, index) in noticeList" :key="index">
                  <td>{{ item.title }}</td>
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
import { mapActions, mapMutations } from 'vuex';

export default {
  name: 'Main',
  data() {
    return {
      latestTradeData: [],
      news: [],
      noticeList: [],
      keyword: '',
    }
  },
  methods: {
    ...mapActions('houseOnGoingStore', ['onGoingDetail']),
    ...mapActions('boardNoticeStore', ['boardDetail']),
    ...mapMutations('dealInfoStore', ['SET_KEYWORD']),
    search() {
      if (this.keyword == '') {
        this.$swal('키워드를 입력하세요.', { icon: 'warning' });
        return;
      }
      this.SET_KEYWORD(this.keyword);
      this.$router.push('/dealInfo');
    },
    houseOngoingDetail(id) {
      this.onGoingDetail(id);
    },
    noticeBoardDetail(id) {
      this.boardDetail(id);
    }
  },
  created() {
    http.get( "/info/news")
      .then(({ data }) => {
        this.news = data.newsDto;
      })
      .catch(error => this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' }));

    http.get("/notices/latest")
      .then(({ data }) => {
        this.noticeList = data.list;
      })
      .catch((error) => {
        // console.log("Main: error ");
        // console.log(error);
        this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
      });

    http.get(
      "/house/deal/ongoing/latest")
      .then(({ data }) => {
        this.latestTradeData = data.list;
      })
      .catch((error) => {
        // console.log("Main: error ");
        // console.log(error);
        this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
      });
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Merriweather:wght@900&display=swap');
  .carousel-item {
    height: 500px;
  }

  .carousel-item img {
    position: absolute;
    top: 0;
    left: 0;
    min-height: 500px;
  }

  .text-re{
    color: rgb(221, 221, 221);
    text-shadow: 1px 1px 2px black, 0 0 25px blue, 0 0 5px darkblue;
    /* text-shadow: 3px 3px 3px rgb(223, 223, 223); */
    font-size : 80px;
    word-spacing: -25px;
    letter-spacing: -5px;
    font-family: 'Merriweather', serif;
  }
  .news-link{
    text-decoration: none;
    color: #333;
    
  }
</style>