package cn.lblbc.shop.module.home

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.lblbc.lib.view.LblViewPager
import cn.lblbc.lib.view.TabBean
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.search.SearchActivity
import cn.lblbc.shop.network.CategoryInfo

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class HomeFragment : BaseVmFragment<HomeViewModel>() {
    private lateinit var topSearchLayout: View
    private lateinit var lblViewPager: LblViewPager

    override fun viewModelClass() = HomeViewModel::class.java
    override fun layoutResId() = R.layout.fragment_home

    override fun initView() {
        topSearchLayout = findViewById(R.id.topSearchLayout)
        lblViewPager = findViewById(R.id.lblViewPager)
    }

    override fun initListeners() {
        topSearchLayout.setOnClickListener { startActivity(Intent(context, SearchActivity::class.java)) }
    }

    override fun initData() {
        mViewModel.queryCategory()
    }

    override fun observe() {
        mViewModel.categoryInfoList.observe(this) {
            val tabBeanList = it.map { TabBean(it.id, it.name) }
            lblViewPager.init(requireActivity(), tabBeanList, 0) {
                GoodsListFragment.newInstance(it)
            }
        }
    }

    private inner class CategoryPagerAdapter(fm: FragmentManager, var tabList: List<CategoryInfo>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return GoodsListFragment.newInstance(tabList[position].id)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabList[position].name
        }

        override fun getCount(): Int {
            return tabList.size
        }
    }
}