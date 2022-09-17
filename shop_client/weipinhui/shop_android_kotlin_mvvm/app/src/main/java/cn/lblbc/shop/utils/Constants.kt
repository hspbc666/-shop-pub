package cn.lblbc.shop.utils

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
const val BASE_URL = "http://lblbc.cn/"
const val EXTRA_KEY_ADDRESS_INFO = "address_info"
const val LOGIN_URL = "user/login"
const val SP_KEY_TOKEN = "token"
const val SP_KEY_USER_ID = "user_id"
const val EXTRA_KEY_GOODS_ID = "goods_id"
const val EXTRA_KEY_ORDER_ID = "order_id"
const val EXTRA_KEY_MONEY = "cost_sum"
const val EXTRA_KEY_COST_SUM = "cost_sum"
const val EXTRA_KEY_ADDRESS_ID = "user_address_id"
const val EXTRA_KEY_ADDRESS = "address"
const val EXTRA_KEY_TAB_INDEX = "tab_index"
const val EXTRA_KEY_SIMPLE_ORDER = "simple_order"

interface OrderStatus {
    companion object {
        const val ORDER_STATUS_ALL = 0//全部
        const val ORDER_STATUS_TO_PAY = 1 //待付款
        const val ORDER_STATUS_TO_DELIVER = 2 //待发货
        const val ORDER_STATUS_TO_RECEIVE = 3 //待收货
        const val ORDER_STATUS_TO_COMMENT = 4 //待评价
        const val ORDER_STATUS_TO_RETURN = 5 //退货中
    }
}