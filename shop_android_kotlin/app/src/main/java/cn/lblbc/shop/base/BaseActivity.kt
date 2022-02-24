package cn.lblbc.shop.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        initData()
        initView()
        initListeners()
    }

    protected abstract fun layoutResId(): Int

    open fun initView() {}

    open fun initData() {}

    open fun initListeners() {}
}
