package cn.lblbc.shop.module.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.*
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.network.Goods
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.EXTRA_KEY_GOODS_ID
import kotlinx.android.synthetic.main.item_goods.view.*
import kotlinx.android.synthetic.main.part_search_top.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SearchActivity : AppCompatActivity() {
    private lateinit var lblRecyclerView: LblRecyclerView<Goods>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initViews()
        initListeners()
    }

    private fun initViews() {
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_goods }
        lblRecyclerView.setColumns(2)
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.goodsNameTv.text = data.name
            itemView.goodsPriceTv.text = getMoneyByYuan(data.price)
            loadImage(this, itemView.goodsIv, data.squarePic)
        }
        lblRecyclerView.setOnItemClick { onItemClick(it) }
    }

    private fun initListeners() {
        backIv.setOnClickListener { finish() }
        searchEt.onSearchKeyDown { search() }
        searchTv.setOnClickListener { search() }
    }

    private fun search() {
        hideSoftKeyboard(this)
        val keyword = searchEt.text.toString()

        launch(
            action = { NetworkRepository.apiService.searchGoods(keyword) },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: List<Goods>) {
        lblRecyclerView.setData(data)
    }

    private fun onItemClick(goods: Goods) {
        val intent = Intent(this, GoodsActivity::class.java)
        intent.putExtra(EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }
}
