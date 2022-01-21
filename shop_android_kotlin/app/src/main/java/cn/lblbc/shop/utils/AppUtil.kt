package cn.lblbc.shop.utils

import android.content.Context
import android.content.pm.PackageManager

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