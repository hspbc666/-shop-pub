package cn.hsp.shop.module.goods_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cn.hsp.shop.R
import cn.hsp.shop.network.response.Goods
import cn.hsp.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide
import java.util.*

class FlowerAdapter(private val mContext: Context) : BaseAdapter() {
    private var mDataList = ArrayList<Goods>()
    private val mInflater =
        mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    fun setData(goodsList: List<Goods>) {
        mDataList.clear()
        mDataList.addAll(goodsList)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mDataList.size
    }

    override fun getItem(position: Int): Any {
        return mDataList[position]
    }

    fun getData(position: Int): Goods {
        return mDataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup): View {
        var convertView: View
        val holder: ViewHolder
        if (_convertView == null) {
            holder = ViewHolder()
            convertView = mInflater.inflate(R.layout.item_goods, null)
            holder.goodsNameTv = convertView.findViewById(R.id.goodsNameTv)
            holder.goodsPriceTv = convertView.findViewById(R.id.goodsPriceTv)
            holder.goodsIv = convertView.findViewById(R.id.goodsIv)
            convertView.tag = holder
        } else {
            convertView = _convertView
            holder = convertView.tag as ViewHolder
        }

        val data = mDataList[position]
        holder.goodsNameTv?.text = data.name
        holder.goodsPriceTv?.text = mContext.getString(R.string.price, getMoneyByYuan(data.price))
        data.longPic?.let { loadImage(holder.goodsIv!!, it) }
        return convertView
    }

    private fun loadImage(goodsIv: ImageView, url: String) {
        Glide.with(mContext)
            .load(url)
            .into(goodsIv)
    }

    private class ViewHolder {
        var goodsNameTv: TextView? = null
        var goodsPriceTv: TextView? = null
        var goodsIv: ImageView? = null
    }
}