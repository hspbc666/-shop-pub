package cn.lblbc.shop.module.home

import androidx.lifecycle.MutableLiveData
import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.network.CategoryInfo

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class HomeViewModel : BaseViewModel() {
    val categoryInfoList: MutableLiveData<List<CategoryInfo>> = MutableLiveData()

    fun queryCategory() {
        launch({
            categoryInfoList.value = ShopRepo.queryCategory()?.data
        })
    }
}