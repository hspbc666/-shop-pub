/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
package cn.hsp.shop.base

import cn.hsp.shop.network.ApiService
import cn.hsp.shop.network.OkHttpClientUtil
import cn.hsp.shop.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository {
    val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClientUtil.getClient()
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(ApiService::class.java)
    }
}