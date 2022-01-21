package cn.lblbc.shop.utils

import java.text.SimpleDateFormat
import java.util.*


fun formatTime(time: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return sdf.format(Date(time))
}

