package cn.lblbc.shop.utils

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

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