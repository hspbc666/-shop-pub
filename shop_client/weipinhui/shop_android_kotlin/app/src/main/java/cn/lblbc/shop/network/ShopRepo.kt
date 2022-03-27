package cn.lblbc.shop.network

import cn.lblbc.shop.base.BaseRepository
import cn.lblbc.shop.network.request.CreateOrderFromCartReq
import cn.lblbc.shop.network.request.CreateOrderReq
import cn.lblbc.shop.network.request.LoginReq
import cn.lblbc.shop.network.response.Address

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
object ShopRepo : BaseRepository() {
    suspend fun queryGoodsByCategory(categoryId: String) = apiService.queryGoodsByCategory(categoryId)
    suspend fun searchGoods(keyword: String) = apiService.searchGoods(keyword)
    suspend fun queryGoods(goodsId: String) = apiService.queryGoods(goodsId)
    suspend fun login(userName: String, password: String) = apiService.login(LoginReq(userName, password))
    suspend fun register(userName: String, password: String) = apiService.register(LoginReq(userName, password))
    suspend fun queryAddress() = apiService.queryAddress()
    suspend fun queryDefaultAddress() = apiService.queryDefaultAddress()
    suspend fun addAddress(address: Address) = apiService.addAddress(address)
    suspend fun modifyAddress(address: Address) = apiService.modifyAddress(address)
    suspend fun deleteAddress(addressId: String) = apiService.deleteAddress(addressId)
    suspend fun queryCategory() = apiService.queryCategory()
    suspend fun queryCart() = apiService.queryCart()
    suspend fun modifyCart(cartId: String, quantity: Int) = apiService.modifyCart(cartId, quantity)
    suspend fun createOrder(req: CreateOrderReq) = apiService.createOrder(req)
    suspend fun createOrderFromCart(req: CreateOrderFromCartReq) = apiService.createOrderFromCart(req)
    suspend fun addToCart(goodsId: String) = apiService.addToCart(goodsId)
    suspend fun queryOrderByStatus(orderStatus: Int) = apiService.queryOrderByStatus(orderStatus)
    suspend fun queryOrder(orderId: String) = apiService.queryOrder(orderId)
    suspend fun deleteOrder(orderId: String) = apiService.deleteOrder(orderId)
}
