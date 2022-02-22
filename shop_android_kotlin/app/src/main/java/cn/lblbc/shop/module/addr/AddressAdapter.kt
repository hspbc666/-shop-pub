package cn.lblbc.shop.module.addr

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.UserAddr
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.item_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddressAdapter(var mViewModel: AddressViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dataList = mutableListOf<UserAddr>()
    private lateinit var mContext: Context
    fun setData(UserAddrList: List<UserAddr>) {
        dataList.clear()
        dataList.addAll(UserAddrList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.userNameTv.text = data.name
        holder.itemView.phoneTv.text = data.phone
        holder.itemView.addressTv.text = data.address
        if (position == 0) {
            holder.itemView.defaultAddrTv.visibility = VISIBLE
        } else {
            holder.itemView.defaultAddrTv.visibility = GONE
        }
        holder.itemView.addrDelTv.setOnClickListener { showDeleteDialog(data.id) }
        holder.itemView.addrEditTv.setOnClickListener { gotoEditAddrPage(data) }
    }

    private fun showDeleteDialog(userAddrId: String) {
        val message = "确认删除当前收货地址么？"
        val alertDialog = AlertDialog.Builder(mContext).setMessage(message).setCancelable(false)
            .setPositiveButton(R.string.delete) { _, _ -> deleteAddress(userAddrId) }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
        alertDialog.show()
    }

    private fun gotoEditAddrPage(data: UserAddr) {
        val intent = Intent(mContext, EditAddressActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_ADDR_INFO, JsonUtil.toJson(data))
        mContext.startActivity(intent)
    }

    private fun deleteAddress(userAddrId: String) {
        mViewModel.deleteAddress(userAddrId, onSuccess = {
            mViewModel.query()
        })
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_addr, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}