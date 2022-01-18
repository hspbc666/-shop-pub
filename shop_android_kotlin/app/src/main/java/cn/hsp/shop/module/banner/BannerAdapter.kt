package cn.hsp.shop.module.banner

import cn.hsp.shop.R
import cn.hsp.shop.network.response.Goods
import com.bumptech.glide.Glide
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import kotlinx.android.synthetic.main.item_banner.view.*

class SimpleAdapter : BaseBannerAdapter<Goods>() {

    override fun bindData(holder: BaseViewHolder<Goods>, data: Goods, position: Int, pageSize: Int) {
        val imageView = holder.itemView.bannerIv
        Glide.with(imageView).load(data.longPic).into(imageView)
    }

    override fun getLayoutId(viewType: Int) = R.layout.item_banner
}