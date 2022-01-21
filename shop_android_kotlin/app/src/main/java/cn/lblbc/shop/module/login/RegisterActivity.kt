package cn.lblbc.shop.module.login

import android.content.Intent
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.passwordEt
import kotlinx.android.synthetic.main.activity_login.registerBtn
import kotlinx.android.synthetic.main.activity_login.userNameEt
import kotlinx.android.synthetic.main.activity_register.*
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class RegisterActivity : BaseVmActivity<LoginViewModel>() {
    override fun viewModelClass() = LoginViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_register

    override fun initListeners() {
        registerBtn.setOnClickListener {
            mViewModel.register(
                    userNameEt.text.toString(),
                    passwordEt.text.toString(),
                    onSuccess = {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    },
                    onFailure = {
                        toast(it)
                    })
        }
    }
}
