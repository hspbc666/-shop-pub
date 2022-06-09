package cn.lblbc.shop.module.order.confirm

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.hide
import cn.lblbc.lib.utils.launch
import cn.lblbc.lib.utils.show
import cn.lblbc.shop.R
import cn.lblbc.shop.module.addr.AddAddressActivity
import cn.lblbc.shop.module.addr.SelectAddressActivity
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.*
import cn.lblbc.shop.utils.*
import kotlinx.android.synthetic.main.activity_confirm_order.*
import kotlinx.android.synthetic.main.order_addr_layout.*
import kotlinx.android.synthetic.main.order_bottom_layout.*
import kotlinx.android.synthetic.main.order_fee_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class ConfirmOrderActivity : AppCompatActivity() {
    private lateinit var mOrderInfoList: List<OrderDetail>
    private var mAddress: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)
        intViews()
        initListeners()
    }

    private fun intViews() {
        val orderInJson = intent.getStringExtra(EXTRA_KEY_SIMPLE_ORDER) ?: ""
        mOrderInfoList = JsonUtil.fromJsonList(orderInJson)!!
        orderListView.setData(mOrderInfoList)

        val moneyText = intent.getStringExtra(EXTRA_KEY_MONEY)!!
        goodsMoneyTv.text = moneyText
        moneyTv.text = moneyText

        initDefaultAddress()
    }

    private fun initDefaultAddress() {
        launch(
            action = { NetworkRepository.apiService.queryDefaultAddress() },
            onSuccess = { it?.data?.let { data -> processAddressResponse(data) } }
        )
    }

    private fun processAddressResponse(data: Address) {
        mAddress = data
        updateAddress()
    }

    private fun updateAddress() {
        mAddress?.let {
            addrLayout.show()
            addAddrLayout.hide()
            receiverNameTv.text = it.name
            addressTv.text = it.address
        }
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
        createOrderTv.setOnClickListener { createOrder() }

        addAddrLayout.setOnClickListener {
            gotoAddAddress()
        }
        addrLayout.setOnClickListener { mAddress?.let { gotoSelectAddress(it) } }
    }

    private fun gotoAddAddress() {
        myActivityLauncher.launch(Intent(this, AddAddressActivity::class.java))
    }

    private fun gotoSelectAddress(address: Address) {
        val intent = Intent(this, SelectAddressActivity::class.java)
        intent.putExtra(EXTRA_KEY_ADDRESS_ID, address.id)
        myActivityLauncher.launch(intent)
    }

    private val myActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        activityResult.data?.let {
            val addressJson = it.getStringExtra(EXTRA_KEY_ADDRESS) ?: ""
            mAddress = JsonUtil.fromJson(addressJson)
            updateAddress()
        }
    }

    private fun createOrder() {
        val simpleCartItemList = mOrderInfoList.map { SimpleCartItem(it.goodsId, it.quantity) }
        val request = CreateOrderRequest(simpleCartItemList, mAddress?.id ?: "")
        launch(
            action = { NetworkRepository.apiService.createOrder(request) },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: CreateOrderInfo) {
        val intent = Intent(this, OrderDetailActivity::class.java)
        intent.putExtra(EXTRA_KEY_ORDER_ID, data.orderId)
        startActivity(intent)
        finish()
    }
}