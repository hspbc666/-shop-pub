//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》  http://lblbc.cn/blog
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
var http = require('../../../utils/httputils.js');

Page({
  data: {
    orderItem: "",
    costSum: 0,
  },
  onLoad: function (option) {
    this.queryData(option.id)
  },
  queryData(orderId) {
    let _this = this
    http.get('shop/orders/' + orderId, '',
      function (resp) {
        _this.setData({
          orderItem: resp.data,
        })
        _this.calcSum(resp.data.list)
      },
      function (err) { })
  },
  calcSum(orderList) {
    var sum = 0
    if (orderList != null && typeof (orderList) != 'undefined') {
      orderList.forEach((elem, index) => {
        sum += elem.quantity * elem.price
      });
    }

    this.setData({
      costSum: "￥" + sum / 100
    })
  },
  delOrder(e) {
    let orderId = e.currentTarget.dataset['id'];
    http.del('shop/orders/' + orderId, '',
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