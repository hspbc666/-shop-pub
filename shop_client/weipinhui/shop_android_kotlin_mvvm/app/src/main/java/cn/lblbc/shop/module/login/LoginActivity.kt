package cn.lblbc.shop.module.login

import android.content.Intent
import cn.lblbc.lib.utils.ToastUtil.toast
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.part_login_btns.*
import kotlinx.android.synthetic.main.part_login_name_pwd.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class LoginActivity : BaseVmActivity<LoginViewModel>() {
    override fun viewModelClass() = LoginViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_login

    override fun initView() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initListeners() {
        loginTv.setOnClickListener {
            mViewModel.login(
                userNameEt.text.toString(),
                passwordEt.text.toString(),
                onSuccess = {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                },
                onFailure = { toast(it) })
        }
        registerTv.setOnClickListener {
            mViewModel.register(
                userNameEt.text.toString(),
                passwordEt.text.toString(),
                onSuccess = {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                },
                onFailure = { toast(it) })
        }
    }
}
