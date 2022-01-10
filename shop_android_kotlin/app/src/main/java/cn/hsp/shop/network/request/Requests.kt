/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network.request

class LoginReq(
    val name: String = "",
    val password: String = ""
)

class RegisterReq(
    val name: String = "",
    val password: String = ""
)

class CreateOrderReq(val cartIdList: List<String>?)

class QueryOrderReq(val orderStatus: Int)
