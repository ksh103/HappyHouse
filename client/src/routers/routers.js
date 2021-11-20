import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import Main from '@/views/Main.vue';
import User from '@/views/User.vue';
import BoardNotice from '@/views/BoardNotice.vue';
import FindPassword from '@/components/user/FindPassword.vue';
import Login from '@/components/user/Login.vue';
import Join from '@/components/user/Join.vue';
import ModifyInfo from '@/components/user/ModifyInfo.vue';
import BoardNoticeList from '@/components/notice/BoardNoticeList.vue';
import BoardNoticeInsert from '@/components/notice/BoardNoticeInsert.vue';
import BoardNoticeModify from '@/components/notice/BoardNoticeModify.vue';
import BoardNoticeDetail from '@/components/notice/BoardNoticeDetail.vue';
import DealInfo from '@/views/DealInfo.vue';

export default new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: Main
    },
    {
      path: '/dealInfo',
      component: DealInfo
    },
    {
      path: '/board/notice',
      name: 'BoardNotice',
      component: BoardNotice,
      redirect: '/board/notice/list',
      children:[
        {
          path: 'list',
          name: 'BoardNoticeList',
          component: BoardNoticeList,
        },
        {
          path: 'insert',
          name: 'BoardNoticeInsert',
          component: BoardNoticeInsert,
        },
        {
          path: 'modify',
          name: 'BoardNoticeModify',
          component: BoardNoticeModify,
        },
        {
          path: 'detail',
          name: 'BoardNoticeDetail',
          component: BoardNoticeDetail,
        },
      ]
    },
    {
      path: '/user',
      name: 'User',
      component: User,
      children: [
        {
          path: 'login',
          name: 'Login',
          component: Login
        },
        {
          path: 'password',
          name: 'FindPassword',
          component: FindPassword
        },       
        {
          path: 'join',
          name: 'Join',
          component: Join
        },
        {
          path: 'modify',
          name: 'ModifyInfo',
          component: ModifyInfo
        },
      ]
    },
    // {
    //   name: 'User',
    //   path: '/user',
    //   component: User
    // },

    // {
    //   name: 'Register',
    //   path: '/register',
    //   component: Register
    // },
  ],
});
