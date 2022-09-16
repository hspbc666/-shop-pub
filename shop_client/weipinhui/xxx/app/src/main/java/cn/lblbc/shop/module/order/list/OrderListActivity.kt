package cn.lblbc.shop.module.order.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.lblbc.lib.view.TabBean
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.utils.EXTRA_KEY_TAB_INDEX
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_ALL
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.activity_order_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderListActivity : BaseVmActivity<OrderListViewModel>() {
    private lateinit var tabList: List<TabBean>
    override fun viewModelClass() = OrderListViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_order_list

    override fun initView() {
        initTabs()
        initToolbar()
    }

    private fun initTabs() {
        val tabIndex = intent.getIntExtra(EXTRA_KEY_TAB_INDEX, ORDER_STATUS_ALL)

        tabList = listOf(
            TabBean("0", "全部"),
            TabBean("1", "待付款"),
            TabBean("2", "待发货"),
            TabBean("3", "待收货"),
            TabBean("4", "待评价"),
            TabBean("5", "退换/售后"),
        )
        lblViewPager.init(this, tabList, tabIndex) {
            OrderFragment.newInstance(it)
        }
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    private inner class OrderPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return OrderFragment.newInstance(tabList[position].id)
        }

        override fun getPageTitle(position: Int): String {
            return tabList[position].name
        }

        override fun getCount(): Int {
            return tabList.size
        }
    }

    class OrderTab(var name: String, var tabId: String)
}
