package cn.lblbc.shop.module.order.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.formatTime
import cn.lblbc.lib.utils.launch
import cn.lblbc.lib.utils.showDeleteDialog
import cn.lblbc.shop.R
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.network.OrderInfo
import cn.lblbc.shop.utils.EXTRA_KEY_ORDER_ID
import cn.lblbc.shop.utils.calcSum1
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.order_detail_bottom_layout.*
import kotlinx.android.synthetic.main.order_detail_fee_layout.*
import kotlinx.android.synthetic.main.order_detail_order_info_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderDetailActivity : AppCompatActivity() {
    private var orderId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        orderId = intent.getStringExtra(EXTRA_KEY_ORDER_ID) ?: ""
        initListeners()
        queryData()
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryOrder(orderId) },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: OrderInfo) {
        orderIdTv.text = data.orderId
        data.address?.let { receiverInfoTv.text = it.name + "," + it.phone + "," + it.address }
        val sum = calcSum1(data.list)
        orderDetailMoneyTv.text = sum
        realPaidTv.text = "实付 " + sum
        orderCreateTimeTv.text = formatTime(data.createTime, "yyyy-MM-dd HH:mm")
        orderListView.setData(data.list)
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener { finish() }
        deleteOrderTv.setOnClickListener {
            val content = "确认删除当前订单么？"
            showDeleteDialog(content) { deleteOrder(orderId) }
        }
    }

    private fun deleteOrder(orderId: String) {
        launch(
            action = { NetworkRepository.apiService.deleteOrder(orderId) },
            onSuccess = { finish() }
        )
    }
}