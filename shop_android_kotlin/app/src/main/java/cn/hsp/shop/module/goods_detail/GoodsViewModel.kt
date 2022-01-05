package cn.hsp.shop.module.goods_detail

import androidx.lifecycle.MutableLiveData
import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.GoodsRepo
import cn.hsp.shop.network.response.Goods

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class GoodsViewModel : BaseViewModel() {
    private val repo by lazy { GoodsRepo() }
    val goods: MutableLiveData<Goods> = MutableLiveData()

    fun queryGoods(
        goodsId: Int,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                goods.value = repo.queryGoods(goodsId)?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

}