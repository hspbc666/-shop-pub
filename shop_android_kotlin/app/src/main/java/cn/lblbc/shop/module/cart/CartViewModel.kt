package cn.lblbc.shop.module.cart

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.response.CartItem

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CartViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val dataList: MutableLiveData<List<CartItem>> = MutableLiveData()
    val selectionItemList = mutableListOf<CartItem>()
    val selectionChangeCount: MutableLiveData<Int> = MutableLiveData()

    fun queryCart(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                dataList.value = repo.queryCart()?.data
                clearSelectionItems()
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    private fun clearSelectionItems() {
        selectionItemList.clear()
        notifySelectionChanged()
    }

    fun modifyCart(
        cartItem: CartItem,
        isChecked: Boolean,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        if (isChecked) {
            selectItem(cartItem)
        }
        launch(
            {
                repo.modifyCart(cartItem.id, cartItem.quantity)
                if (cartItem.quantity <= 0) {
                    queryCart(onSuccess, onFailure, onComplete)
                } else {
                    onSuccess?.invoke()
                }
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun selectItem(cartItem: CartItem) {
        val selectionItem = selectionItemList.firstOrNull { it.goodsId == cartItem.id }
        if (selectionItem != null) {
            if (cartItem.quantity <= 0) {
                selectionItemList.remove(selectionItem)
            } else {
                selectionItem.quantity = cartItem.quantity
            }
        } else {
            selectionItemList.add(cartItem)
        }
        notifySelectionChanged()
    }

    fun disSelectItem(goodsId: String) {
        selectionItemList.removeIf { it.id == goodsId }
        notifySelectionChanged()
    }

    private fun notifySelectionChanged() {
        selectionChangeCount.value = selectionChangeCount.value?.inc()
    }
}