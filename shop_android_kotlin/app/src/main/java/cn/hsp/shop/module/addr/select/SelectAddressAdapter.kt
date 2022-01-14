package cn.hsp.shop.module.addr.select

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import cn.hsp.shop.R
import cn.hsp.shop.module.addr.ModifyAddressActivity
import cn.hsp.shop.network.response.UserAddr
import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.item_addr.view.*

class SelectAddressAdapter(var mViewModelSelect: SelectAddressViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
        holder.itemView.addrEditTv.setOnClickListener { gotoEditAddrPage(data) }
    }

    private fun gotoEditAddrPage(data: UserAddr) {
        val intent = Intent(mContext, ModifyAddressActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_ADDR_INFO, JsonUtil.toJson(data))
        mContext.startActivity(intent)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_select_addr, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}