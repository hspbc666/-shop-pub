//index.js
//获取应用实例
var http = require('../../utils/httputils.js');

Page({
  data: {
    dataList: [],
    selectedCartIndexes: [],
    selectedSum: '￥0'
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
    let index = e.currentTarget.dataset['index'];
    let cartItem = this.data.dataList[index]
    let cartId = cartItem.id;
    let quantity = cartItem.quantity + 1;
    this.callServerToModifyQuantity(cartItem, index, cartId, quantity)
    this.updateSelectionSum()
  },
  decreaseQuantity(e) {
    let index = e.currentTarget.dataset['index'];
    let cartItem = this.data.dataList[index]
    let cartId = cartItem.id;
    let quantity = cartItem.quantity - 1;
    this.callServerToModifyQuantity(cartItem, index, cartId, quantity)
    this.updateSelectionSum()
  },
  callServerToModifyQuantity(cartItem, index, cartId, quantity) {
    this.updateDataList()
    let _this = this
    http.get('shop/cart/modify/' + cartId + '/' + quantity, '',
      function (resp) {
        if (quantity <= 0) {
          _this.queryData()
        } else {
          cartItem.quantity = quantity
        }
      },
      function (err) { })
  },
  updateDataList() {
    this.setData({
      dataList: this.data.dataList
    })
  },
  changeSelection(e) {
    let index = e.currentTarget.dataset['index'];
    let isChecked = e.detail.value
    if (isChecked === true) {
      this.data.selectedCartIndexes.push(index)
    } else {
      let pos = this.data.selectedCartIndexes.indexOf(index)
      if (pos > -1) {
        this.data.selectedCartIndexes.splice(pos, 1)
      }
    }
    this.setData({
      selectedCartIndexes: this.data.selectedCartIndexes
    })
    this.updateSelectionSum()
  },
  updateSelectionSum() {
    var sum = 0
    let selectedCartIndexes = this.data.selectedCartIndexes
    if (selectedCartIndexes != null && typeof(selectedCartIndexes) != 'undefined') {
      selectedCartIndexes.forEach((elem, index) => {
        sum += this.data.dataList[elem].quantity * this.data.dataList[elem].price / 100
      });
    }

    this.setData({
      selectedSum: "￥" + sum
    })
  },
  gotoConfirmOrder(e) {

  }
})