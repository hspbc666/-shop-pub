package cn.hsp.shop.module.goods_detail

import android.widget.ImageView
import androidx.lifecycle.Observer
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import cn.hsp.shop.utils.Constants.EXTRA_KEY_GOODS_ID
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class GoodsActivity : BaseVmActivity<GoodsViewModel>() {
    private var goodsId = ""
    override fun viewModelClass() = GoodsViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_goods

    override fun initView() {
        initToolbar()
        mViewModel.goods.observe(this, Observer {
            goodsNameTv.text = it.name
            priceTv.text = getString(R.string.price, it.price.toString())
            it.pics?.let { loadImage(goodsIv, it) }
        })
    }

    override fun initData() {
        goodsId = intent.getStringExtra(EXTRA_KEY_GOODS_ID) ?: ""
        mViewModel.queryGoods(goodsId)
    }

    private fun loadImage(goodsIv: ImageView, url: String) {
        Glide.with(this)
            .load(url)
            .into(goodsIv)
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }
}
