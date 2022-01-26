package cn.lblbc.shop.module.order.list

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.order.detail.OrderDetailActivity
import cn.lblbc.shop.network.response.QueryOrderResp
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.JsonUtil
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderFragment(var tabId: Int) : BaseVmFragment<OrderListViewModel>() {
    private lateinit var adapter: OrderAdapter
    private lateinit var orderListRv: RecyclerView
    private lateinit var orderListSrl: SwipeRefreshLayout

    override fun viewModelClass() = OrderListViewModel::class.java
    override fun layoutResId() = R.layout.fragment_order

    override fun initView() {
        adapter = OrderAdapter()
        orderListRv = findViewById(R.id.orderListRv)
        orderListRv.adapter = adapter
        orderListSrl = findViewById(R.id.orderListSrl)
        refreshPage()
    }

    override fun initListeners() {
        adapter.setOnItemClick(this::onItemClick)
        orderListSrl.setOnRefreshListener { refreshPage() }
    }

    private fun refreshPage() {
        mViewModel.queryOrder(tabId, onComplete = onQueryComplete())
    }

    private fun onQueryComplete(): () -> Unit = { orderListSrl.isRefreshing = false }

    companion object {
        fun newInstance(tabId: Int): Fragment {
            return OrderFragment(tabId)
        }
    }

    override fun observe() {
        mViewModel.orders.observe(this, { adapter.setData(it) })
    }

    private fun onItemClick(resp: QueryOrderResp) {
        val intent = Intent(context, OrderDetailActivity::class.java)
        val orderInfoInJson = JsonUtil.toJson(resp)
        intent.putExtra(Constants.EXTRA_KEY_ORDER_INFO, orderInfoInJson)
        startActivity(intent)
    }

}