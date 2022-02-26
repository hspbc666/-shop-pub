package cn.lblbc.shop.utils

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
//const val BASE_URL = "http://10.206.50.51:8080/"
//const val BASE_URL = "http://192.168.31.10:8080/"
const val BASE_URL = "https://springboot-cnyo-1514875-1309002655.ap-shanghai.run.tcloudbase.com/"

//    const val BASE_URL = "http://192.168.1.105:8080/"
const val LOGIN_URL = "user/login"
const val SP_KEY_TOKEN = "token"
const val SP_KEY_USER_ID = "user_id"
const val EXTRA_KEY_GOODS_ID = "goods_id"
const val EXTRA_KEY_ORDER_ID = "order_id"
const val EXTRA_KEY_ADDR_INFO = "addr_info"
const val EXTRA_KEY_COST_SUM = "cost_sum"
const val EXTRA_KEY_USER_ADDR_ID = "user_addr_id"
const val EXTRA_KEY_USER_ADDR = "user_addr"
const val EXTRA_KEY_CART_ITEMS = "cart_items"
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

interface AddrType {
    companion object {
        const val ADDR_TYPE_HOME = 1 //家庭
        const val ADDR_TYPE_COMPANY = 2 //公司
        const val ADDR_TYPE_OTHER = 3 //其他
    }
}