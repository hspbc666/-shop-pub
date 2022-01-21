package cn.lblbc.shop.module.home

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.Goods
import cn.lblbc.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide

class GoodsLongView(mContext: Context, attrs: AttributeSet?) : LinearLayout(mContext, attrs) {
    private var mCallback: ((goods: Goods) -> Unit)? = null
    private lateinit var mGoods: Goods
    private var goodsIv: ImageView
    private var goodsNameTv: TextView
    private var goodsPriceTv: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_goods_long, this, true)
        goodsIv = view.findViewById(R.id.goodsIv)
        goodsNameTv = view.findViewById(R.id.goodsNameTv)
        goodsPriceTv = view.findViewById(R.id.goodsPriceTv)
        view.setOnClickListener { mCallback?.invoke(mGoods) }
    }

    fun setCallback(callback: ((goods: Goods) -> Unit)) {
        mCallback = callback
    }

    fun setData(goods: Goods) {
        mGoods = goods
        Glide.with(goodsIv).load(mGoods.longPic).into(goodsIv)
        goodsNameTv.text = mGoods.name
        goodsPriceTv.text = context.getString(R.string.price, getMoneyByYuan(mGoods.price))
    }
}