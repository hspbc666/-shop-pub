package cn.hsp.shop.module.login

import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.SpUtil

object LoginManager {
    fun isLoggedIn() = !SpUtil.get(Constants.SP_KEY_TOKEN, "").isNullOrEmpty()

}