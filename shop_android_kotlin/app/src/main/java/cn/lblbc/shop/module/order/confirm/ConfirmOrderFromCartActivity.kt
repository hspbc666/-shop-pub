package cn.lblbc.shop.module.order.confirm

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.addr.AddAddressActivity
import cn.lblbc.shop.module.addr.select.SelectAddressActivity
import cn.lblbc.shop.module.order.confirm.dialog.CartGoodsListDialog
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.network.response.UserAddr
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_CART_ITEMS
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_COST_SUM
import cn.lblbc.shop.utils.JsonUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods.*
import kotlinx.android.synthetic.main.order_addr_layout.*
import kotlinx.android.synthetic.main.order_bottom_layout.*
import kotlinx.android.synthetic.main.order_fee_layout.*
import kotlinx.android.synthetic.main.order_goods_info_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
open class ConfirmOrderFromCartActivity : BaseVmActivity<ConfirmOrderViewModel>() {
    private var cartItemList: List<CartItem>? = null
    private var userAddr: UserAddr? = null
    override fun viewModelClass() = ConfirmOrderViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_confirm_order

    override fun initView() {
        initToolbar()
    }

    override fun initData() {
        val cartItemsInJson = intent.getStringExtra(EXTRA_KEY_CART_ITEMS)
        cartItemList = JsonUtil.fromJsonList(cartItemsInJson!!)
        val sum = intent.getStringExtra(EXTRA_KEY_COST_SUM)
        goodsSumTv.text = sum
        sumTv.text = sum
        mViewModel.queryDefaultAddress()
        cartItemList?.let {
            if (it.isNotEmpty()) {
                val picUrl = it.first().squarePic
                Glide.with(this).load(picUrl).into(orderGoodsIv)
            }
            packagesTv.text = getString(R.string.package_count, it.size)
        }
    }

    override fun initListeners() {
        createOrderTv.setOnClickListener {
            cartItemList?.let { createOrder(it) }
        }

        addAddrLayout.setOnClickListener {
            val intent = Intent(this, AddAddressActivity::class.java)
            startActivityForResult(intent, requestCodeForAddAddr)
        }
        addrLayout.setOnClickListener {
            userAddr?.let {
                val intent = Intent(this, SelectAddressActivity::class.java)
                intent.putExtra(Constants.EXTRA_KEY_USER_ADDR_ID, it.id)
                startActivityForResult(intent, requestCodeForSelectAddr)
            }
        }
        orderGoodsLayout.setOnClickListener {
            cartItemList?.let { CartGoodsListDialog(this, it).show() }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeForSelectAddr || requestCode == requestCodeForAddAddr) {
            data?.let {
                val userAddrJson = it.getStringExtra(Constants.EXTRA_KEY_USER_ADDR)
                userAddr = JsonUtil.fromJson(userAddrJson ?: "")
                updateUserAddr()
            }
        }
    }

    private fun createOrder(cartItemList: List<CartItem>) {
        mViewModel.createOrderFromCart(cartItemList, onSuccess = {
            showPayDialog(it)
        })
    }

    private fun showPayDialog(orderId: String) {
        val message = "现在支付么？"
        val alertDialog = AlertDialog.Builder(this).setMessage(message).setCancelable(false)
            .setPositiveButton(R.string.pay)
            { _, _ ->
                pay(orderId)
            }
            .setNegativeButton(R.string.cancel) { _, _ ->
                closeAndGotoOrderDetailPage(orderId)
            }
            .create()
        alertDialog.show()
    }

    private fun closeAndGotoOrderDetailPage(orderId: String) {
        mViewModel.queryOrder(orderId, onSuccess = {
            val intent = Intent(this, OrderDetailActivity::class.java)
            val orderInfoInJson = JsonUtil.toJson(it)
            intent.putExtra(Constants.EXTRA_KEY_ORDER_INFO, orderInfoInJson)
            startActivity(intent)
        })
        finish()
    }

    private fun pay(orderId: String) {
        mViewModel.payForOrder(orderId, onSuccess = {
            closeAndGotoOrderDetailPage(orderId)
        })
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun observe() {
        mViewModel.defaultAddress.observe(this, {
            userAddr = it
            updateUserAddr()
        })
    }

    private fun updateUserAddr() {
        userAddr?.let {
            addrLayout.visibility = View.VISIBLE
            addAddrLayout.visibility = View.GONE
            receiverNameTv.text = it.name
            addressTv.text = it.address
        }
    }

    companion object {
        var requestCodeForSelectAddr = 1
        var requestCodeForAddAddr = 2
    }
}
