package cn.lblbc.shop.module.order.confirm

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.addr.AddAddressActivity
import cn.lblbc.shop.module.addr.select.SelectAddressActivity
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.request.SimpleOrderInfo
import cn.lblbc.shop.network.response.Address
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_COST_SUM
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_SIMPLE_ORDER
import cn.lblbc.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.activity_confirm_order.*
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.order_addr_layout.*
import kotlinx.android.synthetic.main.order_bottom_layout.*
import kotlinx.android.synthetic.main.order_fee_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
open class ConfirmOrderActivity : BaseVmActivity<ConfirmOrderViewModel>() {
    private lateinit var orderInfo: SimpleOrderInfo
    private var address: Address? = null
    override fun viewModelClass() = ConfirmOrderViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_confirm_order

    override fun initView() {
        initToolbar()
    }

    override fun initData() {
        val orderInJson = intent.getStringExtra(EXTRA_KEY_SIMPLE_ORDER) ?: ""
        orderInfo = JsonUtil.fromJson(orderInJson)
        val sum = intent.getStringExtra(EXTRA_KEY_COST_SUM)
        goodsSumTv.text = sum
        sumTv.text = sum
        mViewModel.queryDefaultAddress()
        orderListView.setDataBySimpleOrderInfo(orderInfo)
    }

    override fun initListeners() {
        createOrderTv.setOnClickListener {
            createOrder(orderInfo.goodsId)
        }
        addAddrLayout.setOnClickListener {
            val intent = Intent(this, AddAddressActivity::class.java)
            startActivityForResult(intent, requestCodeForAddAddr)
        }
        addrLayout.setOnClickListener {
            address?.let {
                val intent = Intent(this, SelectAddressActivity::class.java)
                intent.putExtra(Constants.EXTRA_KEY_USER_ADDR_ID, it.id)
                startActivityForResult(intent, requestCodeForSelectAddr)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeForSelectAddr || requestCode == requestCodeForAddAddr) {
            data?.let {
                val addressJson = it.getStringExtra(Constants.EXTRA_KEY_USER_ADDR) ?: ""
                address = JsonUtil.fromJson(addressJson)
                updateAddress()
            }
        }
    }

    private fun createOrder(goodsId: String) {
        mViewModel.createOrder(goodsId, address?.id ?: "") { closeAndGotoOrderDetailPage(it) }
    }

    private fun closeAndGotoOrderDetailPage(orderId: String) {
        val intent = Intent(this, OrderDetailActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_ORDER_ID, orderId)
        startActivity(intent)
        finish()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun observe() {
        mViewModel.defaultAddress.observe(this) {
            address = it
            updateAddress()
        }
    }

    private fun updateAddress() {
        address?.let {
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
