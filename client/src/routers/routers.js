import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import Main from '@/views/Main.vue';
import User from '@/views/User.vue';
import BoardNotice from '@/views/BoardNotice.vue';
import DealInfo from '@/views/DealInfo.vue';
import MyAccount from '@/views/MyAccount.vue';
import FindPassword from '@/components/user/FindPassword.vue';
import Login from '@/components/user/Login.vue';
import Join from '@/components/user/Join.vue';
import BoardNoticeList from '@/components/notice/BoardNoticeList.vue';
import BoardNoticeInsert from '@/components/notice/BoardNoticeInsert.vue';
import BoardNoticeModify from '@/components/notice/BoardNoticeModify.vue';
import BoardNoticeDetail from '@/components/notice/BoardNoticeDetail.vue';
import Profile from '@/components/myaccount/Profile.vue';
import Management from '@/components/myaccount/Management.vue';
import Friends from '@/components/myaccount/Friends.vue';
import ModifyInfo from '@/components/myaccount/ModifyInfo.vue';
import ChangePassword from '@/components/myaccount/ChangePassword.vue';

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
      path: '/myaccount',
      component: MyAccount,
      redirect: '/myaccount/profile',
      children:[
        {
          path: 'profile',
          name: 'Profile',
          component: Profile,
        },
        {
          path: 'management',
          name: 'Management',
          component: Management,
        },
        {
          path: 'friends',
          name: 'Friends',
          component: Friends,
        },
        {
          path: 'modifyInfo',
          name: 'ModifyInfo',
          component: ModifyInfo
        },
        {
          path: 'changePw',
          name: 'ChangePassword',
          component: ChangePassword
        },
      ]
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
