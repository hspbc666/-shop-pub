package cn.lblbc.shop.module.banner

import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.Goods
import com.bumptech.glide.Glide
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import kotlinx.android.synthetic.main.item_banner.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SimpleAdapter : BaseBannerAdapter<Goods>() {

    override fun bindData(holder: BaseViewHolder<Goods>, data: Goods, position: Int, pageSize: Int) {
        val imageView = holder.itemView.bannerIv
        Glide.with(imageView).load(data.longPic).into(imageView)
    }

    override fun getLayoutId(viewType: Int) = R.layout.item_banner
}