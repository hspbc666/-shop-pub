package cn.lblbc.shop.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.lblbc.shop.R
import cn.lblbc.shop.module.cart.CartFragment
import cn.lblbc.shop.module.home.HomeFragment
import cn.lblbc.shop.module.mine.MineFragment
import kotlinx.android.synthetic.main.activity_lbl.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class MainActivity : AppCompatActivity() {
    private var lastIndex = 0
    private var mFragments = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lbl)
        initBottomNavigation()
        initData()
    }

    private fun initData() {
        mFragments = ArrayList()
        mFragments.add(HomeFragment())
        mFragments.add(CartFragment())
        mFragments.add(MineFragment())
        setFragmentPosition(0)
    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> setFragmentPosition(0)
                R.id.menu_cart -> setFragmentPosition(1)
                R.id.menu_mine -> setFragmentPosition(2)
            }
            true
        }
    }

    private fun setFragmentPosition(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.ll_frameLayout, currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }
}