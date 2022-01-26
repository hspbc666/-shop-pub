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