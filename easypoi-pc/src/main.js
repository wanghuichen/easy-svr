// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import ElementUI from 'element-ui';

import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/styles/default.scss';


import App from './App';
import store from './store';
import router from './router';
import settings from './settings';
// 全局引入相关包
import * as echarts from "echarts";
// 开启echarts
Vue.prototype.$echarts = echarts;

Vue.prototype.settings = settings;

import VueDirectiveImagePreviewer from 'vue-directive-image-previewer'
import 'vue-directive-image-previewer/dist/assets/style.css'
Vue.use(VueDirectiveImagePreviewer)

Vue.use(ElementUI);


if (process.env.NODE_ENV === 'development'){
  const { mockApi } = require('../mock');
  mockApi();
}
// 设置路由跳转的逻辑
router.beforeEach((to, from, next) => {

  if (to.matched.length !== 0) {
    next()
  } else {
    next({ path: '/index' })
  }
});
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
