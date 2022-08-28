import Vue from 'vue';
import Vuex from 'vuex';
import Cookie from 'js-cookie';

Vue.use(Vuex);

let state = {

  leftMenuStatus: Cookie.get('left_menu_status')==null?'open':Cookie.get('left_menu_status'),
  // loginUserInfo: Cookie.get('login_user_info')==null?null:JSON.parse(Cookie.get('login_user_info')),
  loginUserInfo:Cookie.get("loginUserInfo") == null ? null : JSON.parse(Cookie.get('loginUserInfo')),
Tabs:[{
  title:'首页',
  name:'index'
}],//tabs
  TabsValue:'index',
};

let actions = {};

let getters = {
  getLoginUserInfo: state => state.loginUserInfo
};

let mutations = {
  doToggleLeftMenu: (state) => {
    let status = state.leftMenuStatus == 'open' ? 'close' : 'open';

    state.leftMenuStatus = status;
    Cookie.set('left_menu_status', status, {expires: 30});
  },
  setLoginUserInfo(state, val) {
    Cookie.set('loginUserInfo', JSON.stringify(val), {expires: 30});
    state.loginUserInfo = val;
  },
  SET_TOKEN:(state,token)=>{
    state.token = token // token
    localStorage.setItem("token",token)
  },
};

const store = new Vuex.Store({state, getters, mutations});

export default store;
