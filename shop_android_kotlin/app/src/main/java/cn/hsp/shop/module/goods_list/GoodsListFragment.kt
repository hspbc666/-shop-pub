package cn.hsp.shop.module.goods_list

import android.content.Intent
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.Constants

class GoodsListFragment : BaseVmFragment<GoodsListViewModel>() {
    private lateinit var adapter: FlowerAdapter
    private lateinit var goodsGridView: GridView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    override fun viewModelClass() = GoodsListViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_goods_list

    override fun initView() {
        adapter = FlowerAdapter(context!!)
        goodsGridView = findViewById(R.id.goodsGridView)
        goodsGridView.adapter = adapter
        goodsGridView.setOnItemClickListener { _, _, position, _ ->
            onItemClick(adapter.getData(position))
        }

//        goodsListRv = findViewById(R.id.goodsListRv)
        goodsListSrl = findViewById(R.id.goodsListSrl)

//        goodsListRv.adapter = adapter
//        adapter.setOnItemClick(this::onItemClick)

        goodsListSrl.setOnRefreshListener {
            initData()
        }
    }

    override fun initData() {
        mViewModel.queryGoods(
            onSuccess = {
                goodsListSrl.isRefreshing = true
            },
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun initListeners() {

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