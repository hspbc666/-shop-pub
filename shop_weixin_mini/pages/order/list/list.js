//index.js
//获取应用实例
var http = require('../../../utils/httputils.js');

Page({
  data: {
    categoryList: [],
    tabs: ['全部', '待付款', '待发货', '待收货', '待评价', '退还/售后'],
    current: 0,
  },
  onLoad: function () {
    this.queryData()
  },
  onShow: function () {
    this.queryData()
  },
  tabSelect: function (e) {
    var current = e.currentTarget.dataset.id
    this.setData({
      current: current
    })
  },
  queryData() {
    let _this = this
    http.get('shop/addr/query/', '',
      function (resp) {
        _this.setData({
          categoryList: resp.data
        })
      },
      function (err) { })
  },
  gotoSearch() {
    wx.navigateTo({
      url: '/pages/addr/addr-add/addr-add'
    })
  },
  gotoGoodsDetail(e) {
    wx.navigateTo({
      url: '/pages/addr/addr-add/addr-add'
    })
  },

})