//蓝不蓝编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../../utils/httputils.js');

Page({
  data: {
    name: "",
    phone: "",
    region: "",
    address: "",
    defaultAddress: false,
    addrType: 0
  },
  onLoad: function (option) {
  },
  methods: {
  },
  onSubmit(e) {
    var params = {
      name: e.detail.value.name,
      phone: e.detail.value.phone,
      region: e.detail.value.region,
      address: e.detail.value.address,
      addrType: this.data.addrType,
      defaultAddress: this.data.defaultAddress,
    }
    http.post('shop/addr/add', params,
      function (resp) {
        wx.navigateBack({
          delta: 0,
          success: (res) => { },
          fail: (res) => { },
          complete: (res) => { },
        })
      },
      function (err) { })
  },
  radioChange(e) {
    this.setData({
      addrType: e.detail.value
    })
  },
  switchChange(e) {
    this.setData({
      defaultAddress: e.detail.value
    })
  }
})