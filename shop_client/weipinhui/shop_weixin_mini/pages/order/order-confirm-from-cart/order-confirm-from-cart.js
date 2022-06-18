//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》  http://lblbc.cn/blog
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
var http = require('../../../utils/httputils.js');

Page({
  data: {
    cartList: [],
    addr: null,
    costSum: 0
  },
  onLoad: function (option) {
    this.queryCart(option.ids)
    this.queryDefaultAddr()
  },
  queryCart(cartIds) {
    let _this = this
    http.get('shop/cart/' + cartIds, '',
      function (resp) {
        _this.setData({
          cartList: resp.data
        })

        _this.calcSum(resp.data)
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
  queryDefaultAddr() {
    let _this = this
    http.get('shop/addr_default', '',
      function (resp) {
        _this.setData({
          addr: resp.data
        })
      },
      function (err) { })
  },
  createOrder(e) {
    let _this = this

    let cartList = this.data.cartList
    let cartIds = []
    if (cartList != null && typeof (cartList) != 'undefined') {
      cartList.forEach((elem, index) => {
        cartIds.push(elem.id)
      });

      var params = {
        cartIdList: cartIds,
        addressId: this.data.addr.id
      }
      http.post('shop/ordersFromCart', params,
        function (resp) {
          wx.navigateBack()
          _this.gotoOrderDetail(resp.data.orderId)
        },
        function (err) { })
    }
  },
  gotoSelectAddr() {
    if (this.data.addr != null) {
      wx.navigateTo({
        url: '/pages/addr/addr-select/addr-select?id=' + this.data.addr.id
      })
    } else {
      wx.navigateTo({
        url: '/pages/addr/addr-select/addr-select'
      })
    }
  },
  gotoOrderDetail(orderId) {
    wx.navigateTo({
      url: '/pages/order/order-detail/order-detail?id=' + orderId
    })
  },
})