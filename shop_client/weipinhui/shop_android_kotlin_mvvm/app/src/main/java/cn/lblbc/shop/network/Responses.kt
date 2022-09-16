package cn.lblbc.shop.network

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class Goods(
    var id: String = "",
    var name: String = "",
    var price: Long = 0,
    var longPic: String = "",
    var squarePic: String = "",
    var descPic: String = ""
)

class CartItem(
    var id: String = "",
    var goodsId: String = "",
    var name: String = "",
    var price: Long = 0L,
    var squarePic: String = "",
    var quantity: Int = 0
)

class LoginResp(var id: Int = 0, var token: String = "")

class CreateOrderResp(var orderId: String = "")

class OrderInfo(
    val orderId: String = "",
    val status: Int = 0,
    val createTime: Long = 0,
    val list: List<OrderDetail> = listOf(),
    val address: Address? = null
)

class OrderDetail(
    var goodsId: String = "",
    var name: String = "",
    var price: Long = 0,
    var squarePic: String = "",
    var quantity: Int = 0
)

class Address(
    var id: String = "",
    var name: String = "",
    var phone: String = "",
    var address: String = "",
    var defaultAddress: Boolean = false,
) {
    fun toSimpleInfo(): String {
        return "$name，$phone，$address"
    }
}

class CategoryInfo(val id: String = "", val name: String = "")