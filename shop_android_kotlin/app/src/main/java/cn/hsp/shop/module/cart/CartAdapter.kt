package cn.hsp.shop.module.goods_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.hsp.shop.R
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.getMoneyByYuan
import kotlinx.android.synthetic.main.item_goods.view.*
import com.bumptech.glide.Glide


/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class CartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClick: (Goods: Goods) -> Unit
    private var dataList = mutableListOf<Goods>()
    private lateinit var mContext: Context
    fun setData(goodsList: List<Goods>) {
        dataList.clear()
        dataList.addAll(goodsList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.goodsNameTv.text = data.name
        holder.itemView.goodsPriceTv.text = mContext.getString(R.string.price, getMoneyByYuan(data.price))
        data.squarePic?.let { loadImage(holder.itemView.goodsIv, it) }

        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    private fun loadImage(goodsIv: ImageView, url: String) {
        Glide.with(mContext)
            .load(url)
            .into(goodsIv)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_goods, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (Goods: Goods) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}