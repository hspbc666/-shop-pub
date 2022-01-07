/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network

import cn.hsp.shop.base.BaseRepository
import cn.hsp.shop.network.request.LoginRequest
import cn.hsp.shop.network.request.RegisterRequest

class ShopRepo : BaseRepository() {
    suspend fun queryGoods() = apiService.queryGoods()
    suspend fun queryGoods(goodsId: String) = apiService.queryGoods(goodsId)
    suspend fun queryCart() = apiService.queryCart()
    suspend fun addToCart(goodsId: String) = apiService.addToCart(goodsId)
    suspend fun modifyCart(goodsId: String, quantity: Int) = apiService.modifyCart(goodsId, quantity)
//    suspend fun queryData(noteId: Long) = apiService.queryData(noteId)
//    suspend fun addData(content: String) =
//        apiService.addData(AddNoteRequest(content))

//    suspend fun modifyData(id: Long, content: String) = apiService.modifyData(
//        ModifyNoteRequest(id, content)
//    )

    //    suspend fun deleteData(noteId: Long) = apiService.deleteData(noteId)
    suspend fun login(userName: String, password: String) =
        apiService.login(LoginRequest(userName, password))

    suspend fun register(userName: String, password: String) =
        apiService.register(RegisterRequest(userName, password))
}