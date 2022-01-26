//厦门大学计算机专业 | 前华为工程师
//专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
//包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
//公众号：蓝不蓝编程
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