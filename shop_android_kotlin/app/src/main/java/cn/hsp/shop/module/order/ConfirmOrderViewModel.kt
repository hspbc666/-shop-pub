package cn.hsp.shop.module.order

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.ShopRepo
import cn.hsp.shop.network.request.CreateOrderReq
import cn.hsp.shop.network.response.CartItem
import cn.hsp.shop.network.response.Goods

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class ConfirmOrderViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val goods: MutableLiveData<Goods> = MutableLiveData()

    fun createOrder(
        cartItemList: List<CartItem>,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val cartIdList = cartItemList.map { it.id }
                repo.createOrder(CreateOrderReq(cartIdList))
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

}