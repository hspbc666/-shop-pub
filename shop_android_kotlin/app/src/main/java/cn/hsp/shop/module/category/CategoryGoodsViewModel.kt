package cn.hsp.shop.module.category

import androidx.lifecycle.MutableLiveData
import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.ShopRepo
import cn.hsp.shop.network.response.CategoryInfo
import cn.hsp.shop.network.response.Goods

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CategoryGoodsViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val goodsList: MutableLiveData<List<Goods>> = MutableLiveData()

    fun queryGoods(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                goodsList.value = repo.queryGoods()?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}