//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
App({

  serverUrl: "http://192.168.31.10:8080/",
  userInfo: "",

  setGlobalUserInfo: function (user) {
    //设置用户对象为缓存的方法(key,data)
    wx.setStorageSync("userInfo", user);
  },

  getGlobalUserInfo: function () {
    //获取缓存的用户对象
    return wx.getStorageSync("userInfo");
  },

  /**
   * 验证登录
   */
  checkIsLogin() {
    if (this.userInfo != '') {
      return true;
    }
    else {
      return false;
    }
  },
  login() {
    let _this = this
    wx.login({ //登录
      success(res) {
        console.log(res)
        wx.request({
          url: _this.serverUrl + 'user/loginByWx',
          method: "POST",
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data: {
            code: res.code,
          },
          success(result) {
            _this.setGlobalUserInfo(result.data.data)
            // that.setGlobalUserInfo(result.data.data)
            console.log("登录成功");
          },
          fail() {
            console.log("失败");
          }
        })
      },
      fail() {
        console.log("登录失败");
      }
    })
  },
  onLaunch: function () {
    this.login()
  },
})