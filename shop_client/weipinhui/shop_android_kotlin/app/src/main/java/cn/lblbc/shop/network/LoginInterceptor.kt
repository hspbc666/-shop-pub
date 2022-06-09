package cn.lblbc.shop.network

import cn.lblbc.lib.utils.SpUtil
import cn.lblbc.shop.utils.SP_KEY_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class LoginInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SpUtil.get(SP_KEY_TOKEN, "")
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(newRequest)
    }
}