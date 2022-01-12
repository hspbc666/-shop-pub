package cn.hsp.shop.module.order

import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.ShopRepo
import cn.hsp.shop.network.request.CreateOrderFromCartReq
import cn.hsp.shop.network.request.CreateOrderReq
import cn.hsp.shop.network.request.SimpleOrderInfo
import cn.hsp.shop.network.response.CartItem
import cn.hsp.shop.network.response.QueryOrderResp

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class OrderDetailViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }

    fun payForOrder(
        orderId: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                repo.payForOrder(orderId)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun deleteOrder(
        orderId: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                repo.deleteOrder(orderId)
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}