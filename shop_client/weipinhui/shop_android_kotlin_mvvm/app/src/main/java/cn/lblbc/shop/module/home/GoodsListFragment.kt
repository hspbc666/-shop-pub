package cn.lblbc.shop.module.home

import android.content.Intent
import android.widget.GridView
import androidx.fragment.app.Fragment
import cn.lblbc.lib.utils.getMoneyByYuan
import cn.lblbc.lib.utils.loadImage
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.module.goods_detail.GoodsAdapter
import cn.lblbc.shop.network.Goods
import cn.lblbc.shop.utils.EXTRA_KEY_GOODS_ID
import kotlinx.android.synthetic.main.item_goods.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class GoodsListFragment(private var categoryId: String) : BaseVmFragment<HomeGoodsViewModel>() {
    private lateinit var lblRecyclerView: LblRecyclerView<Goods>
    override fun viewModelClass() = HomeGoodsViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_goods_list

    override fun initView() {
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_goods }
        lblRecyclerView.setColumns(2)
        lblRecyclerView.setOnBind { itemView, data ->
            onItemClick(data)
        }
        lblRecyclerView.setOnItemClick { onItemClick(it) }
    }

    override fun initData() {
        mViewModel.queryGoods(categoryId)
    }

    override fun observe() {
        mViewModel.goodsList.observe(this) { lblRecyclerView.setData(it) }
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }

    companion object {
        fun newInstance(categoryId: String): Fragment {
            return GoodsListFragment(categoryId)
        }
    }
}