package cn.hsp.shop.module.goods_list

import android.content.Intent
import android.view.View
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.module.search.SearchActivity
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.Constants

class GoodsListFragment : BaseVmFragment<GoodsListViewModel>() {
    private lateinit var adapter: GoodsListAdapter
    private lateinit var goodsGridView: GridView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    private lateinit var homeSearchLayout: View
    override fun viewModelClass() = GoodsListViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_goods_list

    override fun initView() {
        adapter = GoodsListAdapter(context!!)
        goodsGridView = findViewById(R.id.goodsGridView)
        goodsGridView.adapter = adapter
        goodsListSrl = findViewById(R.id.goodsListSrl)
        homeSearchLayout = findViewById(R.id.homeSearchLayout)
    }

    override fun initListeners() {
        goodsGridView.setOnItemClickListener { _, _, position, _ -> onItemClick(adapter.getData(position)) }
        goodsListSrl.setOnRefreshListener { initData() }
        homeSearchLayout.setOnClickListener { startActivity(Intent(context, SearchActivity::class.java)) }
    }

    override fun initData() {
        mViewModel.queryGoods(
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
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