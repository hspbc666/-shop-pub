package cn.lblbc.shop.module.goods_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.*
import cn.lblbc.lib.utils.ToastUtil.toast
import cn.lblbc.shop.R
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.mine.LoginManager
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity
import cn.lblbc.shop.network.AddToCartRequest
import cn.lblbc.shop.network.Goods
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.network.OrderDetail
import cn.lblbc.shop.utils.EXTRA_KEY_GOODS_ID
import cn.lblbc.shop.utils.EXTRA_KEY_MONEY
import cn.lblbc.shop.utils.EXTRA_KEY_SIMPLE_ORDER
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.part_goods_bottom.*
import kotlinx.android.synthetic.main.part_goods_detail_info.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class GoodsActivity : AppCompatActivity() {
    private var goodsId = ""
    private var goodsUrl = ""
    private var mGoods: Goods? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        goodsId = intent.getStringExtra(EXTRA_KEY_GOODS_ID) ?: ""
        initListeners()
        queryData()
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }

        addToCartTv.setOnClickListener {
            if (LoginManager.isLoggedIn()) {
                addToCart()
            } else {
                gotoLogin()
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
        val price = getMoneyByYuan(it.price)
        val intent = Intent(this, ConfirmOrderActivity::class.java)
        val orderDetail = OrderDetail(goodsId, it.name, it.price, it.squarePic, 1)
        val orderInJson = JsonUtil.toJson(listOf(orderDetail))
        intent.putExtra(EXTRA_KEY_SIMPLE_ORDER, orderInJson)
        intent.putExtra(EXTRA_KEY_MONEY, price)
        startActivity(intent)
    }

    private fun addToCart() {
        launch(
            action = { NetworkRepository.apiService.addToCart(AddToCartRequest(goodsId)) },
            onSuccess = { toast("已加入购物车") }
        )
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryGoods(goodsId) },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(goods: Goods) {
        mGoods = goods
        val price = getMoneyByYuan(goods.price)
        goodsNameTv.text = goods.name
        goodsPriceTv.text = price
        descIv.setData(goods.descPic)
        loadImage(this, goodsIv, goods.squarePic)
        goodsUrl = goods.originalLink
    }
}
