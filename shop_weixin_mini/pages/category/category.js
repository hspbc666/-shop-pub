//index.js
//获取应用实例
var http = require('../../utils/httputils.js');

Page({
  data: {
    categoryList: [],
    goodsList: [],
    current: 0,
  },
  onLoad: function () {
    this.queryCategory()
  },
  onShow: function () {
    this.queryCategory()
  },
  tabSelect: function (e) {
    var current = e.currentTarget.dataset.id
    this.setData({
      current: current
    })
    this.queryGoods()
  },
  queryCategory() {
    let _this = this
    http.get('shop/category', '',
      function (resp) {
        _this.setData({
          categoryList: resp.data
        })
        _this.queryGoods()
      },
      function (err) { })
  },
  queryGoods() {
    let _this = this
    http.get('shop/goods/category/' + this.getCurrentCategoryId(), '',
      function (resp) {
        _this.setData({
          goodsList: resp.data
        })
      },
      function (err) { })
  },
  getCurrentCategoryId() {
    return this.data.categoryList[this.data.current].id
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