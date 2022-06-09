package cn.lblbc.shop.module.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.lblbc.shop.R
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.order.list.OrderListActivity
import cn.lblbc.shop.module.settings.SettingsActivity
import cn.lblbc.shop.utils.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class MineFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mine, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        settingsIv = view.findViewById(R.id.settingsIv)
        userLogoIv = view.findViewById(R.id.userLogoIv)
        userNameTv = view.findViewById(R.id.userNameTv)
        userInfoLayout = view.findViewById(R.id.userInfoLayout)
        allOrderTv = view.findViewById(R.id.allOrderTv)
        toPayTv = view.findViewById(R.id.toPayTv)
        toDeliverTv = view.findViewById(R.id.toDeliverTv)
        toReceiveTv = view.findViewById(R.id.toReceiveTv)
        toCommentTv = view.findViewById(R.id.toCommentTv)
        toReturnTv = view.findViewById(R.id.afterSalesTv)
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
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

    private fun gotoOrderPage(tabIndex: Int) {
        val intent = Intent(context, OrderListActivity::class.java)
        intent.putExtra(EXTRA_KEY_TAB_INDEX, tabIndex)
        startActivity(intent)
    }

    private fun gotoLoginPage() {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    private fun gotoSettingsPage() {
        startActivity(Intent(context, SettingsActivity::class.java))
    }
}