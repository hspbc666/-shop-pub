package cn.hsp.shop.module.order

import androidx.lifecycle.MutableLiveData
import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.ShopRepo
import cn.hsp.shop.network.request.QueryOrderReq
import cn.hsp.shop.network.response.QueryOrderResp
import cn.hsp.shop.utils.Constants.ORDER_TAB_ALL
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_COMMENT
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_DELIVER
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_PAY
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_RECEIVE
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_RETURN

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class OrderListModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val allOrders: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()
    val toPayOrders: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()
    val toDeliverOrders: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()
    val toReceiveOrders: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()
    val toCommentOrders: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()
    val toReturnOrders: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()
//    val allOrder: MutableLiveData<List<QueryOrderResp>> = MutableLiveData()

    fun queryAllOrder(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                allOrders.value = repo.queryOrder(QueryOrderReq(ORDER_TAB_ALL))?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryToPayOrder(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                toPayOrders.value = repo.queryOrder(QueryOrderReq(ORDER_TAB_TO_PAY))?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryToDeliverOrder(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                toDeliverOrders.value = repo.queryOrder(QueryOrderReq(ORDER_TAB_TO_DELIVER))?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryToReceiveOrder(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                toReceiveOrders.value = repo.queryOrder(QueryOrderReq(ORDER_TAB_TO_RECEIVE))?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryToCommentOrder(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                toCommentOrders.value = repo.queryOrder(QueryOrderReq(ORDER_TAB_TO_COMMENT))?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

    fun queryToReturnOrder(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                toReturnOrders.value = repo.queryOrder(QueryOrderReq(ORDER_TAB_TO_RETURN))?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}