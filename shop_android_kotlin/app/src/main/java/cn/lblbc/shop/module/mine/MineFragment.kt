package cn.lblbc.shop.module.mine

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseFragment
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.login.LoginManager
import cn.lblbc.shop.module.order.list.OrderListActivity
import cn.lblbc.shop.module.settings.SettingsActivity
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_ALL
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_COMMENT
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_DELIVER
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_PAY
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_RECEIVE
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_RETURN

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class MineFragment : BaseFragment() {
    private lateinit var settingsIv: ImageView
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
        settingsIv = findViewById(R.id.settingsIv)
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
            userNameTv.text = "尊贵会员"
        } else {
            userNameTv.text = "登录/注册 >"
        }

        if (LoginManager.isLoggedIn()) {
            settingsIv.setOnClickListener { gotoSettingsPage() }
            userInfoLayout.setOnClickListener { }
            allOrderTv.setOnClickListener { gotoOrderPage(ORDER_STATUS_ALL) }
            toPayTv.setOnClickListener { gotoOrderPage(ORDER_STATUS_TO_PAY) }
            toDeliverTv.setOnClickListener { gotoOrderPage(ORDER_STATUS_TO_DELIVER) }
            toReceiveTv.setOnClickListener { gotoOrderPage(ORDER_STATUS_TO_RECEIVE) }
            toCommentTv.setOnClickListener { gotoOrderPage(ORDER_STATUS_TO_COMMENT) }
            toReturnTv.setOnClickListener { gotoOrderPage(ORDER_STATUS_TO_RETURN) }
        } else {
            settingsIv.setOnClickListener { gotoLoginPage() }
            userInfoLayout.setOnClickListener { gotoLoginPage() }
            allOrderTv.setOnClickListener { gotoLoginPage() }
            toPayTv.setOnClickListener { gotoLoginPage() }
            toDeliverTv.setOnClickListener { gotoLoginPage() }
            toReceiveTv.setOnClickListener { gotoLoginPage() }
            toCommentTv.setOnClickListener { gotoLoginPage() }
            toReturnTv.setOnClickListener { gotoLoginPage() }
        }
    }

    private fun gotoSettingsPage() {
        startActivity(Intent(context, SettingsActivity::class.java))
    }

    private fun gotoOrderPage(tabIndex: Int) {
        val intent = Intent(context, OrderListActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_TAB_INDEX, tabIndex)
        startActivity(intent)
    }

    private fun gotoLoginPage() {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}