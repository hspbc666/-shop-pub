package cn.lblbc.shop.module.settings

import android.content.Intent
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseActivity
import cn.lblbc.shop.module.addr.AddressListActivity
import cn.lblbc.shop.module.login.LoginManager
import cn.lblbc.shop.utils.getVersionName
import kotlinx.android.synthetic.main.activity_settins.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SettingsActivity : BaseActivity() {
    override fun layoutResId(): Int = R.layout.activity_settins

    override fun initView() {
        initToolbar()
        appVersionTv.text = getVersionName(this)
    }

    override fun initListeners() {
        addressLayout.setOnClickListener { startActivity(Intent(this, AddressListActivity::class.java)) }
        quitLoginTv.setOnClickListener {
            LoginManager.quitLogin()
            finish()
        }
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }
}