package cn.hsp.shop.module.banner

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import cn.hsp.shop.R
import cn.hsp.shop.network.response.Goods
import com.zhpan.bannerview.BannerViewPager

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
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    Log.v("MainActivity", "当前页面:$position")
                }
            })
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