package cn.lblbc.shop.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDataList = mutableListOf<T>()
    protected lateinit var mContext: Context
    lateinit var onItemClick: (data: T) -> Unit
    override fun getItemCount(): Int = mDataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataList: List<T>) {
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(layoutResId(), parent, false)
        return ViewHolder(view)
    }

    protected abstract fun layoutResId(): Int

    fun getItem(position: Int) = mDataList[position]
}