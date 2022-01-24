//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
/**
 * 请求头
 */
var header = {
    'content-type': 'application/json',
    'Authorization': "Bearer " + wx.getStorageSync("userInfo").token,
  }
  
  /**
   * 供外部post请求调用  
   */
  function post(url, params, onSuccess, onFailed) {
    console.log("请求方式：", "POST")
    request(url, params, "POST", onSuccess, onFailed);
  
  }
  
  /**
   * 供外部get请求调用
   */
  function get(url, params, onSuccess, onFailed) {
    console.log("请求方式：", "GET")
    request(url, params, "GET", onSuccess, onFailed);
  }
  
  /**
   * function: 封装网络请求
   * @url URL地址
   * @params 请求参数
   * @method 请求方式：GET/POST
   * @onSuccess 成功回调
   * @onFailed  失败回调
   */
  
  function request(url, params, method, onSuccess, onFailed) {
    console.log('请求url：' + url);
    url = getApp().serverUrl+url
    wx.showLoading({
      title: "正在加载中...",
    })
    console.log("请求头：", header)
    wx.request({
      url: url,
      data: dealParams(params),
      method: method,
      header: header,
      success: function(res) {
        wx.hideLoading();
        console.log('响应：', res.data);
  
        if (res.data) {
          /** start 根据需求 接口的返回状态码进行处理 */
          if (res.statusCode == 200) {
            onSuccess(res.data); //request success
          } else {
            onFailed(res.data.message); //request failed
          }
          /** end 处理结束*/
        }
      },
      fail: function(error) {
        onFailed(""); //failure for other reasons
      }
    })
  }
  
  /**
   * function: 根据需求处理请求参数：添加固定参数配置等
   * @params 请求参数
   */
  function dealParams(params) {
    console.log("请求参数:", params)
    return params;
  }
  
  
  // 1.通过module.exports方式提供给外部调用
  module.exports = {
    post: post,
    get: get,
  }
  