let objs = [
  {url: '/mock/user/do_login',
    method: 'post',
    template: (config) => {
      const { usercode, password } = config.query;
      if (usercode === 'admin' && password === '111111'){
        return {
          code: 0,
          message: '当前用户超级管理员',
          data: {user_type: 1, usercode: usercode, nickname: '张三丰', avatar_url: 'https://avatar-static.segmentfault.com/143/275/143275376-58b7dc3a72625_big64'}
        };
      }
      return {
        code: 10,
        message: '账号和密码错误，登录失败!'
      };
    }
  }
];

export default objs;
