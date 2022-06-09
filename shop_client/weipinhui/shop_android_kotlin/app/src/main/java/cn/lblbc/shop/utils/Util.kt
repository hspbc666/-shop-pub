package cn.lblbc.shop.utils

import cn.lblbc.lib.utils.getMoneyByYuan
import cn.lblbc.shop.network.CartItem
import cn.lblbc.shop.network.OrderDetail

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
fun calcSum1(data: List<OrderDetail>): String {
    val sum = data.sumOf { it.price * it.quantity }
    return getMoneyByYuan(sum)
}

fun calcSum2(data: List<CartItem>): String {
    val sum = data.sumOf { it.price * it.quantity }
    return getMoneyByYuan(sum)
}