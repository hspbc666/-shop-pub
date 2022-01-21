package cn.lblbc.shop.utils

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * 把金额从分为单位,转化为元为单位,保留最多两位小数(舍弃末尾的0)
 */
fun getMoneyByYuan(moneyByFen: Long) = getNoMoreThanTwoDigits(moneyByFen / 100.0)

/**
 * 对入参保留最多两位小数(舍弃末尾的0),如:
 * 3.345->3.34
 * 3.40->3.4
 * 3.0->3
 */
fun getNoMoreThanTwoDigits(number: Double): String {
    val format = DecimalFormat("0.##")
    //未保留小数的舍弃规则,RoundingMode.FLOOR表示直接舍弃。
    format.roundingMode = RoundingMode.FLOOR
    return format.format(number)
}