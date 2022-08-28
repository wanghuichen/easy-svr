let objs = [
  {url: '/mock/common/get_full_city_json',
    method: 'get',
    template: (config) => {
      let citys = require('./city_code');
      return {
        code: 0,
        message: '获取城市信息成功',
        data: citys
      };
    }
  },
  {url: '/mock/common/get_up_city_for_elecascader',
    method: 'get',
    template: (config) => {
      let citys = require('./city_code');
      for(let p in citys){
        if(citys[p]['city'] == null){
          continue;
        }

        citys[p]['children']= citys[p]['city'];
        delete citys[p]['city'];

        for( let c in citys[p]['children']){

          delete citys[p]['children'][c]['area'];
        }
      }

      return {
        code: 0,
        message: '获取城市信息成功',
        data: citys
      };
    }
  }
];

export default objs;
