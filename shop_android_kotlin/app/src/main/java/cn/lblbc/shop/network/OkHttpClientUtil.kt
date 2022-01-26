package cn.lblbc.shop.network

import cn.lblbc.shop.network.interceptor.HttpLogInterceptor
import cn.lblbc.shop.network.interceptor.LoginInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
object OkHttpClientUtil {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .apply {
                    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    addInterceptor(HttpLogInterceptor())
                    addInterceptor(LoginInterceptor())
                }.build()
    }
}
