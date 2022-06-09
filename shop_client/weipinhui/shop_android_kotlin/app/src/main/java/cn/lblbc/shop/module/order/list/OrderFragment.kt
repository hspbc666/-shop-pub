package cn.lblbc.shop.module.order.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.lblbc.lib.utils.launch
import cn.lblbc.lib.utils.showDeleteDialog
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.network.OrderInfo
import cn.lblbc.shop.utils.*
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderFragment(private var tabId: String) : Fragment() {
    private lateinit var lblRecyclerView: LblRecyclerView<OrderInfo>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        lblRecyclerView = view.findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_order }
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.orderStatusTv.text = getOrderStatus(data.status)
            itemView.goodsCountTv.text = "共" + data.list.size + "件商品"
            itemView.moneyTv.text = calcSum1(data.list)
            itemView.orderListView.setData(data.list)
            itemView.deleteOrderTv.setOnClickListener { onDeleteBtnClick(data.orderId) }
        }
        lblRecyclerView.setOnItemClick { onItemClick(it) }
    }

    private fun onItemClick(it: OrderInfo) {
        val intent = Intent(context, OrderDetailActivity::class.java)
        intent.putExtra(EXTRA_KEY_ORDER_ID, it.orderId)
        startActivity(intent)
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

    private fun onDeleteBtnClick(orderId: String) {
        val content = "确认删除当前订单么？"
        showDeleteDialog(content) { deleteOrder(orderId) }
    }

    private fun deleteOrder(orderId: String) {
        launch(
            action = { NetworkRepository.apiService.deleteOrder(orderId) },
            onSuccess = { queryData() }
        )
    }

    override fun onResume() {
        super.onResume()
        queryData()
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryOrderByStatus(tabId) },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: List<OrderInfo>) {
        lblRecyclerView.setData(data)
    }

    companion object {
        fun newInstance(tabId: String): Fragment {
            return OrderFragment(tabId)
        }
    }
}