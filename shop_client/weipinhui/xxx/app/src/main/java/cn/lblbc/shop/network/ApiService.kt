package cn.lblbc.shop.network

import retrofit2.http.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
interface ApiService {

    @POST("user/login")
    @Headers("ignoreToken:true")
    suspend fun login(@Body req: LoginRequest): Result<LoginResp?>?

    @POST("user/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body req: LoginRequest): Result<LoginResp?>?

    @GET("shop/category")
    suspend fun queryCategory(): Result<List<CategoryInfo>?>?

    @GET("shop/goods/category/{categoryId}")
    suspend fun queryGoodsByCategory(@Path("categoryId") categoryId: String): Result<List<Goods>?>?

    @GET("shop/goods/query/{goodsId}")
    suspend fun queryGoods(@Path("goodsId") goodsId: String): Result<Goods?>?

    @GET("shop/goods/search/{keyword}")
    suspend fun searchGoods(@Path("keyword") keyword: String): Result<List<Goods>?>?

    @GET("shop/cart/add/{goodsId}")
    suspend fun addToCart(@Path("goodsId") goodsId: String): Result<String?>?

    @GET("shop/cart/modify/{cartId}/{quantity}")
    suspend fun modifyCart(@Path("cartId") cartId: String, @Path("quantity") quantity: Int): Result<String?>?

    @GET("shop/cart/list")
    suspend fun queryCart(): Result<List<CartItem>?>?

    @POST("shop/order/create")
    suspend fun createOrder(@Body req: CreateOrderRequest): Result<CreateOrderResp?>?

    @GET("shop/order/queryByStatus/{orderStatus}")
    suspend fun queryOrderByStatus(@Path("orderStatus") orderStatus: Int): Result<List<OrderInfo>?>?

    @GET("shop/order/query/{orderId}")
    suspend fun queryOrder(@Path("orderId") orderId: String): Result<OrderInfo?>?

    @GET("shop/order/del/{orderId}")
    suspend fun deleteOrder(@Path("orderId") orderId: String): Result<String?>?

    @GET("shop/addr/query")
    suspend fun queryAddress(): Result<List<Address>?>?

    @GET("shop/addr/query_default")
    suspend fun queryDefaultAddress(): Result<Address?>?

    @POST("shop/addr/add")
    suspend fun addAddress(@Body req: Address): Result<String?>?

    @POST("shop/addr/modify")
    suspend fun modifyAddress(@Body req: Address): Result<String?>?

    @GET("shop/addr/del/{addressId}")
    suspend fun deleteAddress(@Path("addressId") addressId: String): Result<String?>?
}