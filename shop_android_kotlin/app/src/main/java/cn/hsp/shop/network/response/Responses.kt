/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network.response

open class Goods {
    var id: String = ""
    var name: String = ""
    var price: Long = 0
    var longPic: String? = null
    var squarePic: String? = null
}

class CartItem {
    var id: String = ""
    var goodsId: String = ""
    var name: String = ""
    var price: Long = 0
    var longPic: String? = null
    var squarePic: String? = null
    var quantity = 0
}

class LoginResp {
    var id: Int = 0
    var token: String = ""
}

class RegisterResp {
    var id: Int = 0
}

class CreateOrderResp {
    var orderId: String = ""
}

class QueryOrderResp {
    val orderId: String? = null
    val status: Int? = null
    val createTime: Long = 0
    val list: List<FullOrderInfo>? = null
}

class FullOrderInfo {
    val orderId: String? = null
    val goodsId: String? = null
    val name: String? = null
    val price: Long = 0
    val longPic: String? = null
    val squarePic: String? = null
    val quantity = 0
}

class UserAddr {
    var id = ""
    var name: String? = null
    var phone: String? = null
    var region: String? = null
    var address: String? = null
    var defaultAddress = false
    var addrType = 0
}
