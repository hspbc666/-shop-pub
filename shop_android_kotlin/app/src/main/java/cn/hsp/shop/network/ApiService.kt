/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network

import cn.hsp.shop.network.request.LoginRequest
import cn.hsp.shop.network.request.RegisterRequest
import cn.hsp.shop.network.response.*
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    @Headers("ignoreToken:true")
    suspend fun login(@Body request: LoginRequest): Result<LoginResp?>?

    @POST("user/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResp?>?

    @GET("shop/goods/list")
    suspend fun queryGoods(): Result<List<Goods>?>?

    @GET("shop/goods/query/{goodsId}")
    suspend fun queryGoods(@Path("goodsId") goodsId: String): Result<Goods?>?

    @GET("shop/cart/add/{goodsId}")
    suspend fun addToCart(@Path("goodsId") goodsId: String): Result<String?>?

    @GET("shop/cart/modify/{goodsId}/{quantity}")
    suspend fun modifyCart(
        @Path("goodsId") goodsId: String,
        @Path("quantity") quantity: Int
    ): Result<String?>?

    @GET("shop/cart/list")
    suspend fun queryCart(): Result<List<CartItem>?>?

//
//    @GET("goods/api/del/{goodsId}")
//    suspend fun deleteData(@Path("goodsId") goodsId: Long): Result<String?>?
//

//    @POST("goods/api/modify")
//    suspend fun modifyData(@Body request: ModifyGoodsRequest): Result<String?>?


//    @GET("/article/list/{page}/json")
//    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>


}