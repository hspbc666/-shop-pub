package cn.hsp.shop.module.mine

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseFragment
import cn.hsp.shop.module.login.LoginActivity
import cn.hsp.shop.module.login.LoginManager
import cn.hsp.shop.module.order.list.OrderListActivity
import cn.hsp.shop.module.settings.SettingsActivity
import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_ALL
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_COMMENT
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_DELIVER
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_PAY
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_RECEIVE
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_RETURN

class MineFragment : BaseFragment() {
    private lateinit var userLogoIv: ImageView
    private lateinit var userNameTv: TextView
    private lateinit var userInfoLayout: View
    private lateinit var allOrderTv: View
    private lateinit var toPayTv: View
    private lateinit var toDeliverTv: View
    private lateinit var toReceiveTv: View
    private lateinit var toCommentTv: View
    private lateinit var toReturnTv: View
    override fun layoutResId() = R.layout.fragment_mine

    override fun initView() {
        userLogoIv = findViewById(R.id.userLogoIv)
        userNameTv = findViewById(R.id.userNameTv)
        userInfoLayout = findViewById(R.id.userInfoLayout)
        allOrderTv = findViewById(R.id.allOrderTv)
        toPayTv = findViewById(R.id.toPayTv)
        toDeliverTv = findViewById(R.id.toDeliverTv)
        toReceiveTv = findViewById(R.id.toReceiveTv)
        toCommentTv = findViewById(R.id.toCommentTv)
        toReturnTv = findViewById(R.id.afterSalesTv)
    }

    private fun refreshPage() {
        if (LoginManager.isLoggedIn()) {
            userLogoIv.setImageResource(R.drawable.ic_user_loggedin)
            userNameTv.text = "尊贵会员"
        } else {
            userLogoIv.setImageResource(R.drawable.ic_user_default)
            userNameTv.text = "登录/注册 >"
        }

        if (LoginManager.isLoggedIn()) {
            userInfoLayout.setOnClickListener { }
            allOrderTv.setOnClickListener { jumpToOrder(ORDER_TAB_ALL) }
            toPayTv.setOnClickListener { jumpToOrder(ORDER_TAB_TO_PAY) }
            toDeliverTv.setOnClickListener { jumpToOrder(ORDER_TAB_TO_DELIVER)}
            toReceiveTv.setOnClickListener { jumpToOrder(ORDER_TAB_TO_RECEIVE)}
            toCommentTv.setOnClickListener { jumpToOrder(ORDER_TAB_TO_COMMENT)}
            toReturnTv.setOnClickListener { jumpToOrder(ORDER_TAB_TO_RETURN)}
        } else {
            userInfoLayout.setOnClickListener { gotoLoginPage() }
            allOrderTv.setOnClickListener { gotoLoginPage() }
            toPayTv.setOnClickListener { gotoLoginPage() }
            toDeliverTv.setOnClickListener { gotoLoginPage() }
            toReceiveTv.setOnClickListener { gotoLoginPage() }
            toCommentTv.setOnClickListener { gotoLoginPage() }
            toReturnTv.setOnClickListener { gotoLoginPage() }
        }
    }

    private fun jumpToOrder(tabIndex: Int) {
        val intent = Intent(context, OrderListActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_TAB_INDEX, tabIndex)
        startActivity(intent)
    }

    override fun initListeners() {
        findViewById<View>(R.id.settingsIv).setOnClickListener {
            startActivity(Intent(context, SettingsActivity::class.java))
        }
    }

    private fun gotoLoginPage() {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}