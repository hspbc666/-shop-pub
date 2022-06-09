package cn.lblbc.shop.network

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class DefaultResp(var code: Int, var msg: String, var data: String?)

class QueryGoodsByCategoryResp(var code: Int, var msg: String, var data: List<Goods>?)

class Goods(
    var id: String = "",
    var name: String = "",
    var price: Long = 0,
    var squarePic: String = "",
    var descPic: String = "",
    var originalLink: String = ""
)

class CategoryInfo(val id: String = "", val name: String = "")
class QueryCategoryResp(var code: Int, var msg: String, var data: List<CategoryInfo>?)

class LoginResp(val code: Int, val msg: String, val data: LoginInfo?)

class LoginInfo(var id: Int = 0, var token: String = "")

class QueryGoodsResp(var code: Int, var msg: String, var data: Goods?)

class QueryCartResp(var code: Int, var msg: String, var data: List<CartItem>?)
class CartItem(
    var id: String = "",
    var goodsId: String = "",
    var name: String = "",
    var price: Long = 0L,
    var squarePic: String = "",
    var quantity: Int = 0
)

class SearchGoodsResp(var code: Int, var msg: String, var data: List<Goods>?)
class QueryAddressResp(val code: Int, val msg: String, val data: List<Address>?)
class Address(
    var id: String = "",
    var name: String = "",
    var phone: String = "",
    var address: String = "",
    var defaultAddress: Boolean = false,
)

class QueryDefaultAddressResp(val code: Int, val msg: String, val data: Address?)
class CreateOrderResp(val code: Int, val msg: String, val data: CreateOrderInfo?)
class CreateOrderInfo(var orderId: String = "")

class QueryOrderResp(val code: Int, val msg: String, val data: OrderInfo?)
class OrderInfo(
    val orderId: String = "",
    val status: Int = 0,
    val createTime: Long = 0,
    val list: List<OrderDetail> = listOf(),
    val address: Address? = null
)

class QueryOrderByStatusResp(val code: Int, val msg: String, val data: List<OrderInfo>?)

class OrderDetail(
    var goodsId: String = "",
    var name: String = "",
    var price: Long = 0,
    var squarePic: String = "",
    var quantity: Int = 0
)
