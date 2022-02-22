package cn.lblbc.shop.module.category

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.search.SearchActivity
import cn.lblbc.shop.network.response.CategoryInfo

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CategoryFragment : BaseVmFragment<CategoryViewModel>() {
    private lateinit var homeSearchLayout: View
    private lateinit var viewPager: ViewPager

    override fun viewModelClass() = CategoryViewModel::class.java
    override fun layoutResId() = R.layout.fragment_category

    override fun initView() {
        homeSearchLayout = findViewById(R.id.homeSearchLayout)
        viewPager = findViewById(R.id.viewPager)
    }

    override fun initListeners() {
        homeSearchLayout.setOnClickListener { startActivity(Intent(context, SearchActivity::class.java)) }
    }

    override fun initData() {
        mViewModel.queryCategory()
    }

    override fun observe() {
        mViewModel.categoryInfoList.observe(this) {
            viewPager.adapter = CategoryPagerAdapter(activity!!.supportFragmentManager, it)
        }
    }

    private inner class CategoryPagerAdapter(fm: FragmentManager, var tabList: List<CategoryInfo>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return CategoryGoodsFragment.newInstance(tabList[position].id)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabList[position].name
        }

        override fun getCount(): Int {
            return tabList.size
        }
    }
}