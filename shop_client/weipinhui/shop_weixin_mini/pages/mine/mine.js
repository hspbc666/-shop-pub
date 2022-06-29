//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》  http://lblbc.cn/blog
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
var http = require('../../utils/httputils.js');

Page({
  data: {
  },
  methods: {
  },
  gotoSettings(e) {
    wx.navigateTo({
      url: '/pages/settings/settings'
    })
  },
  gotoOrderPage(e) {
    var status = e.currentTarget.dataset.status
    wx.navigateTo({
      url: '/pages/order/order-list/order-list?status='+status
    })
  }

})