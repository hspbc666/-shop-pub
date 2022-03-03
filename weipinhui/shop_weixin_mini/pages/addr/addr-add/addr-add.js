//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
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