package cn.lblbc.shop.module.order.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.view.TabBean
import cn.lblbc.shop.R
import cn.lblbc.shop.utils.EXTRA_KEY_TAB_INDEX
import cn.lblbc.shop.utils.ORDER_STATUS_ALL
import kotlinx.android.synthetic.main.activity_order_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        initListeners()
        initViews()
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
    }

    private fun initViews() {
        val tabIndex = intent.getIntExtra(EXTRA_KEY_TAB_INDEX, ORDER_STATUS_ALL)

        val tabBeanList = listOf(
            TabBean("0", "全部"),
            TabBean("1", "待付款"),
            TabBean("2", "待发货"),
            TabBean("3", "待收货"),
            TabBean("4", "待评价"),
            TabBean("5", "退换/售后"),
        )
        lblViewPager.init(this, tabBeanList, tabIndex) {
            OrderFragment.newInstance(it)
        }
    }
}