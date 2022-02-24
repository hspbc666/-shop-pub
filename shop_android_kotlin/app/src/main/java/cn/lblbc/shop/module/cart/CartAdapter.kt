package cn.lblbc.shop.module.cart

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.ui.view.BaseAdapter
import cn.lblbc.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cart_goods.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CartAdapter(var mViewModel: CartViewModel) : BaseAdapter<CartItem>() {
    override fun layoutResId() = R.layout.item_cart_goods

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
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
        holder.itemView.cartItemCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mViewModel.selectItem(data)
            } else {
                mViewModel.disSelectItem(data.id)
            }
        }
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(mContext).load(url).into(imageView)
    }
}