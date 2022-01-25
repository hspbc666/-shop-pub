//index.js
//获取应用实例
var http = require('../../../utils/httputils.js');

Page({
  data: {
    orderItem: "",
  },
  onLoad: function (option) {
  },
  queryData(orderId) {
    let _this = this
    http.get('shop/order/query/' + orderId, '',
      function (resp) {
        _this.setData({
          orderItem: resp.data,
        })
      },
      function (err) { })
  },
  delOrder(e) {
    let goodsId = e.currentTarget.dataset['id'];
    http.get('shop/order/del/' + goodsId, '',
      function (resp) {
       wx.showToast({
         title: '已删除',
       })
      },
      function (err) { })
  }

})