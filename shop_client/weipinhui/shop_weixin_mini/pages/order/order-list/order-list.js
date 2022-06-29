//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》  http://lblbc.cn/blog
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
var http = require('../../../utils/httputils.js');

Page({
  data: {
    orderList: [],
    tabs: ['全部', '待付款', '待发货', '待收货', '待评价', '退还/售后'],
    current: 0,
  },
  onLoad: function (option) {
    var current = option.status
    this.setData({
      current: current
    })
    this.queryOrder()
  },
  onShow: function () {
    this.queryOrder()
  },
  tabSelect: function (e) {
    var current = e.currentTarget.dataset.id
    this.setData({
      current: current
    })
    this.queryOrder()
  },
  queryOrder() {
    let _this = this
    http.get('shop/orders?orderStatus='+this.data.current, '',
      function (resp) {
        _this.setData({
          orderList: resp.data
        })
      },
      function (err) { })
  },
  gotoOrderDetail(e) {
    let orderId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/order/order-detail/order-detail?id=' + orderId
    })
  },
  delOrder(e) {
    let _this = this
    let orderId = e.currentTarget.dataset['id'];
    http.del('shop/orders/' + orderId, '',
      function (resp) {
        _this.queryOrder()
      },
      function (err) { })
  }

})