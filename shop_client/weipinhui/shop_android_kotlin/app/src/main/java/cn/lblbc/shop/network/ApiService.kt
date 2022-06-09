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
    suspend fun login(@Body request: LoginRequest): LoginResp?

    @POST("user/register")
    suspend fun register(@Body request: LoginRequest): LoginResp?

    @GET("shop/goods")
    suspend fun queryGoodsByCategory(@Query("categoryId") categoryId: String): QueryGoodsByCategoryResp?

    @GET("shop/categories")
    suspend fun queryCategory(): QueryCategoryResp?

    @GET("shop/goods/{goodsId}")
    suspend fun queryGoods(@Path("goodsId") goodsId: String): QueryGoodsResp?

    @GET("shop/cart")
    suspend fun queryCart(): QueryCartResp?

    @POST("shop/cart")
    suspend fun addToCart(@Body request: AddToCartRequest): DefaultResp?

    @PUT("shop/cart/{cartId}")
    suspend fun modifyCart(@Path("cartId") cartId: String, @Body request: ModifyCartRequest): DefaultResp?

    @GET("shop/goodsBySearch")
    suspend fun searchGoods(@Query("keyword") keyword: String): SearchGoodsResp?

    @GET("shop/addrs")
    suspend fun queryAddress(): QueryAddressResp?

    @GET("shop/addr_default")
    suspend fun queryDefaultAddress(): QueryDefaultAddressResp?

    @POST("shop/addrs")
    suspend fun addAddress(@Body address: Address): DefaultResp?

    @PUT("shop/addrs")
    suspend fun modifyAddress(@Body address: Address): DefaultResp?

    @DELETE("shop/addrs/{addressId}")
    suspend fun deleteAddress(@Path("addressId") addressId: String): DefaultResp?

    @POST("shop/orders")
    suspend fun createOrder(@Body request: CreateOrderRequest): CreateOrderResp?

    @DELETE("shop/orders/{orderId}")
    suspend fun deleteOrder(@Path("orderId") orderId: String): DefaultResp?

    @GET("shop/orders/{orderId}")
    suspend fun queryOrder(@Path("orderId") orderId: String): QueryOrderResp?

    @GET("shop/orders")
    suspend fun queryOrderByStatus(@Query("orderStatus") orderStatus: String): QueryOrderByStatusResp?
}