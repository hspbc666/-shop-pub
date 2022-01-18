package cn.hsp.shop.module.category

import android.content.Intent
import android.view.View
import android.widget.GridView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.module.search.SearchActivity
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.Constants

class CategoryFragment : BaseVmFragment<CategoryViewModel>() {
    private lateinit var adapter: CategoryAdapter
    private lateinit var goodsGridView: GridView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    override fun viewModelClass() = CategoryViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_category

    override fun initView() {
        adapter = CategoryAdapter(context!!)
        goodsGridView = findViewById(R.id.goodsGridView)
        goodsGridView.adapter = adapter
        goodsListSrl = findViewById(R.id.goodsListSrl)
    }

    override fun initListeners() {
        goodsGridView.setOnItemClickListener { _, _, position, _ -> onItemClick(adapter.getData(position)) }
        goodsListSrl.setOnRefreshListener { initData() }
    }

    override fun initData() {
        mViewModel.queryGoods(
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun observe() {
        mViewModel.dataList.observe(this, { adapter.setData(it) })
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