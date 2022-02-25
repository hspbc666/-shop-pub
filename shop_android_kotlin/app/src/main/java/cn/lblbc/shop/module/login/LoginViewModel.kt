package cn.lblbc.shop.module.login

import cn.lblbc.shop.base.BaseViewModel
import cn.lblbc.shop.network.ShopRepo
import cn.lblbc.shop.utils.SP_KEY_TOKEN
import cn.lblbc.shop.utils.SP_KEY_USER_ID
import cn.lblbc.shop.utils.SpUtil

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class LoginViewModel : BaseViewModel() {

    fun login(userName: String, password: String, onSuccess: () -> Unit, onFailure: (msg: String) -> Unit) {
        launch(
            {
                val resp = ShopRepo.login(userName, password)
                if (resp?.isSuccess() == true) {
                    SpUtil.put(SP_KEY_TOKEN, resp.data?.token ?: "")
                    SpUtil.put(SP_KEY_USER_ID, resp.data?.id ?: 0)
                    onSuccess.invoke()
                } else {
                    onFailure.invoke(resp?.msg ?: "")
                }
            },
            { onFailure.invoke(it.message ?: "error") })
    }

    fun register(userName: String, password: String, onSuccess: () -> Unit, onFailure: (msg: String) -> Unit) {
        launch(
            {
                val resp = ShopRepo.register(userName, password)
                if (resp?.isSuccess() == true) {
                    onSuccess.invoke()
                } else {
                    onFailure.invoke(resp?.msg ?: "")
                }
            },
            { onFailure.invoke(it.message ?: "error") })
    }
}