package cn.lblbc.shop.module.order.confirm

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.CreateOrderRequest
import cn.lblbc.shop.network.Address

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class ConfirmOrderViewModel : BaseViewModel() {
    val defaultAddress: MutableLiveData<Address> = MutableLiveData()

    fun createOrder(request: CreateOrderRequest, onSuccess: (orderId: String) -> Unit) {
        launch({
            val createOrderResp = ShopRepo.createOrder(request)
            createOrderResp?.data?.orderId?.let { onSuccess.invoke(it) }
        })
    }

    fun queryDefaultAddress() {
        launch({
            ShopRepo.queryDefaultAddress()?.data?.let { defaultAddress.value = it }
        })
    }
}