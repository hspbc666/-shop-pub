package cn.lblbc.shop.module.settings

import android.content.Intent
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseActivity
import cn.lblbc.shop.module.addr.AddressListActivity
import cn.lblbc.shop.module.login.LoginManager
import kotlinx.android.synthetic.main.part_settings_addr.*
import kotlinx.android.synthetic.main.part_settings_quit_login.*
import kotlinx.android.synthetic.main.part_settings_title.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SettingsActivity : BaseActivity() {
    override fun layoutResId(): Int = R.layout.activity_settings

    override fun initView() {
        initToolbar()
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