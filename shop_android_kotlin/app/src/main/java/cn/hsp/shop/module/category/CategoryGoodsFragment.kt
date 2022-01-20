package cn.hsp.shop.module.category

import android.content.Intent
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.Constants

class CategoryGoodsFragment(var categoryId: String) : BaseVmFragment<CategoryGoodsViewModel>() {
    private lateinit var goodsAdapter: CategoryGoodsAdapter
    private lateinit var goodsGridView: GridView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    override fun viewModelClass() = CategoryGoodsViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_category_goods

    override fun initView() {
        goodsAdapter = CategoryGoodsAdapter(context!!)
        goodsGridView = findViewById(R.id.goodsGridView)
        goodsGridView.adapter = goodsAdapter
        goodsListSrl = findViewById(R.id.goodsListSrl)
    }

    override fun initListeners() {
        goodsGridView.setOnItemClickListener { _, _, position, _ ->
            onItemClick(
                goodsAdapter.getData(
                    position
                )
            )
        }
        goodsListSrl.setOnRefreshListener { initData() }
    }

    override fun initData() {
        mViewModel.queryGoods(categoryId,
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun observe() {
        mViewModel.goodsList.observe(this, { goodsAdapter.setData(it) })
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

    companion object {
        fun newInstance(categoryId: String): Fragment {
            return CategoryGoodsFragment(categoryId)
        }
    }
}