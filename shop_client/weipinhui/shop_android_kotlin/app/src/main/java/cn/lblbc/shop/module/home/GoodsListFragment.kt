package cn.lblbc.shop.module.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.lblbc.lib.utils.getMoneyByYuan
import cn.lblbc.lib.utils.launch
import cn.lblbc.lib.utils.loadImage
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.network.Goods
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.EXTRA_KEY_GOODS_ID
import kotlinx.android.synthetic.main.item_goods.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class GoodsListFragment(private var tabId: String) : Fragment() {
    private lateinit var lblRecyclerView: LblRecyclerView<Goods>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_goods_list, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        lblRecyclerView = view.findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_goods }
        lblRecyclerView.setColumns(2)
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.goodsNameTv.text = data.name
            itemView.goodsPriceTv.text = getMoneyByYuan(data.price)
            loadImage(itemView.goodsIv, data.squarePic)
        }

        lblRecyclerView.setOnItemClick { onItemClick(it) }
    }

    private fun onItemClick(it: Goods) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(EXTRA_KEY_GOODS_ID, it.id)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        queryData()
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryGoodsByCategory(tabId) },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: List<Goods>) {
        lblRecyclerView.setData(data)
    }

    companion object {
        fun newInstance(tabId: String): Fragment {
            return GoodsListFragment(tabId)
        }
    }
}