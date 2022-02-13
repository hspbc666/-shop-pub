package cn.lblbc.shop.module.order.confirm

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.request.CreateOrderFromCartReq
import cn.lblbc.shop.network.request.CreateOrderReq
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.network.response.OrderInfo
import cn.lblbc.shop.network.response.UserAddr

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class ConfirmOrderViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val defaultAddress: MutableLiveData<UserAddr> = MutableLiveData()

    fun createOrderFromCart(
        cartItemList: List<CartItem>,
        userAddrId:String,
        onSuccess: ((orderId: String) -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val cartIdList = cartItemList.map { it.id }
                val createOrderResp = repo.createOrderFromCart(CreateOrderFromCartReq(cartIdList,userAddrId))
                onSuccess?.invoke(createOrderResp?.data?.orderId!!)
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun createOrder(
        goodsId: String,
        userAddrId: String,
        onSuccess: ((orderId: String) -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val createOrderResp = repo.createOrder(CreateOrderReq(goodsId, userAddrId))
                onSuccess?.invoke(createOrderResp?.data?.orderId!!)
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

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

    fun queryOrder(
        orderId: String,
        onSuccess: ((orderInfo: OrderInfo) -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                repo.queryOrder(orderId)?.data?.let { onSuccess?.invoke(it) }
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryDefaultAddress(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                repo.queryDefaultAddress()?.data?.let { defaultAddress.value =it }
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}