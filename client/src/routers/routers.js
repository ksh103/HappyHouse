import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import Main from '@/components/Main.vue';
import User from '@/components/User.vue';
import Login from '@/components/user/Login.vue';
import Join from '@/components/user/Join.vue';
import ModifyInfo from '@/components/user/ModifyInfo.vue';
import BoardNotice from '@/components/BoardNotice.vue';


export default new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      component: Main
    },
    {
      path: '/board/notice',
      name: 'Notice',
      component: BoardNotice
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
    }
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
