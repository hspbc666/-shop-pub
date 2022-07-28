package cn.lblbc.shop.module.addr

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.ui.view.BaseAdapter
import cn.lblbc.shop.utils.EXTRA_KEY_ADDR_INFO
import cn.lblbc.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.item_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddressAdapter(var mViewModel: AddressViewModel) : BaseAdapter<Address>() {
    override fun layoutResId() = R.layout.item_addr
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
        holder.itemView.addrDelTv.setOnClickListener { showDeleteDialog(data.id) }
        holder.itemView.addrEditTv.setOnClickListener { gotoEditAddressPage(data) }
    }

    private fun showDeleteDialog(addressId: String) {
        val message = "确认删除当前收货地址么？"
        val alertDialog = AlertDialog.Builder(mContext).setMessage(message).setCancelable(false)
            .setPositiveButton(R.string.delete) { _, _ -> deleteAddress(addressId) }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
        alertDialog.show()
    }

    private fun gotoEditAddressPage(data: Address) {
        val intent = Intent(mContext, EditAddressActivity::class.java)
        intent.putExtra(EXTRA_KEY_ADDR_INFO, JsonUtil.toJson(data))
        mContext.startActivity(intent)
    }

    private fun deleteAddress(addressId: String) {
        mViewModel.deleteAddress(addressId) { mViewModel.query() }
    }
}