let objs = [
  {url: '/mock/buildsite/list_for_cascader',
    method: 'get',
    template: (config) => {
      let buildsite = {};
      let lst = [];
      return {
        code: 0,
        message: '获取工地成功',
        data: lst
      };
    }
  },
];

export default objs;
