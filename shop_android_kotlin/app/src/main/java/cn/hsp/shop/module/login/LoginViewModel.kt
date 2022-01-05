package cn.hsp.shop.module.login

import cn.hsp.shop.base.BaseViewModel
import cn.hsp.shop.network.GoodsRepo
import cn.hsp.shop.utils.Constants.SP_KEY_TOKEN
import cn.hsp.shop.utils.Constants.SP_KEY_USER_ID
import cn.hsp.shop.utils.SpUtil
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class LoginViewModel : BaseViewModel() {
    private val repo by lazy { GoodsRepo() }

    fun login(
        userName: String, password: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val resp = repo.login(userName, password)
                if (resp?.isSuccess() == true) {
                    SpUtil.put(SP_KEY_TOKEN, resp?.data?.token ?: "")
                    SpUtil.put(SP_KEY_USER_ID, resp?.data?.id ?: 0L)
                    onSuccess?.invoke()
                } else {
                    onFailure?.invoke(resp?.msg ?: "")
                }
            },
            { onFailure?.invoke(it.message ?: "error") },
            { onComplete?.invoke() })
    }

    fun register(
        userName: String, password: String,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val resp = repo.register(userName, password)
                if (resp?.isSuccess() == true) {
                    onSuccess?.invoke()
                } else {
                    onFailure?.invoke(resp?.msg ?: "")
                }
            },
            { onFailure?.invoke(it.message ?: "error") },
            { onComplete?.invoke() })
    }
}