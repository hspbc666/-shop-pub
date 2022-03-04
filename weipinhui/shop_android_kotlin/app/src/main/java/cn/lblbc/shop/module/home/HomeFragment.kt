package cn.lblbc.shop.module.home

import android.content.Intent
import android.view.View
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.banner.BannerView
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.module.search.SearchActivity
import cn.lblbc.shop.network.response.Goods
import cn.lblbc.shop.utils.EXTRA_KEY_GOODS_ID

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class HomeFragment : BaseVmFragment<HomeViewModel>() {
    private lateinit var bannerView: BannerView
    private lateinit var homeSearchLayout: View
    private lateinit var goodsSquareView1: GoodsSquareView
    private lateinit var goodsSquareView2: GoodsSquareView
    private lateinit var goodsSquareView3: GoodsSquareView
    private lateinit var goodsSquareView4: GoodsSquareView
    private lateinit var goodsSquareView5: GoodsLongView
    private lateinit var goodsSquareView6: GoodsLongView
    override fun viewModelClass() = HomeViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        bannerView = findViewById(R.id.bannerView)
        homeSearchLayout = findViewById(R.id.homeSearchLayout)
        goodsSquareView1 = findViewById(R.id.goodsSquareView1)
        goodsSquareView2 = findViewById(R.id.goodsSquareView2)
        goodsSquareView3 = findViewById(R.id.goodsSquareView3)
        goodsSquareView4 = findViewById(R.id.goodsSquareView4)
        goodsSquareView5 = findViewById(R.id.goodsSquareView5)
        goodsSquareView6 = findViewById(R.id.goodsSquareView6)
    }

    override fun initListeners() {
        homeSearchLayout.setOnClickListener { startActivity(Intent(context, SearchActivity::class.java)) }
        bannerView.setCallback { gotoGoodsDetail(it) }
        goodsSquareView1.setCallback { gotoGoodsDetail(it) }
        goodsSquareView2.setCallback { gotoGoodsDetail(it) }
        goodsSquareView3.setCallback { gotoGoodsDetail(it) }
        goodsSquareView4.setCallback { gotoGoodsDetail(it) }
        goodsSquareView5.setCallback { gotoGoodsDetail(it) }
        goodsSquareView6.setCallback { gotoGoodsDetail(it) }
    }

    private fun gotoGoodsDetail(goods: Goods) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(EXTRA_KEY_GOODS_ID, goods.id)
        startActivity(intent)
    }

    private fun refreshPage() {
        queryData()
    }

    private fun queryData() {
        val homeCategoryId = "0"
        mViewModel.queryGoodsByCategory(homeCategoryId)
    }

    override fun observe() {
        mViewModel.dataList.observe(this) {
            bannerView.setData(it.subList(0, 3))
            goodsSquareView1.setData(it[3])
            goodsSquareView2.setData(it[4])
            goodsSquareView3.setData(it[5])
            goodsSquareView4.setData(it[6])
            goodsSquareView5.setData(it[7])
            goodsSquareView6.setData(it[8])
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}