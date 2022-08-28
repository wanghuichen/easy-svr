import axios from 'axios';
import {MessageBox, Message} from 'element-ui';
import jwt_decode from "jwt-decode";

// create an axios instance
const axiosService = axios.create({
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 60000 // request timeout
});

// request interceptor
axiosService.interceptors.request.use(
  config => {

    return config;
  },
  error => {
    // do something with request error
    console.log('request error:::',error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
// axiosService.interceptors.response.use(
//   response => {
//     const res = response.data;
//     console.log(res)
//     return res;
//   if (response.data.errno == 303 || response.data.errno == undefined || response.data.errno == null) {
//     //清空token 返回登录页
//     return Promise.reject({code:303,msg:"登录错误。"});
//   }
//
//   if (response.data.errno == 403) {
//     return Promise.reject({code:403,msg:"无权限访问。"});
//   }
//   if (response.data.errno == 500) {
//     return Promise.reject({code:500,msg:"内部服务器错误。"});
//   }
//   return res;
// },
// error => {
//   return Promise.reject({code:10000,msg:error});
//   }
// );

export default axiosService;
