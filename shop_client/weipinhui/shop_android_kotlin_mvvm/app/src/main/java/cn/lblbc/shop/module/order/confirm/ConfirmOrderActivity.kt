package cn.lblbc.shop.module.order.confirm

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.addr.AddAddressActivity
import cn.lblbc.shop.module.addr.select.SelectAddressActivity
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.CreateOrderRequest
import cn.lblbc.shop.network.SimpleCartItem
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.network.OrderDetail
import cn.lblbc.shop.utils.*
import kotlinx.android.synthetic.main.activity_confirm_order.*
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.order_addr_layout.*
import kotlinx.android.synthetic.main.order_bottom_layout.*
import kotlinx.android.synthetic.main.order_fee_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
open class ConfirmOrderActivity : BaseVmActivity<ConfirmOrderViewModel>() {
    private lateinit var mOrderInfoList: List<OrderDetail>
    private var mAddress: Address? = null
    override fun viewModelClass() = ConfirmOrderViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_confirm_order

    override fun initView() {
        initToolbar()
    }

    override fun initData() {
        val orderInJson = intent.getStringExtra(EXTRA_KEY_SIMPLE_ORDER) ?: ""
        mOrderInfoList = JsonUtil.fromJsonList(orderInJson)!!
        orderListView.setData(mOrderInfoList)

        val sum = intent.getStringExtra(EXTRA_KEY_COST_SUM)
        goodsMoneyTv.text = sum
        moneyTv.text = sum
        mViewModel.queryDefaultAddress()
    }

    override fun initListeners() {
        createOrderTv.setOnClickListener { createOrder() }
        addAddrLayout.setOnClickListener {
            val intent = Intent(this, AddAddressActivity::class.java)
            startActivityForResult(intent, requestCodeForAddAddr)
        }
        addrLayout.setOnClickListener {
            mAddress?.let {
                val intent = Intent(this, SelectAddressActivity::class.java)
                intent.putExtra(EXTRA_KEY_ADDRESS_ID, it.id)
                startActivityForResult(intent, requestCodeForSelectAddr)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeForSelectAddr || requestCode == requestCodeForAddAddr) {
            data?.let {
                val addressJson = it.getStringExtra(EXTRA_KEY_ADDRESS) ?: ""
                mAddress = JsonUtil.fromJson(addressJson)
                updateAddress()
            }
        }
    }

    private fun createOrder(goodsId: String) {
    }

    private fun createOrder() {
        val simpleCartItemList = mOrderInfoList.map { SimpleCartItem(it.goodsId, it.quantity) }
        val request = CreateOrderRequest(simpleCartItemList, mAddress?.id ?: "")
        mViewModel.createOrder(request) { closeAndGotoOrderDetailPage(it) }

    }


    private fun closeAndGotoOrderDetailPage(orderId: String) {
        val intent = Intent(this, OrderDetailActivity::class.java)
        intent.putExtra(EXTRA_KEY_ORDER_ID, orderId)
        startActivity(intent)
        finish()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun observe() {
        mViewModel.defaultAddress.observe(this) {
            mAddress = it
            updateAddress()
        }
    }

    private fun updateAddress() {
        mAddress?.let {
            addrLayout.visibility = VISIBLE
            addAddrLayout.visibility = GONE
            receiverNameTv.text = it.name
            addressTv.text = it.address
        }
    }

    companion object {
        var requestCodeForSelectAddr = 1
        var requestCodeForAddAddr = 2
    }
}
