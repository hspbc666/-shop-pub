package cn.hsp.shop.module.order.confirm.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.hsp.shop.R
import cn.hsp.shop.network.response.CartItem
import cn.hsp.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_order_dialog_goods.view.*


/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CartGoodsListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = mutableListOf<CartItem>()
    private lateinit var mContext: Context
    fun setData(cartItemList: List<CartItem>) {
        dataList.clear()
        dataList.addAll(cartItemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.goodsNameTv.text = data.name
        holder.itemView.orderDialogQuantity.text = mContext.getString(R.string.amount_with_prefix, data.quantity)
        holder.itemView.goodsPriceTv.text =
            mContext.getString(R.string.price, getMoneyByYuan(data.price))
        data.squarePic?.let { loadImage(holder.itemView.goodsIv, it) }
    }

    private fun loadImage(goodsIv: ImageView, url: String) {
        Glide.with(mContext).load(url).into(goodsIv)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_order_dialog_goods, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}