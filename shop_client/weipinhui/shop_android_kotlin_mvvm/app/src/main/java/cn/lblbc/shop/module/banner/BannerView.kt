package cn.lblbc.shop.module.banner

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.Goods
import com.zhpan.bannerview.BannerViewPager

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class BannerView(mContext: Context, attrs: AttributeSet?) : LinearLayout(mContext, attrs) {
    private var mCallback: ((goods: Goods) -> Unit)? = null
    private lateinit var mBannerBeanList: List<Goods>
    private var mBanner: BannerViewPager<Goods>

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_banner, this, true)
        mBanner = view.findViewById(R.id.banner)
    }

    fun setCallback(callback: ((goods: Goods) -> Unit)) {
        mCallback = callback
    }

    fun setData(bannerBeanList: List<Goods>) {
        mBannerBeanList = bannerBeanList
        setupViewPager()
    }

    private fun setupViewPager() {
        mBanner.apply {
            setOnPageClickListener { _, position ->
                mCallback?.invoke(mBannerBeanList[position])
            }
            adapter = SimpleAdapter()
            setIndicatorSliderRadius(dp2px(3f), dp2px(4.5f))
            setIndicatorSliderColor(Color.parseColor("#FFFFFFFF"), Color.parseColor("#BFFFFFFF"))
        }
            .create(mBannerBeanList)
    }

    fun dp2px(dpValue: Float): Int {
        return (0.5f + dpValue * Resources.getSystem().displayMetrics.density).toInt()
    }
}