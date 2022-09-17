package cn.lblbc.shop.module.search

import android.content.Intent
import android.view.inputmethod.EditorInfo
import cn.lblbc.lib.utils.*
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.module.goods_detail.GoodsAdapter
import cn.lblbc.shop.network.Goods
import cn.lblbc.shop.utils.EXTRA_KEY_GOODS_ID
import kotlinx.android.synthetic.main.item_goods.view.*
import kotlinx.android.synthetic.main.part_search_top.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SearchActivity : BaseVmActivity<SearchViewModel>() {
    private lateinit var lblRecyclerView: LblRecyclerView<Goods>
    override fun viewModelClass() = SearchViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_search
    override fun initView() {
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_goods }
        lblRecyclerView.setColumns(2)
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.goodsNameTv.text = data.name
            itemView.goodsPriceTv.text = getMoneyByYuan(data.price)
            loadImage(this, itemView.goodsIv, data.squarePic)
        }
        lblRecyclerView.setOnItemClick { onItemClick(it) }
        showSoftKeyboard(this, searchEt)
    }

    override fun initListeners() {
        backIv.setOnClickListener { finish() }
        searchEt.onSearchKeyDown { search() }
    }

    private fun search() {
        val keyword = searchEt.text.toString()
        mViewModel.queryGoods(keyword)
        hideSoftKeyboard(this)
    }

    override fun observe() {
        mViewModel.goodsList.observe(this) {
            lblRecyclerView.setData(it)
        }
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(this, GoodsActivity::class.java)
        intent.putExtra(EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }
}
