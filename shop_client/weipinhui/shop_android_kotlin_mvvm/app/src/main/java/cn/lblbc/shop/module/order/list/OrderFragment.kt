package cn.lblbc.shop.module.order.list

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.OrderInfo
import cn.lblbc.shop.utils.EXTRA_KEY_ORDER_ID
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_COMMENT
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_DELIVER
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_PAY
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_RECEIVE
import cn.lblbc.shop.utils.OrderStatus.Companion.ORDER_STATUS_TO_RETURN
import cn.lblbc.shop.utils.calcSum1
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderFragment(private var tabId: String) : BaseVmFragment<OrderListViewModel>() {
    private lateinit var lblRecyclerView: LblRecyclerView<OrderInfo>

    override fun viewModelClass() = OrderListViewModel::class.java
    override fun layoutResId() = R.layout.fragment_order

    override fun initView() {
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_order }
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.orderStatusTv.text = getOrderStatus(data.status)
            itemView.goodsCountTv.text = "共" + data.list.size + "件商品"
            itemView.moneyTv.text = calcSum1(data.list)
            itemView.orderListView.setData(data.list)
            itemView.deleteOrderTv.setOnClickListener { onDeleteBtnClick(data.orderId) }
        }
        lblRecyclerView.setOnItemClick { onItemClick(it) }

        refreshPage()
    }

    private fun refreshPage() {
        mViewModel.queryOrder(tabId)
    }

    private fun getOrderStatus(orderStatus: Int): String {
        return when (orderStatus) {
            ORDER_STATUS_TO_PAY -> "待付款"
            ORDER_STATUS_TO_DELIVER -> "待发货"
            ORDER_STATUS_TO_RECEIVE -> "待收货"
            ORDER_STATUS_TO_COMMENT -> "待评价"
            ORDER_STATUS_TO_RETURN -> "退货中"
            else -> "已完成"
        }
    }

    private fun onItemClick(orderInfo: OrderInfo) {
        val intent = Intent(context, OrderDetailActivity::class.java)
        intent.putExtra(EXTRA_KEY_ORDER_ID, orderInfo.orderId)
        startActivity(intent)
    }

    private fun onDeleteBtnClick(orderId: String) {
        showDeleteDialog(orderId)
    }

    private fun showDeleteDialog(orderId: String) {
        val message = "确认删除当前订单么？"
        val alertDialog = AlertDialog.Builder(requireContext()).setMessage(message).setCancelable(false)
            .setPositiveButton(R.string.delete) { _, _ -> mViewModel.deleteOrder(orderId) { refreshPage() } }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
        alertDialog.show()
    }

    override fun observe() {
        mViewModel.orders.observe(this) { lblRecyclerView.setData(it) }
    }

    companion object {
        fun newInstance(tabId: String): Fragment {
            return OrderFragment(tabId)
        }
    }
}