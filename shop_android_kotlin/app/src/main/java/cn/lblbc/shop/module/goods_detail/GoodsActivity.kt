package cn.lblbc.shop.module.goods_detail

import android.content.Intent
import android.widget.ImageView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.login.LoginManager
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity
import cn.lblbc.shop.network.request.SimpleOrderInfo
import cn.lblbc.shop.network.response.Goods
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_GOODS_ID
import cn.lblbc.shop.utils.JsonUtil
import cn.lblbc.shop.utils.getMoneyByYuan
import cn.lblbc.shop.utils.toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.part_goods_bottom.*
import kotlinx.android.synthetic.main.part_goods_detail_info.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class GoodsActivity : BaseVmActivity<GoodsViewModel>() {
    private var goodsId = ""
    private var price = ""
    private var goods: Goods? = null
    override fun viewModelClass() = GoodsViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_goods

    override fun initView() {
        initToolbar()
        mViewModel.goods.observe(this, {
            goods = it
            price = getString(R.string.price, getMoneyByYuan(it.price))
            goodsNameTv.text = it.name
            goodsPriceTv.text = price
            it.squarePic?.let { loadImage(goodsIv, it) }
        })
    }

    override fun initData() {
        goodsId = intent.getStringExtra(EXTRA_KEY_GOODS_ID) ?: ""
        mViewModel.queryGoods(goodsId)
    }

    override fun initListeners() {
        addToCartTv.setOnClickListener {
            if (LoginManager.isLoggedIn()) {
                mViewModel.addToCart(goodsId, onSuccess = {
                    toast("已加入购物车")
                })
            } else {
                startActivity(Intent(this@GoodsActivity, LoginActivity::class.java))
            }
        }
        buyTv.setOnClickListener {
            if (LoginManager.isLoggedIn()) {
                val intent = Intent(this, ConfirmOrderActivity::class.java)
                val simpleOrderInfo = SimpleOrderInfo(goodsId, 1, goods?.squarePic)
                val orderInJson = JsonUtil.toJson(simpleOrderInfo)
                intent.putExtra(Constants.EXTRA_KEY_SIMPLE_ORDER, orderInJson)
                intent.putExtra(Constants.EXTRA_KEY_COST_SUM, price)
                startActivity(intent)
            } else {
                startActivity(Intent(this@GoodsActivity, LoginActivity::class.java))
            }
        }
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
