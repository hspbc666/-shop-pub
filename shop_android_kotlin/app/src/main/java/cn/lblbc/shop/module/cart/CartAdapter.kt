package cn.lblbc.shop.module.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cart_goods.view.*


/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CartAdapter(var mViewModel: CartViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClick: (cartItem: CartItem) -> Unit
    private var dataList = mutableListOf<CartItem>()
    private lateinit var mContext: Context
    fun setData(cartItemList: List<CartItem>) {
        dataList.clear()
        dataList.addAll(cartItemList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.cartItemCheckBox.isChecked = false
        holder.itemView.goodsNameTv.text = data.name
        holder.itemView.quantityView.setData(data.quantity)
        holder.itemView.goodsPriceTv.text =
            mContext.getString(R.string.price, getMoneyByYuan(data.price))
        data.squarePic?.let { loadImage(holder.itemView.goodsIv, it) }

        holder.itemView.quantityView.setCallback {
            data.quantity = it
            mViewModel.modifyCart(data, isChecked = holder.itemView.cartItemCheckBox.isChecked)
        }
        holder.itemView.setOnClickListener { onItemClick(data) }
        holder.itemView.cartItemCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mViewModel.selectItem(data)
            } else {
                mViewModel.disSelectItem(data.id)
            }
        }
    }

    private fun loadImage(goodsIv: ImageView, url: String) {
        Glide.with(mContext).load(url).into(goodsIv)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_cart_goods, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (CartItem: CartItem) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}