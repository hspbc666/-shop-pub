package cn.lblbc.shop.module.order.confirm

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.request.CreateOrderFromCartReq
import cn.lblbc.shop.network.request.CreateOrderReq
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.network.response.Address

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class ConfirmOrderViewModel : BaseViewModel() {
    val defaultAddress: MutableLiveData<Address> = MutableLiveData()

    fun createOrderFromCart(cartItemList: List<CartItem>, addressId: String, onSuccess: ((orderId: String) -> Unit)? = null) {
        launch({
            val cartIdList = cartItemList.map { it.id }
            val createOrderResp = ShopRepo.createOrderFromCart(CreateOrderFromCartReq(cartIdList, addressId))
            onSuccess?.invoke(createOrderResp?.data?.orderId!!)
        })
    }

    fun createOrder(goodsId: String, addressId: String, onSuccess: ((orderId: String) -> Unit)? = null) {
        launch({
            val createOrderResp = ShopRepo.createOrder(CreateOrderReq(goodsId, addressId))
            onSuccess?.invoke(createOrderResp?.data?.orderId!!)
        })
    }

    fun queryDefaultAddress() {
        launch({
            ShopRepo.queryDefaultAddress()?.data?.let { defaultAddress.value = it }
        })
    }
}