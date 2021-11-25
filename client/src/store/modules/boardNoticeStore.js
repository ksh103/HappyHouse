import Vue from 'vue';
import http from "@/common/axios.js";
import router from '@/routers/routers.js'
import util from "@/common/util.js";

const boardNoticeStore = {
  namespaced: true,
  state: {
    list: [],
    limit: 10,
    offset: 0,

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

  getters: {
    getBoardList: function(state){
      return state.list;
    },
    // pagination
    getPageCount: function(state){
      return Math.ceil(state.totalListItemCount/state.listRowCount);
    },
    getStartPageIndex: function(state){
      if( (state.currentPageIndex % state.pageLinkCount) == 0 ){ //10, 20...맨마지막
        return ((state.currentPageIndex / state.pageLinkCount)-1)*state.pageLinkCount + 1
      }else{
        return  Math.floor(state.currentPageIndex / state.pageLinkCount)*state.pageLinkCount + 1
      }
    },
    getEndPageIndex: function(state, getters){
      let ret = 0;
      if( (state.currentPageIndex % state.pageLinkCount) == 0 ){ //10, 20...맨마지막
        ret = ((state.currentPageIndex / state.pageLinkCount)-1)*state.pageLinkCount + state.pageLinkCount;
      }else{
        ret = Math.floor(state.currentPageIndex / state.pageLinkCount)*state.pageLinkCount + state.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ( ret > getters.getPageCount ) ? getters.getPageCount : ret;
    },
    getPrev: function(state){
      if( state.currentPageIndex <= state.pageLinkCount ){
        return false;
      }else{
        return true;
      }
    },
    getNext: function(state, getters){
      if( ( Math.floor( getters.getPageCount / state.pageLinkCount ) * state.pageLinkCount ) < state.currentPageIndex){
        return false;
      }else{
        return true;
      }
    }
  },
  mutations: {
    SET_BOARD_LIST(state, list){
      state.list = list
    },
    SET_BOARD_TOTAL_LIST_ITEM_COUNT(state, count){
      state.totalListItemCount = count
    },
    SET_BOARD_MOVE_PAGE(state, pageIndex){
      state.offset = (pageIndex - 1) * state.listRowCount;
      state.currentPageIndex = pageIndex;
    },
    SET_BOARD_DETAIL(state, payload){
      state.noticeId = payload.noticeId
      state.title = payload.title;
      state.content = payload.content;
      state.userName = payload.userName;
      state.readCount = payload.readCount;
      state.regDt = payload.regDt;
      state.fileList = payload.fileList;
      state.sameUser = payload.sameUser;
    },
  },
  actions: {
    boardList({ commit, state }){
      http.get(
        "/notices",
        {
          params: {
            limit: state.limit,
            offset: state.offset,
            searchWord: ''
          }
        })
        .then(({ data }) => {
          commit( 'SET_BOARD_LIST', data.list );
          commit( 'SET_BOARD_TOTAL_LIST_ITEM_COUNT', data.count );
        })
        .catch((error) => {
          console.log("ListVue: error ");
          console.log(error);
          Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
        });
    },
    boardDetail({ commit }, noticeId){
      http.get(`/notices/${noticeId}`)
        .then(({ data }) => {
          console.log("DetailVue: data : ");
          console.log(data);

          if (data.dto.fileList) {
            console.log('exist')
            data.dto.fileList.forEach(file => {
              console.log(file)
            })
          } else {
            console.log('not')
          }

          commit( 'SET_BOARD_DETAIL', {
            ...data.dto,
            regDt: util.makeDateStr(data.dto.regDt.date.year, data.dto.regDt.date.month, data.dto.regDt.date.day, '.'),            
          });
          
          // commit( 'SET_BOARD_DETAIL',
          //   { 
          //     noticeId: data.dto.noticeId,
          //     title: data.dto.title,
          //     content: data.dto.content,
          //     userName: data.dto.userName,
          //     readCount : data.dto.readCount,
          //     regDt: util.makeDateStr(data.dto.regDt.date.year, data.dto.regDt.date.month, data.dto.regDt.date.day, '.'),
          //     fileList: data.dto.fileList,
          //     sameUser: data.dto.sameUser
          //   }
          // );

          router.push("/board/notice/detail");
        }
      )
      .catch((error) => {
        console.log("DetailVue: error ");
        console.log(error);
        Vue.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
      });
    },
  },
};

export default boardNoticeStore;