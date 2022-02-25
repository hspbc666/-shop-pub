package cn.lblbc.shop.module.order.detail

import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.network.response.OrderDetail
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_ORDER_ID
import cn.lblbc.shop.utils.formatTime
import cn.lblbc.shop.utils.getMoneyByYuan
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.order_detail_bottom_layout.*
import kotlinx.android.synthetic.main.order_detail_fee_layout.*
import kotlinx.android.synthetic.main.order_detail_order_info_layout.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
open class OrderDetailActivity : BaseVmActivity<OrderDetailViewModel>() {
    private var orderId = ""
    override fun viewModelClass() = OrderDetailViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_order_detail

    override fun initView() {
        initToolbar()
    }

    override fun initData() {
        orderId = intent.getStringExtra(EXTRA_KEY_ORDER_ID) ?: ""
        mViewModel.queryOrder(orderId)
    }

    override fun observe() {
        mViewModel.orderInfo.observe(this) { orderInfo ->
            orderInfo?.let {
                orderIdTv.text = it.orderId
                receiverInfoTv.text = it.address?.toSimpleInfo()
                val sum = calcSum(it.list)
                orderDetailSumTv.text = getString(R.string.price, sum)
                realPaidTv.text = getString(R.string.real_paid, sum)
                orderCreateTimeTv.text = formatTime(it.createTime)
                orderListView.setData(it.list)
            }
        }
    }

    override fun initListeners() {
        deleteOrderTv.setOnClickListener {
            deleteOrder(orderId)
        }
    }

    private fun deleteOrder(orderId: String) {
        mViewModel.deleteOrder(orderId) {
            finish()
        }
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun calcSum(data: List<OrderDetail>): CharSequence {
        val sum = data.sumOf { it.price * it.quantity }
        return getMoneyByYuan(sum)
    }
}
