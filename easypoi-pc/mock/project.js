let objs = [
  {url: '/mock/project/list_providers',
    method: 'post',
    template: (config) => {
      let lst = [{'label':'河南省','value':''}];
      return {
        code: 0,
        message: '获取工地成功',
        data: lst
      };
    }
  },
];

export default objs;
