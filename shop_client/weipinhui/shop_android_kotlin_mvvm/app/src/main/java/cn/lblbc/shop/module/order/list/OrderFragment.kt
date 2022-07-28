package cn.lblbc.shop.module.order.list

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.response.OrderInfo
import cn.lblbc.shop.utils.EXTRA_KEY_ORDER_ID

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderFragment(private var tabId: Int) : BaseVmFragment<OrderListViewModel>() {
    private lateinit var adapter: OrderAdapter
    private lateinit var orderListRv: RecyclerView

    override fun viewModelClass() = OrderListViewModel::class.java
    override fun layoutResId() = R.layout.fragment_order

    override fun initView() {
        adapter = OrderAdapter()
        orderListRv = findViewById(R.id.orderListRv)
        orderListRv.adapter = adapter
        refreshPage()
    }

    private fun refreshPage() {
        mViewModel.queryOrder(tabId)
    }

    override fun initListeners() {
        adapter.onItemClick = this::onItemClick
        adapter.onDeleteBtnClick = this::onDeleteBtnClick
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
        mViewModel.orders.observe(this) { adapter.setData(it) }
    }

    companion object {
        fun newInstance(tabId: Int): Fragment {
            return OrderFragment(tabId)
        }
    }
}