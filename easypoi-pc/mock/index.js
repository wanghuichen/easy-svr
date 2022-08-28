import Mock from 'mockjs';
import common from './common';
import user from './user';
import buildsite from './buildsite';


let arr = [
  ...common,
  ...user,
  ...buildsite
];

export function mockApi() {
  Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send;
  Mock.XHR.prototype.send = function() {
    if (this.custom.xhr) {
      this.custom.xhr.withCredentials = this.withCredentials || false;

      if (this.responseType) {
        this.custom.xhr.responseType = this.responseType;
      }
    }
    this.proxy_send(...arguments);
  };

  /**
   * @param {string} url
   * @returns {Object}
   */
  function _param2Obj(url) {
    const search = url.split('?')[1];
    if (!search) {
      return {};
    }
    return JSON.parse(
      '{"' +
        decodeURIComponent(search)
          .replace(/"/g, '\\"')
          .replace(/&/g, '","')
          .replace(/=/g, '":"')
          .replace(/\+/g, ' ') +
        '"}'
    );
  }

  function _XHR2ExpressReqWrap(respond) {
    return function(options) {
      let result = null;
      if (respond instanceof Function) {
        const { body, type, url } = options;
        // https://expressjs.com/en/4x/api.html#req
        result = respond({
          method: type,
          body: JSON.parse(body),
          query: _param2Obj(url)
        });
      } else {
        result = respond;
      }
      return Mock.mock(result);
    };
  }

  for (const v of arr){
    Mock.mock(new RegExp(v.url), v.method || 'post', _XHR2ExpressReqWrap(v.template));
  }
}
