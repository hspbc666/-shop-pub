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
    suspend fun login(@Body request: LoginRequest): Result<LoginResp?>?

    @POST("user/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body request: LoginRequest): Result<LoginResp?>?

    @GET("shop/categories")
    suspend fun queryCategory(): Result<List<CategoryInfo>?>?

    @GET("shop/goods")
    suspend fun queryGoodsByCategory(@Query("categoryId") categoryId: String): Result<List<Goods>?>?

    @GET("shop/goods/{goodsId}")
    suspend fun queryGoods(@Path("goodsId") goodsId: String): Result<Goods?>?

    @GET("shop/goodsBySearch")
    suspend fun searchGoods(@Query("keyword") keyword: String): Result<List<Goods>?>?

    @POST("shop/cart")
    suspend fun addToCart(@Body request: AddToCartRequest): Result<String?>?

    @PUT("shop/cart/{cartId}")
    suspend fun modifyCart(@Path("cartId") cartId: String, @Body request: ModifyCartRequest): Result<String?>?

    @GET("shop/cart")
    suspend fun queryCart(): Result<List<CartItem>?>?

    @POST("shop/orders")
    suspend fun createOrder(@Body req: CreateOrderRequest): Result<CreateOrderResp?>?

    @GET("shop/orders")
    suspend fun queryOrderByStatus(@Query("orderStatus") orderStatus: String): Result<List<OrderInfo>?>?

    @GET("shop/orders/{orderId}")
    suspend fun queryOrder(@Path("orderId") orderId: String): Result<OrderInfo?>?

    @DELETE("shop/orders/{orderId}")
    suspend fun deleteOrder(@Path("orderId") orderId: String): Result<String?>?

    @GET("shop/addrs")
    suspend fun queryAddress(): Result<List<Address>?>?

    @GET("shop/addr_default")
    suspend fun queryDefaultAddress(): Result<Address?>?

    @POST("shop/addrs")
    suspend fun addAddress(@Body address: Address): Result<String?>?

    @PUT("shop/addrs")
    suspend fun modifyAddress(@Body address: Address): Result<String?>?

    @DELETE("shop/addrs/{addressId}")
    suspend fun deleteAddress(@Path("addressId") addressId: String): Result<String?>?
}