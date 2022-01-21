/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
package cn.lblbc.shop.network

import cn.lblbc.shop.network.request.*
import cn.lblbc.shop.network.response.*
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    @Headers("ignoreToken:true")
    suspend fun login(@Body req: LoginReq): Result<LoginResp?>?

    @POST("user/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body req: RegisterReq): Result<RegisterResp?>?

    @GET("shop/category")
    suspend fun queryCategory(): Result<List<CategoryInfo>?>?

    @GET("shop/goods/list")
    suspend fun queryGoods(): Result<List<Goods>?>?

    @GET("shop/goods/category/{categoryId}")
    suspend fun queryGoodsByCategory(@Path("categoryId") categoryId: String): Result<List<Goods>?>?

    @GET("shop/goods/query/{goodsId}")
    suspend fun queryGoods(@Path("goodsId") goodsId: String): Result<Goods?>?

    @GET("shop/goods/search/{keyword}")
    suspend fun searchGoods(@Path("keyword") keyword: String): Result<List<Goods>?>?

    @GET("shop/cart/add/{goodsId}")
    suspend fun addToCart(@Path("goodsId") goodsId: String): Result<String?>?

    @GET("shop/cart/modify/{cartId}/{quantity}")
    suspend fun modifyCart(
        @Path("cartId") cartId: String,
        @Path("quantity") quantity: Int
    ): Result<String?>?

    @GET("shop/cart/list")
    suspend fun queryCart(): Result<List<CartItem>?>?

    @POST("shop/order/createFromCart")
    suspend fun createOrderFromCart(@Body req: CreateOrderFromCartReq): Result<CreateOrderResp?>?

    @POST("shop/order/create")
    suspend fun createOrder(@Body req: CreateOrderReq): Result<CreateOrderResp?>?

    @GET("shop/order/pay/{orderId}")
    suspend fun payForOrder(@Path("orderId") orderId: String): Result<String?>?

    @POST("shop/order/query")
    suspend fun queryOrder(@Body req: QueryOrderReq): Result<List<QueryOrderResp>?>?

    @GET("shop/order/query/{orderId}")
    suspend fun queryOrder(@Path("orderId") orderId: String): Result<QueryOrderResp?>?

    @GET("shop/order/del/{orderId}")
    suspend fun deleteOrder(@Path("orderId") orderId: String): Result<String?>?

    @GET("shop/addr/query")
    suspend fun queryAddress(): Result<List<UserAddr>?>?

    @GET("shop/addr/query_default")
    suspend fun queryDefaultAddress(): Result<UserAddr?>?

    @POST("shop/addr/add")
    suspend fun addAddress(@Body req: UserAddr): Result<String?>?

    @POST("shop/addr/modify")
    suspend fun modifyAddress(@Body req: UserAddr): Result<String?>?

    @GET("shop/addr/del/{userAddrId}")
    suspend fun deleteAddress(@Path("userAddrId") userAddrId: String): Result<String?>?

}