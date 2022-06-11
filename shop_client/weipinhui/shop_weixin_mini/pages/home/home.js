//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
var http = require('../../utils/httputils.js');

Page({
  data: {
    goodsList:[],
    vertical: false,// banner滑动方向是否为纵向
  },
  onLoad: function () {
    this.queryGoods()
  },
  onShow: function () {
    this.queryGoods()
  },
  queryGoods() {
    let homeCategoryId = 0
    let _this = this
    http.get('shop/goods/category/' + homeCategoryId, '',
      function (resp) {
        _this.setData({
          goodsList: resp.data
        })
      },
      function (err) { })
  },
  gotoGoodsDetail(e) {
    let goodsId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/goods/goods?id=' + goodsId
    })
  },

})