//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../utils/httputils.js');

Page({
  data: {
  },
  methods: {
  },
  gotoAddr(e) {
    wx.navigateTo({
      url: '/pages/addr/addr-list/addr-list'
    })
  }
})