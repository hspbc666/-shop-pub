package cn.lblbc.shop.module.search

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.response.Goods

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SearchViewModel : BaseViewModel() {
    private val repo by lazy { ShopRepo() }
    val goodsList: MutableLiveData<List<Goods>> = MutableLiveData()

    fun queryGoods(
        keyword: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                goodsList.value = repo.searchGoods(keyword)?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }

}