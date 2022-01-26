var http = require('../../../utils/httputils.js');

Page({
  data: {
    orderItem: "",
  },
  onLoad: function (option) {
    this.queryData(option.id)
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
    let orderId = e.currentTarget.dataset['id'];
    http.get('shop/order/del/' + orderId, '',
      function (resp) {
       wx.showToast({
         title: '已删除',
       })

       wx.navigateBack({
        delta: 0,
      })
      },
      function (err) { })
  }

})