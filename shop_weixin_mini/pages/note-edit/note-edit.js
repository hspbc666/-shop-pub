//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var http = require('../../utils/httputils.js');

Page({
  data: {
    noteId: 0,
    noteContent: ""
  },
  onLoad: function (option) {
    this.queryNote(Number(option.id))
  },
  methods: {
  },
  queryNote(noteId) {
    let _this = this
    http.get('note/api/query/' + noteId, '',
      function (resp) {
        _this.setData({
          noteId: noteId,
          noteContent: resp.data.content
        })
      },
      function (err) { })
  },
  modifyNote(e) {
    var params = {
      id: this.data.noteId,
      content: e.detail.value.content,
    }
    http.post('note/api/modify', params,
      function (resp) {
        wx.navigateBack({
          delta: 0,
          success: (res) => { },
          fail: (res) => { },
          complete: (res) => { },
        })
      },
      function (err) { })
  }

})