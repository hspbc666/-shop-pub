package cn.lblbc.shop.module.order.detail

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.response.OrderInfo

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderDetailViewModel : BaseViewModel() {
    val orderInfo: MutableLiveData<OrderInfo> = MutableLiveData()

    fun queryOrder(orderId: String) {
        launch({
            orderInfo.value = ShopRepo.queryOrder(orderId)?.data
        })
    }

    fun deleteOrder(orderId: String, onSuccess: () -> Unit) {
        launch({
            ShopRepo.deleteOrder(orderId)
            onSuccess.invoke()
        })
    }
}