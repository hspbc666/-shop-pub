/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
package cn.hsp.shop.network

import cn.hsp.shop.network.request.LoginRequest
import cn.hsp.shop.network.request.RegisterRequest
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.network.response.LoginResp
import cn.hsp.shop.network.response.RegisterResp
import cn.hsp.shop.network.response.Result
import retrofit2.http.*

interface ApiService {

    @POST("api/login")
    @Headers("ignoreToken:true")
    suspend fun login(@Body request: LoginRequest): Result<LoginResp?>?

    @POST("api/register")
    @Headers("ignoreToken:true")
    suspend fun register(@Body request: RegisterRequest): Result<RegisterResp?>?

    @GET("shop/api/goods/list_all")
    @Headers("ignoreToken:true")
    suspend fun queryGoods(): Result<List<Goods>?>?
//
//    @GET("goods/api/query/{goodsId}")
//    suspend fun queryData(@Path("goodsId") goodsId: Long): Result<Goods?>?
//
//    @GET("goods/api/del/{goodsId}")
//    suspend fun deleteData(@Path("goodsId") goodsId: Long): Result<String?>?
//
//    @POST("goods/api/add")
//    suspend fun addData(@Body request: AddGoodsRequest): Result<String?>?
//
//    @POST("goods/api/modify")
//    suspend fun modifyData(@Body request: ModifyGoodsRequest): Result<String?>?


//    @GET("/article/list/{page}/json")
//    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>


}