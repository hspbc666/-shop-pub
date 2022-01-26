//蓝不蓝编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../../utils/httputils.js');

Page({
  data: {
    cartList: [],
    addr: "",
    costSum: 0
  },
  methods: {
  },
  onLoad: function (option) {
    this.queryCart(option.ids)
    this.queryDefaultAddr()
  },
  queryCart(cartIds) {
    let _this = this
    http.get('shop/cart/queryByIds/' + cartIds, '',
      function (resp) {
        _this.setData({
          cartList: resp.data
        })

        var sum = 0
        if (resp.data != null && typeof (resp.data) != 'undefined') {
          resp.data.forEach((elem, index) => {
            sum += elem.quantity * elem.price
          });
        }

        _this.setData({
          costSum: "￥" + sum / 100
        })
      },
      function (err) { })
  },
  queryDefaultAddr() {
    let _this = this
    http.get('shop/addr/query_default', '',
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
        cartIdList: cartIds
      }
      http.post('shop/order/createFromCart', params,
        function (resp) {
          wx.navigateBack({
            delta: 0,
          })
          _this.gotoOrderDetail(resp.data.orderId)
        },
        function (err) { })
    }
  },
  gotoOrderDetail(orderId) {
    wx.navigateTo({
      url: '/pages/order/order-detail/order-detail?id=' + orderId
    })
  },
})