package cn.lblbc.shop.module.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.SpUtil
import cn.lblbc.lib.utils.ToastUtil.toast
import cn.lblbc.lib.utils.launch
import cn.lblbc.shop.R
import cn.lblbc.shop.network.LoginRequest
import cn.lblbc.shop.network.LoginResp
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.SP_KEY_TOKEN
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.part_login_btns.*
import kotlinx.android.synthetic.main.part_login_name_pwd.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListeners()
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
        loginTv.setOnClickListener { login() }
        registerTv.setOnClickListener { register() }
    }

    private fun login() {
        val userName = userNameEt.text.toString()
        val password = passwordEt.text.toString()
        val loginReq = LoginRequest(userName, password)
        launch(
            action = { NetworkRepository.apiService.login(loginReq) },
            onSuccess = { processResponse(it) }
        )
    }

    private fun register() {
        val userName = userNameEt.text.toString()
        val password = passwordEt.text.toString()
        val loginReq = LoginRequest(userName, password)
        launch(
            action = { NetworkRepository.apiService.register(loginReq) },
            onSuccess = { processResponse(it) }
        )
    }

    private fun processResponse(resp: LoginResp?) {
        if (resp?.code == 0) {
            SpUtil.put(SP_KEY_TOKEN, resp.data?.token ?: "")
            finish()
        } else {
            toast(resp?.msg ?: "登录/注册失败")
        }
    }
}
