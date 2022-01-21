package cn.lblbc.shop.module.login

import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.SpUtil

object LoginManager {
    fun isLoggedIn() = !SpUtil.get(Constants.SP_KEY_TOKEN, "").isNullOrEmpty()
    fun quitLogin() {
        SpUtil.remove(Constants.SP_KEY_TOKEN)
    }
}