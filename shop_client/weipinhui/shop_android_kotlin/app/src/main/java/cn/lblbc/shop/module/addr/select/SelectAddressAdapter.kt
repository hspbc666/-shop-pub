package cn.lblbc.shop.module.addr.select

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.Address
import cn.lblbc.shop.ui.view.BaseAdapter
import kotlinx.android.synthetic.main.item_addr.view.addressTv
import kotlinx.android.synthetic.main.item_addr.view.defaultAddrTv
import kotlinx.android.synthetic.main.item_addr.view.phoneTv
import kotlinx.android.synthetic.main.item_addr.view.userNameTv
import kotlinx.android.synthetic.main.item_select_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SelectAddressAdapter(private var selectedAddressId: String?) : BaseAdapter<Address>() {
    override fun layoutResId() = R.layout.item_select_addr
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.userNameTv.text = data.name
        holder.itemView.phoneTv.text = data.phone
        holder.itemView.addressTv.text = data.address
        if (position == 0) {
            holder.itemView.defaultAddrTv.visibility = VISIBLE
        } else {
            holder.itemView.defaultAddrTv.visibility = GONE
        }

        holder.itemView.selectAddrRadioBtn.isChecked = false
        if (data.id == selectedAddressId) {
            holder.itemView.selectAddrRadioBtn.isChecked = true
        }
        holder.itemView.selectAddrRadioBtn.setOnClickListener { onItemClick(data) }
        holder.itemView.setOnClickListener { onItemClick(data) }
    }
}