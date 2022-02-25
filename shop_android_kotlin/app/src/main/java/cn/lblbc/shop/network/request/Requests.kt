package cn.lblbc.shop.network.request

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class LoginReq(var name: String = "", var password: String = "")

class RegisterReq(var name: String = "", var password: String = "")

class CreateOrderFromCartReq(var cartIdList: List<String>, var addressId: String)

class CreateOrderReq(var goodsId: String, var addressId: String)

class SimpleOrderInfo(
    var goodsId: String = "",
    var goodsName: String = "",
    var quantity: Int = 0,
    var price: Long = 0,
    var squarePic: String = ""
)
