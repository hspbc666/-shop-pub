//花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
//编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
var dtime = '_deadtime';

function set(k, v, t) {
  wx.setStorageSync(k, v)
  var seconds = parseInt(t);
  if (seconds > 0) {
    var timestamp = Date.parse(new Date());
    timestamp = timestamp / 1000 + seconds;
    wx.setStorageSync(k + dtime, timestamp + "")
  } else {
    wx.removeStorageSync(k + dtime)
  }
}

function get(k, def) {
  var deadtime = parseInt(wx.getStorageSync(k + dtime))
  if (deadtime) {
    if (parseInt(deadtime) < Date.parse(new Date()) / 1000) {
      if (def) {
        return def;
      } else {
        return;
      }
    }
  }
  var res = wx.getStorageSync(k);
  if (res) {
    return res;
  } else {
    return def;
  }
}

function remove(k) {
  wx.removeStorageSync(k);
  wx.removeStorageSync(k + dtime);
}

function clear() {
  wx.clearStorageSync();
}

module.exports = {
  set: function(t, r, a) {
        wx.setStorageSync(t, r);
        var n = parseInt(a);
        if (n > 0) {
            var o = Date.parse(new Date());
            o = o / 1e3 + n, wx.setStorageSync(t + dtime, o + "");
        } else wx.removeStorageSync(t + dtime);
    },
    get: function(t, r) {
        var a = parseInt(wx.getStorageSync(t + dtime));
        if (a && parseInt(a) < Date.parse(new Date()) / 1e3) return r || void 0;
        var n = wx.getStorageSync(t);
        return n || r;
    },
    remove: function(t) {
        wx.removeStorageSync(t), wx.removeStorageSync(t + dtime);
    },
    clear: function() {
        wx.clearStorageSync();
    }
}