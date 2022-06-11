//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
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
    http.get('shop/categories', '',
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
    http.get('shop/goods?categoryId=' + this.getCurrentCategoryId(), '',
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
    let goodsId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/goods/goods?id=' + goodsId
    })
  },

})