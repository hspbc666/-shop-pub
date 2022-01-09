package cn.hsp.shop.module.order

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import cn.hsp.shop.utils.Constants.ORDER_TAB_ALL
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_COMMENT
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_DELIVER
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_PAY
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_RECEIVE
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.activity_order_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class OrderListActivity : BaseVmActivity<OrderListModel>() {
    private lateinit var tabList: List<OrderTab>
    override fun viewModelClass() = OrderListModel::class.java
    override fun layoutResId(): Int = R.layout.activity_order_list

    override fun initView() {
        initTabs()
        initToolbar()
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }

    private fun initTabs() {
        tabList = listOf(
            OrderTab("全部", ORDER_TAB_ALL),
            OrderTab("待付款", ORDER_TAB_TO_PAY),
            OrderTab("待发货", ORDER_TAB_TO_DELIVER),
            OrderTab("待收货", ORDER_TAB_TO_RECEIVE),
            OrderTab("待评价", ORDER_TAB_TO_COMMENT),
        )
    }

    override fun initData() {

    }

    override fun initListeners() {

    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    private inner class MainPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return OrderFragment.newInstance(position)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabList[position].name
        }

        override fun getCount(): Int {
            return tabList.size
        }
    }

    class OrderTab(var name: String, var tabId: Int)
}
