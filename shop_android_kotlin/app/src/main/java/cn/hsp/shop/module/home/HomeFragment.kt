package cn.hsp.shop.module.home

import android.content.Intent
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.banner.BannerView
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.module.search.SearchActivity
import cn.hsp.shop.utils.Constants

class HomeFragment : BaseVmFragment<HomeViewModel>() {
    private lateinit var bannerView: BannerView
    private lateinit var homeSearchLayout: View
    override fun viewModelClass() = HomeViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        bannerView = findViewById(R.id.bannerView)
        homeSearchLayout = findViewById(R.id.homeSearchLayout)
    }

    override fun initListeners() {
        homeSearchLayout.setOnClickListener { startActivity(Intent(context, SearchActivity::class.java)) }
        bannerView.setCallback {
            val intent = Intent(context, GoodsActivity::class.java)
            intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, it.id)
            startActivity(intent)
        }
    }

    private fun refreshPage() {
        queryData()
    }

    private fun queryData() {
        val categoryId = "1"
        mViewModel.queryGoodsByCategory(categoryId)
    }

    override fun observe() {
        mViewModel.dataList.observe(this, { bannerView.setData(it.subList(0,3)) })
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}