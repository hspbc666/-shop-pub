package cn.hsp.shop.module.search

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import kotlinx.android.synthetic.main.part_search_top.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class SearchActivity : BaseVmActivity<SearchViewModel>() {
    override fun viewModelClass() = SearchViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_search

    override fun initData() {
    }

    override fun initListeners() {
        searchBackIv.setOnClickListener { finish() }
        searchEt.setOnEditorActionListener(TextView.OnEditorActionListener { _, keyCode, _ ->
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            true
        })
        searchTv.setOnClickListener { search() }
    }

    private fun search() {
        val keyword = searchEt.text.toString()
        mViewModel.queryGoods(keyword)
    }

    override fun observe() {
        mViewModel.goodsList.observe(this, Observer {
//            adapter.setData(it)
            val list = it
            val xx = 1
        })
    }
}
