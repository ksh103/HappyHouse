import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';
import http from "@/common/axios.js";
import router from '@/routers/routers.js'
import VueAlertify from 'vue-alertify';

Vue.use(Vuex);
Vue.use(VueAlertify);

// const SUCCESS = 1;

export default new Vuex.Store({
  state: {
    // 유저 정보
    user: {
      isAuth: false,
      seq: 0,
      id: '',
      level: 0,
      password: '',
      name: '',
      email: '',
    },
    // 공지사항 게시판 정보
    board: {
      list: [],
      limit: 10,
      offset: 0,
      searchWord: '',

      // pagination
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      totalListItemCount: 0,

      // detail, update, delete
      noticeId: 0,
      title: '',
      content: '',
      userName: '',
      regDt: {},
      readCount: 0,
      fileList: [],
      sameUser: false

    },
  },
  mutations: {
    SET_USER(state, payload) {
      state.user.isAuth = true;
      state.user = payload;
    },
    SET_USER_LOGOUT(state){
      state.user.isAuth = false;
      state.user.seq = 0,
      state.user.id = '',
      state.user.level = 0,
      state.user.password = '',
      state.user.name = '',
      state.user.email = ''
    },
    SET_USER_MODIFY(state, payload) {
      state.user.name = payload.name;
      state.user.password = payload.password;
      state.user.email = payload.email;
    },
    SET_BOARD_LIST(state, list){
      state.board.list = list
    },
    SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count){
      state.board.totalListItemCount = count
    },
    SET_BOARD_MOVE_PAGE(state, pageIndex){
      state.board.offset = (pageIndex - 1) * state.board.listRowCount;
      state.board.currentPageIndex = pageIndex;
    },
    SET_BOARD_DETAIL(state, payload){
      state.board.noticeId = payload.noticeId
      state.board.title = payload.title;
      state.board.content = payload.content;
      state.board.userName = payload.userName;
      state.board.readCount = payload.readCount;
      state.board.regDt = payload.regDt;
      state.board.fileList = payload.fileList;
      state.board.sameUser = payload.sameUser;
    },
  },
  actions: {
    login(context, { userId, userPassword }) {
      http.post(
        "/user/login",
        {
          userId,
          userPassword
        }
      )
      .then((response) => {
        console.log("LoginVue: data : ");
        console.log(response.data);

        const { 
          dto: {
            userId: id,
            userLevel: level,
            userSeq: seq,
            userName: name,
            userPassword: password,
            userEmail: email,
          }
        } = response.data;
        
          context.commit('SET_USER', { isAuth: true, seq, id, level, password, name, email });

          router.push("/")
        })
        .catch( error => {
          console.log("LoginVue: error : ");
          console.log(error);

          if (error.response.status == '404'){
            alert('아이디 또는 비밀번호를 확인하세요.')
            // this.$alertify.error('이메일 또는 비밀번호를 확인하세요.');
          } else {
            alert('Opps!! 서버에 문제가 발생했습니다.');
          }
        });
      },
    logout(context){
      http.get('/user/logout')
      .then(() => {
        context.commit("SET_USER_LOGOUT");
        router.push('/');
        }).catch(error => {
          console.log(error)
          alert('Opps!! 서버에 문제가 발생했습니다.');
          //this.$alertify.error('서버에서 문제 발생했습니다');
        })
    },
    boardList(context){
      http.get(
        "/notices",
        {
          params: {
            limit: this.state.board.limit,
            offset: this.state.board.offset,
            searchWord: this.state.board.searchWord
          }
        })
        .then(({ data }) => {
          console.log("BoardMainVue: data : ");
          console.log(data);
          if( data.result == 'login' ){
            router.push("/user/login")
          }else{
            context.commit( 'SET_BOARD_LIST', data.list );
            context.commit( 'SET_BOARD_TOTAL_LIST_ITEM_COUNT', data.count );
          }
      });
    },
  },
  modules: {},
  getters: {
    isAuth: function(state){
      return state.user.isAuth;
    },
    getBoardList: function(state){
      return state.board.list;
    },
    // pagination
    getPageCount: function(state){
      return Math.ceil(state.board.totalListItemCount/state.board.listRowCount);
    },
    getStartPageIndex: function(state){
      if( (state.board.currentPageIndex % state.board.pageLinkCount) == 0 ){ //10, 20...맨마지막
        return ((state.board.currentPageIndex / state.board.pageLinkCount)-1)*state.board.pageLinkCount + 1
      }else{
        return  Math.floor(state.board.currentPageIndex / state.board.pageLinkCount)*state.board.pageLinkCount + 1
      }
    },
    getEndPageIndex: function(state, getters){
      let ret = 0;
      if( (state.board.currentPageIndex % state.board.pageLinkCount) == 0 ){ //10, 20...맨마지막
        ret = ((state.board.currentPageIndex / state.board.pageLinkCount)-1)*state.board.pageLinkCount + state.board.pageLinkCount;
      }else{
        ret = Math.floor(state.board.currentPageIndex / state.board.pageLinkCount)*state.board.pageLinkCount + state.board.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ( ret > getters.getPageCount ) ? getters.getPageCount : ret;
    },
    getPrev: function(state){
      if( state.board.currentPageIndex <= state.board.pageLinkCount ){
        return false;
      }else{
        return true;
      }
    },
    getNext: function(getters){
      if( getters.getEndPageIndex >= getters.getPageCount){
        return false;
      }else{
        return true;
      }
    }
  },
  plugins: [
    createPersistedState()
  ]
});