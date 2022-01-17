package cn.hsp.shop.module.order.list

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.order.detail.OrderDetailActivity
import cn.hsp.shop.network.response.QueryOrderResp
import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_ALL
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_COMMENT
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_DELIVER
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_PAY
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_RECEIVE
import cn.hsp.shop.utils.Constants.OrderTab.Companion.ORDER_TAB_TO_RETURN
import cn.hsp.shop.utils.JsonUtil

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
    }

    override fun initListeners() {
        adapter.setOnItemClick(this::onItemClick)
        orderListSrl.setOnRefreshListener { refreshPage() }
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }

    private fun refreshPage() {
        when (tabId) {
            ORDER_TAB_ALL -> mViewModel.queryAllOrder(onComplete = onQueryComplete())
            ORDER_TAB_TO_PAY -> mViewModel.queryToPayOrder(onComplete = onQueryComplete())
            ORDER_TAB_TO_DELIVER -> mViewModel.queryToDeliverOrder(onComplete = onQueryComplete())
            ORDER_TAB_TO_RECEIVE -> mViewModel.queryToReceiveOrder(onComplete = onQueryComplete())
            ORDER_TAB_TO_COMMENT -> mViewModel.queryToCommentOrder(onComplete = onQueryComplete())
            ORDER_TAB_TO_RETURN -> mViewModel.queryToReturnOrder(onComplete = onQueryComplete())
        }
    }

    private fun onQueryComplete(): () -> Unit = { orderListSrl.isRefreshing = false }

    companion object {
        fun newInstance(tabId: Int): Fragment {
            return OrderFragment(tabId)
        }
    }

    override fun observe() {
        when (tabId) {
            ORDER_TAB_ALL -> observeData(mViewModel.allOrders)
            ORDER_TAB_TO_PAY -> observeData(mViewModel.toPayOrders)
            ORDER_TAB_TO_DELIVER -> observeData(mViewModel.toDeliverOrders)
            ORDER_TAB_TO_RECEIVE -> observeData(mViewModel.toReceiveOrders)
            ORDER_TAB_TO_COMMENT -> observeData(mViewModel.toCommentOrders)
            ORDER_TAB_TO_RETURN -> observeData(mViewModel.toReturnOrders)
        }
    }

    private fun observeData(data: MutableLiveData<List<QueryOrderResp>>) {
        data.observe(this, { adapter.setData(it) })
    }

    private fun onItemClick(resp: QueryOrderResp) {
        val intent = Intent(context, OrderDetailActivity::class.java)
        val orderInfoInJson = JsonUtil.toJson(resp)
        intent.putExtra(Constants.EXTRA_KEY_ORDER_INFO, orderInfoInJson)
        startActivity(intent)
    }

}