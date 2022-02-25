package cn.lblbc.shop.module.order.list

import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.utils.EXTRA_KEY_TAB_INDEX
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_ALL
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_COMMENT
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_DELIVER
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_PAY
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_RECEIVE
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_RETURN
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.activity_order_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderListActivity : BaseVmActivity<OrderListViewModel>() {
    private lateinit var tabList: List<OrderTab>
    override fun viewModelClass() = OrderListViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_order_list

    override fun initView() {
        val tabIndex = intent.getIntExtra(EXTRA_KEY_TAB_INDEX, ORDER_STATUS_ALL)
        initTabs()
        initToolbar()
        viewPager.adapter = OrderPagerAdapter(supportFragmentManager)
        viewPager.currentItem = tabIndex
    }

    private fun initTabs() {
        listOf(
            OrderTab("全部", ORDER_STATUS_ALL),
            OrderTab("待付款", ORDER_STATUS_TO_PAY),
            OrderTab("待发货", ORDER_STATUS_TO_DELIVER),
            OrderTab("待收货", ORDER_STATUS_TO_RECEIVE),
            OrderTab("待评价", ORDER_STATUS_TO_COMMENT),
            OrderTab("退换/售后", ORDER_STATUS_TO_RETURN),
        ).also { tabList = it }
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    private inner class OrderPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return OrderFragment.newInstance(tabList[position].tabId)
        }

        override fun getPageTitle(position: Int): String {
            return tabList[position].name
        }

        override fun getCount(): Int {
            return tabList.size
        }
    }

    class OrderTab(var name: String, var tabId: Int)
}
