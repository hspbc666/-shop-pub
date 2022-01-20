package cn.hsp.shop.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.hsp.shop.R
import cn.hsp.shop.module.cart.CartFragment
import cn.hsp.shop.module.category.CategoryFragment
import cn.hsp.shop.module.home.HomeFragment
import cn.hsp.shop.module.mine.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class HomeActivity : AppCompatActivity() {
    private var lastIndex = 0
    private var mFragments = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        initData()
    }

    private fun initData() {
        mFragments = ArrayList()
        mFragments.add(HomeFragment())
        mFragments.add(CategoryFragment())
        mFragments.add(CartFragment())
        mFragments.add(MineFragment())
        setFragmentPosition(0)
    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> setFragmentPosition(0)
                R.id.menu_category -> setFragmentPosition(1)
                R.id.menu_cart -> setFragmentPosition(2)
                R.id.menu_mine -> setFragmentPosition(3)
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