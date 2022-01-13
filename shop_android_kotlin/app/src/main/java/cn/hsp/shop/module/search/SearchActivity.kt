package cn.hsp.shop.module.search

import android.widget.ImageView
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.part_search_top.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class SearchActivity : BaseVmActivity<SearchViewModel>() {
    private var goodsId = ""
    private var price = 0L
    override fun viewModelClass() = SearchViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_search

    override fun initView() {
        searchBackIv.setOnClickListener { finish() }

    }

    override fun initData() {
    }

    override fun initListeners() {

    }

    private fun loadImage(goodsIv: ImageView, url: String) {
        Glide.with(this)
            .load(url)
            .into(goodsIv)
    }

}
