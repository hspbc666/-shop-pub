package cn.hsp.shop.module.category

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.network.response.CategoryInfo

class CategoryFragment() : BaseVmFragment<CategoryViewModel>() {
    private lateinit var viewPager: ViewPager

    override fun viewModelClass() = CategoryViewModel::class.java
    override fun layoutResId() = R.layout.fragment_category

    override fun initView() {
        viewPager = findViewById(R.id.viewPager)
    }

    override fun initData() {
        mViewModel.queryCategory()
    }

    override fun observe() {
        mViewModel.categoryInfoList.observe(this, {
            viewPager.adapter = CategoryPagerAdapter(activity!!.supportFragmentManager, it)
        })
    }

    private inner class CategoryPagerAdapter(fm: FragmentManager, var tabList: List<CategoryInfo>) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return CategoryGoodsFragment.newInstance(tabList[position].id)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabList[position].name
        }

        override fun getCount(): Int {
            return tabList.size
        }
    }
}