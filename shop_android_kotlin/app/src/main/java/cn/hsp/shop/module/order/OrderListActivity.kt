package cn.hsp.shop.module.order

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.activity_order_list.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class OrderListActivity : BaseVmActivity<OrderListModel>() {
    override fun viewModelClass() = OrderListModel::class.java
    override fun layoutResId(): Int = R.layout.activity_order_list

    override fun initView() {
        initToolbar()
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }

    override fun initData() {

    }

    override fun initListeners() {

    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    //ViewPager适配器  10个Fragment
    private inner class MainPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return BlankFragment.newInstance(position)
        }

        //TabLayout会根据当前page的title自动绑定tab
        override fun getPageTitle(position: Int): CharSequence? {
            return "Tab $position"
        }

        override fun getCount(): Int {
            return 10
        }
    }
}
