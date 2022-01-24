/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
package cn.lblbc.shop.network

import cn.lblbc.shop.base.BaseRepository
import cn.lblbc.shop.network.request.*
import cn.lblbc.shop.network.response.UserAddr

class ShopRepo : BaseRepository() {
    suspend fun queryCategory() = apiService.queryCategory()
    suspend fun queryGoodsByCategory(categoryId: String) = apiService.queryGoodsByCategory(categoryId)
    suspend fun queryGoods(goodsId: String) = apiService.queryGoods(goodsId)
    suspend fun searchGoods(keyword: String) = apiService.searchGoods(keyword)
    suspend fun queryCart() = apiService.queryCart()
    suspend fun addToCart(goodsId: String) = apiService.addToCart(goodsId)
    suspend fun modifyCart(cartId: String, quantity: Int) =
        apiService.modifyCart(cartId, quantity)

    suspend fun createOrder(req: CreateOrderReq) = apiService.createOrder(req)
    suspend fun createOrderFromCart(req: CreateOrderFromCartReq) = apiService.createOrderFromCart(req)
    suspend fun payForOrder(orderId: String) = apiService.payForOrder(orderId)
    suspend fun queryOrder(req: QueryOrderReq) = apiService.queryOrder(req)
    suspend fun queryOrder(orderId: String) = apiService.queryOrder(orderId)
    suspend fun deleteOrder(orderId: String) = apiService.deleteOrder(orderId)
    suspend fun queryAddress() = apiService.queryAddress()
    suspend fun queryDefaultAddress() = apiService.queryDefaultAddress()
    suspend fun addAddress(userAddr: UserAddr) = apiService.addAddress(userAddr)
    suspend fun modifyAddress(userAddr: UserAddr) = apiService.modifyAddress(userAddr)
    suspend fun deleteAddress(userAddrId: String) = apiService.deleteAddress(userAddrId)
    suspend fun login(userName: String, password: String) =
        apiService.login(LoginReq(userName, password))

    suspend fun register(userName: String, password: String) =
        apiService.register(RegisterReq(userName, password))
}