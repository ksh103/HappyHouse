import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

import Main from '@/components/Main.vue';
import BoardNotice from '@/components/BoardNotice.vue';

export default new VueRouter({
  routes: [
    {
      path: '/',
      component: Main
    },
    {
      name: 'Notice',
      path: '/board/notice',
      component: BoardNotice
    },

    // {
    //   name: 'BoardMain',
    //   path: '/board',
    //   component: BoardMain
    // },

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
