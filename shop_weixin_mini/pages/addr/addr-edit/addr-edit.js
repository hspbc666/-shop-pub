//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../../utils/httputils.js');

Page({
  data: {
    userAddrId: 0,
    name: "",
    phone: "",
    region: "",
    address: "",
    defaultAddress: false,
    addrType: 0
  },
  onLoad: function (option) {
    this.queryData(option.id)
  },
  methods: {
  },
  queryData(userAddrId) {
    let _this = this
    http.get('shop/addr/query/' + userAddrId, '',
      function (resp) {
        _this.setData({
          userAddrId: userAddrId,
          name: resp.data.name,
          phone: resp.data.phone,
          region: resp.data.region,
          address: resp.data.address,
          addrType: resp.data.addrType,
          defaultAddress: resp.data.defaultAddress,
        })
      },
      function (err) { })
  },
  onSubmit(e) {
    var params = {
      id: this.data.userAddrId,
      name: e.detail.value.name,
      phone: e.detail.value.phone,
      region: e.detail.value.region,
      address: e.detail.value.address,
      addrType: this.data.addrType,
      defaultAddress: this.data.defaultAddress,
    }
    http.post('shop/addr/modify', params,
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
  radioChange(e)
  {
    this.setData({
      addrType: e.detail.value
    })
  },
  switchChange(e)
  {
    this.setData({
      defaultAddress: e.detail.value
    })
  }

})