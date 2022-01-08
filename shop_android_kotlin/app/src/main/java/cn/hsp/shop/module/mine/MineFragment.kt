package cn.hsp.shop.module.mine

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseFragment
import cn.hsp.shop.module.login.LoginActivity
import cn.hsp.shop.module.login.LoginManager
import cn.hsp.shop.module.settings.SettingsActivity

class MineFragment : BaseFragment() {
    private lateinit var userLogoIv: ImageView
    private lateinit var userNameTv: TextView
    private lateinit var userInfoLayout: View
    override fun layoutResId() = R.layout.fragment_mine

    override fun initView() {
        userLogoIv = findViewById(R.id.userLogoIv)
        userNameTv = findViewById(R.id.userNameTv)
        userInfoLayout = findViewById(R.id.userInfoLayout)
    }

    private fun refreshPage() {
        if (LoginManager.isLoggedIn()) {
            userLogoIv.setImageResource(R.drawable.ic_user_loggedin)
            userNameTv.text = "尊贵会员"
            userInfoLayout.setOnClickListener { }
        } else {
            userLogoIv.setImageResource(R.drawable.ic_user_default)
            userNameTv.text = "登录/注册 >"
            userInfoLayout.setOnClickListener {
                startActivity(Intent(context, LoginActivity::class.java))
            }
        }
    }

    override fun initListeners() {
        findViewById<View>(R.id.settingsIv).setOnClickListener {
            startActivity(Intent(context, SettingsActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}