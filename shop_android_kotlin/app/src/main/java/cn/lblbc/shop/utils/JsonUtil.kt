package cn.lblbc.shop.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
object JsonUtil {
    fun toJson(obj: Any): String? {
        return Gson().toJson(obj)
    }

    inline fun <reified T : Any> fromJson(json: String): T {
        return Gson().fromJson(json, T::class.java)
    }

    inline fun <reified T : Any> fromJsonList(jsonList: String): List<T>? {
        return Gson().fromJson(jsonList, object : TypeToken<List<T?>?>() {}.type)
    }
}