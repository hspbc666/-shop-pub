var http = require('../../utils/httputils.js');

Page({
  data: {
    id: "",
    name: "",
    picUrl: "",
    price: 0,
  },
  onLoad: function (option) {
    this.queryData(option.id)
  },
  queryData(goodsId) {
    let _this = this
    http.get('shop/goods/query/' + goodsId, '',
      function (resp) {
        _this.setData({
          id: resp.data.id,
          name: resp.data.name,
          picUrl: resp.data.squarePic,
          price: '￥' + resp.data.price / 100
        })
      },
      function (err) { })
  },
  addToCart(e) {
    let goodsId = e.currentTarget.dataset['id'];
    http.get('shop/cart/add/' + goodsId, '',
      function (resp) {
        wx.showToast({
          title: '已加入购物车',
        })
      },
      function (err) { })
  },
  gotoConfirmOrder(e) {
    let goodsId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/order/order-confirm/order-confirm?id=' + goodsId
    })
  }

})