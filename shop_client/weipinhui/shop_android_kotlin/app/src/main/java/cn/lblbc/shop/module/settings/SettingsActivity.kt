package cn.lblbc.shop.module.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.shop.R
import cn.lblbc.shop.module.addr.AddressListActivity
import cn.lblbc.shop.module.mine.LoginManager
import kotlinx.android.synthetic.main.part_settings_addr.*
import kotlinx.android.synthetic.main.part_settings_quit_login.*
import kotlinx.android.synthetic.main.part_settings_title.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initListeners()
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
        addressLayout.setOnClickListener { gotoAddressList() }
        quitLoginTv.setOnClickListener {
            LoginManager.quitLogin()
            finish()
        }
    }

    private fun gotoAddressList() {
        startActivity(Intent(this, AddressListActivity::class.java))
    }
}