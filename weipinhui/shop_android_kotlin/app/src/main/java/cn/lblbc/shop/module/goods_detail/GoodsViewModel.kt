package cn.lblbc.shop.module.goods_detail

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
class GoodsViewModel : BaseViewModel() {
    val goods: MutableLiveData<Goods> = MutableLiveData()

    fun queryGoods(goodsId: String) {
        launch({
            goods.value = ShopRepo.queryGoods(goodsId)?.data
        })
    }

    fun addToCart(goodsId: String, onSuccess: () -> Unit) {
        launch({
            ShopRepo.addToCart(goodsId)
            onSuccess.invoke()
        })
    }
}