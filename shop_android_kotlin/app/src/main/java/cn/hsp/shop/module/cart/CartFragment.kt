package cn.hsp.shop.module.cart

import android.content.Intent
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.getMoneyByYuan

class CartFragment : BaseVmFragment<CartViewModel>() {
    private lateinit var adapter: CartAdapter
    private lateinit var goodsListRv: RecyclerView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    private lateinit var selectdCountTv: TextView
    private lateinit var sumTv: TextView
    override fun viewModelClass() = CartViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_cart

    override fun initView() {
        adapter = CartAdapter(mViewModel)
        goodsListRv = findViewById(R.id.goodsListRv)
        goodsListSrl = findViewById(R.id.goodsListSrl)
        selectdCountTv = findViewById(R.id.selectdCountTv)
        sumTv = findViewById(R.id.sumTv)

        goodsListRv.adapter = adapter
        adapter.setOnItemClick(this::onItemClick)

        goodsListSrl.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        refreshData()
    }

    private fun refreshData() {
        mViewModel.queryCart(
            onSuccess = {
                goodsListSrl.isRefreshing = true
            },
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
        mViewModel.selectionChangeCount.observe(this, Observer {
            var sum = 0L
            mViewModel.selectionItemList.forEach { sum += it.price * it.quantity }
            selectdCountTv.text =
                context?.getString(R.string.selected_count, mViewModel.selectionItemList.size)
            sumTv.text = context?.getString(R.string.price, getMoneyByYuan(sum))
        })
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}