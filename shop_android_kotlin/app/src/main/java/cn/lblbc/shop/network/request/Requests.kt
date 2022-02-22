package cn.lblbc.shop.network.request

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class LoginReq(
    val name: String = "",
    val password: String = ""
)

class RegisterReq(
    val name: String = "",
    val password: String = ""
)

class CreateOrderFromCartReq(val cartIdList: List<String>, val userAddrId: String)

class CreateOrderReq(val goodsId: String, val userAddrId: String)

class SimpleOrderInfo(
    val goodsId: String?,
    val quantity: Int,
    var squarePic: String? = null
)
