package cn.lblbc.shop.utils

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

fun showSoftKeyboard(context: Context, editText: EditText) {
    editText.requestFocus()
    val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}

fun hideSoftKeyboard(activity: Activity) {
    val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
    val v = activity.window.peekDecorView()
    if (v != null) {
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}