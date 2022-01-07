/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network.response

class CartItem : Goods() {
    var quantity: Int = 0
}

open class Goods {
    var id: String = ""
    var name: String = ""
    var price: Long = 0
    var longPic: String? = null
    var squarePic: String? = null
}

class LoginResp {
    val id: Int = 0
    val token: String = ""
}

class RegisterResp {
    val id: Int = 0
}