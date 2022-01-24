//index.js
//获取应用实例
var http = require('../../utils/httputils.js');

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
    http.get('shop/cart/list/', '',
      function (resp) {
        _this.setData({
          dataList: resp.data
        })
      },
      function (err) { })
  },
  increaseQuantity(e) {
    let cartId = e.currentTarget.dataset['id'];
    let quantity = e.currentTarget.dataset['quantity'] + 1;
    let _this = this
    http.get('shop/cart/modify/' + cartId + '/' + quantity, '',
      function (resp) {
        _this.updateQuantityInDataList(cartId, quantity)
      },
      function (err) { })
  },
  updateQuantityInDataList(cartId, newQuantity) {

  }
})