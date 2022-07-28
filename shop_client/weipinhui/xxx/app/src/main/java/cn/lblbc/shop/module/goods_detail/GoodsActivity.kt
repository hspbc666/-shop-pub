package cn.lblbc.shop.module.goods_detail

import android.content.Intent
import android.widget.ImageView
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.login.LoginManager
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity
import cn.lblbc.shop.network.Goods
import cn.lblbc.shop.network.OrderDetail
import cn.lblbc.shop.utils.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.part_goods_bottom.*
import kotlinx.android.synthetic.main.part_goods_detail_info.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class GoodsActivity : BaseVmActivity<GoodsViewModel>() {
    private var goodsId = ""
    private var price = ""
    private var mGoods: Goods? = null
    override fun viewModelClass() = GoodsViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_goods

    override fun initView() {
        initToolbar()
        mViewModel.goods.observe(this) {
            mGoods = it
            price = getString(R.string.price, getMoneyByYuan(it.price))
            goodsNameTv.text = it.name
            goodsPriceTv.text = price
            loadImage(goodsIv, it.squarePic)
            loadImage(descIv, it.descPic)
        }
    }

    override fun initData() {
        goodsId = intent.getStringExtra(EXTRA_KEY_GOODS_ID) ?: ""
        mViewModel.queryGoods(goodsId)
    }

    override fun initListeners() {
        addToCartTv.setOnClickListener {
            if (LoginManager.isLoggedIn()) {
                mViewModel.addToCart(goodsId) { toast("已加入购物车") }
            } else {
                startActivity(Intent(this@GoodsActivity, LoginActivity::class.java))
            }
        }
        buyTv.setOnClickListener {
            if (LoginManager.isLoggedIn()) {
                mGoods?.let { gotoConfirmOrder(it) }
            } else {
                gotoLogin()
            }
        }
    }


    private fun gotoLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun gotoConfirmOrder(it: Goods) {
        val price = cn.lblbc.lib.utils.getMoneyByYuan(it.price)
        val intent = Intent(this, ConfirmOrderActivity::class.java)
        val orderDetail = OrderDetail(goodsId, it.name, it.price, it.squarePic, 1)
        val orderInJson = JsonUtil.toJson(listOf(orderDetail))
        intent.putExtra(EXTRA_KEY_SIMPLE_ORDER, orderInJson)
        intent.putExtra(EXTRA_KEY_MONEY, price)
        startActivity(intent)
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(this).load(url).into(imageView)
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }
}
