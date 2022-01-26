//蓝不蓝编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../../utils/httputils.js');

Page({
  data: {
    goods: "",
    addr: "",
    costSum: 0
  },
  methods: {
  },
  onLoad: function (option) {
    this.queryCart(option.id)
    this.queryDefaultAddr()
  },
  queryCart(goodsId) {
    let _this = this
    http.get('shop/goods/query/' + goodsId, '',
      function (resp) {
        _this.setData({
          goods: resp.data
        })

        _this.setData({
          costSum: "￥" + resp.data.price / 100
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

    http.get('shop/order/create/' + this.data.goods.id, '',
      function (resp) {
        wx.navigateBack({
          delta: 0,
        })
        _this.gotoOrderDetail(resp.data.orderId)
      },
      function (err) { })

  },
  gotoOrderDetail(orderId) {
    wx.navigateTo({
      url: '/pages/order/order-detail/order-detail?id=' + orderId
    })
  },
  gotoSelectAddr(e) {
    let addrId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/addr/addr-select/addr-select?id=' + addrId
    })
  }
})