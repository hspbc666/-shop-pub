package cn.lblbc.shop.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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