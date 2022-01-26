//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
var http = require('../../../utils/httputils.js');

Page({
  data: {
    dataList: [],
  },
  onLoad: function () {
    this.queryData()
  },
  onShow: function () {
    this.queryData()
  },
  methods: {
  },
  queryData() {
    let _this = this
    http.get('shop/addr/query/', '',
      function (resp) {
        _this.setData({
          dataList: resp.data
        })
      },
      function (err) { })
  },
  gotoAddAddr() {
    wx.navigateTo({
      url: '/pages/addr/addr-add/addr-add'
    })
  },
  gotoEditAddr(e) {
    let userAddrId = e.currentTarget.dataset['id'];
    wx.navigateTo({
      url: '/pages/addr/addr-edit/addr-edit?id=' + userAddrId
    })
  },
  deleteAddr(e) {
    let userAddrId = e.currentTarget.dataset['id'];
    let _this = this
    http.get('shop/addr/del/' + userAddrId, '',
      function (resp) {
        _this.queryData()
      },
      function (err) { })
  },

})