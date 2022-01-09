/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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