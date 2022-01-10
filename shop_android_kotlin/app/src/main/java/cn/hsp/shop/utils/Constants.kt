package cn.hsp.shop.utils
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
object Constants {
//    const val BASE_URL = "http://10.206.50.51:8080/"
    const val BASE_URL = "http://192.168.31.10:8080/"
    const val LOGIN_URL = "user/login"
    const val SP_KEY_TOKEN = "token"
    const val SP_KEY_USER_ID = "user_id"
    const val EXTRA_KEY_GOODS_ID = "goods_id"
    const val EXTRA_KEY_COST_SUM = "cost_sum"
    const val EXTRA_KEY_CART_ITEMS = "cart_items"

    const val ORDER_TAB_ALL = 0
    const val ORDER_TAB_TO_PAY = 1
    const val ORDER_TAB_TO_DELIVER = 2
    const val ORDER_TAB_TO_RECEIVE = 3
    const val ORDER_TAB_TO_COMMENT = 4
    const val ORDER_TAB_TO_RETURN = 5

}