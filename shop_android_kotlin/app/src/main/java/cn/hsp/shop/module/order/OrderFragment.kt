package cn.hsp.shop.module.order

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.cart.CartAdapter
import cn.hsp.shop.network.response.QueryOrderResp
import cn.hsp.shop.utils.Constants.ORDER_TAB_ALL
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_COMMENT
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_DELIVER
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_PAY
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_RECEIVE
import cn.hsp.shop.utils.Constants.ORDER_TAB_TO_RETURN

class OrderFragment(var tabId: Int) : BaseVmFragment<OrderListModel>() {
    private lateinit var adapter: OrderAdapter
    private lateinit var orderListRv: RecyclerView
    private lateinit var orderListSrl: SwipeRefreshLayout

    override fun viewModelClass() = OrderListModel::class.java
    override fun layoutResId() = R.layout.fragment_order

    override fun initView() {
        adapter = OrderAdapter(mViewModel)
        orderListRv = findViewById(R.id.orderListRv)
        orderListRv.adapter = adapter

        orderListSrl = findViewById(R.id.orderListSrl)
    }

    override fun initListeners() {
        adapter.setOnItemClick(this::onItemClick)
    }

    override fun initData() {
        when (tabId) {
            ORDER_TAB_ALL -> mViewModel.queryAllOrder { }
            ORDER_TAB_TO_PAY -> mViewModel.queryToPayOrder { }
            ORDER_TAB_TO_DELIVER -> mViewModel.queryToDeliverOrder { }
            ORDER_TAB_TO_RECEIVE -> mViewModel.queryToReceiveOrder { }
            ORDER_TAB_TO_COMMENT -> mViewModel.queryToCommentOrder { }
            ORDER_TAB_TO_RETURN -> mViewModel.queryToReturnOrder { }
        }
    }

    companion object {
        fun newInstance(tabId: Int): Fragment {
            return OrderFragment(tabId)
        }
    }

    override fun observe() {
        when (tabId) {
            ORDER_TAB_ALL -> mViewModel.allOrders.observe(this, Observer { adapter.setData(it) })
            ORDER_TAB_TO_PAY -> mViewModel.toPayOrders.observe(
                this,
                Observer { adapter.setData(it) })
            ORDER_TAB_TO_DELIVER -> mViewModel.toDeliverOrders.observe(
                this,
                Observer { adapter.setData(it) })
            ORDER_TAB_TO_RECEIVE -> mViewModel.toReceiveOrders.observe(
                this,
                Observer { adapter.setData(it) })
            ORDER_TAB_TO_COMMENT -> mViewModel.toCommentOrders.observe(
                this,
                Observer { adapter.setData(it) })
            ORDER_TAB_TO_RETURN -> mViewModel.toReturnOrders.observe(
                this,
                Observer { adapter.setData(it) })
        }
    }

    private fun onItemClick(resp: QueryOrderResp) {
//        val intent = Intent(context, GoodsActivity::class.java)
//        intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, cartItem.id)
//        startActivity(intent)
    }

}