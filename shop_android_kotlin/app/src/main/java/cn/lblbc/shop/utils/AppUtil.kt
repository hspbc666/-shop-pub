package cn.lblbc.shop.utils

import android.content.Context
import android.content.pm.PackageManager
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

/**
 * 获取当前apk的版本号
 */
fun getVersionCode(context: Context): Int {
    var versionCode = 0
    try {
        versionCode = context.packageManager.getPackageInfo(context.packageName, 0).versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return versionCode
}

/**
 * 获取当前apk的版本名
 */
fun getVersionName(context: Context): String {
    var versionName = ""
    try {
        versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return versionName
}