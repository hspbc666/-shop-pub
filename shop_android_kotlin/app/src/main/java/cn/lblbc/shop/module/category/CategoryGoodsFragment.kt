package cn.lblbc.shop.module.category

import android.content.Intent
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.network.response.Goods
import cn.lblbc.shop.utils.Constants

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CategoryGoodsFragment(var categoryId: String) : BaseVmFragment<CategoryGoodsViewModel>() {
    private lateinit var goodsAdapter: CategoryGoodsAdapter
    private lateinit var goodsGridView: GridView
    override fun viewModelClass() = CategoryGoodsViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_category_goods

    override fun initView() {
        goodsAdapter = CategoryGoodsAdapter(context!!)
        goodsGridView = findViewById(R.id.goodsGridView)
        goodsGridView.adapter = goodsAdapter
    }

    override fun initListeners() {
        goodsGridView.setOnItemClickListener { _, _, position, _ ->
            onItemClick(goodsAdapter.getData(position))
        }
    }

    override fun initData() {
        mViewModel.queryGoods(categoryId)
    }

    override fun observe() {
        mViewModel.goodsList.observe(this) { goodsAdapter.setData(it) }
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }

    companion object {
        fun newInstance(categoryId: String): Fragment {
            return CategoryGoodsFragment(categoryId)
        }
    }
}