package cn.hsp.shop.module.mine

import android.content.Intent
import android.view.View
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseFragment
import cn.hsp.shop.module.settings.SettingsActivity

class MineFragment : BaseFragment() {
    override fun layoutResId() = R.layout.fragment_mine
    override fun initListeners() {
        findViewById<View>(R.id.settingsIv).setOnClickListener {
            startActivity(Intent(context, SettingsActivity::class.java))
        }
    }
}