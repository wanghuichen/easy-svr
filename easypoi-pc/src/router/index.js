import Vue from 'vue';
import Router from 'vue-router';

import Frame from '@/views/layout/Frame';


Vue.use(Router);

export default new Router({

  routes: [
    {
      path: '/login-local',
      component: Frame,
      children: [
        {
          path: '/index',
          meta: {pageTitle: '管理平台首页', leftMenuIndex: 'index'},
          component: () => import('@/views/Index')
        },
        {
          path: '/sys-user-list',
          meta: {pageTitle: '用户列表', leftMenuIndex: 'sys-user-list'},
          component: () => import('@/views/SysUserList')
        },

      ]
    },
  ]
});

