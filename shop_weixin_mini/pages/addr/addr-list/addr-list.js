//index.js
//获取应用实例
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
  addAddr() {
    wx.navigateTo({
      url: '/pages/addr/addr-add'
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