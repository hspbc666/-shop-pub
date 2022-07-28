package cn.lblbc.shop.module.goods_detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.Goods
import cn.lblbc.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class GoodsAdapter(private val mContext: Context) : BaseAdapter() {
    private var mDataList = ArrayList<Goods>()
    private val mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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
        val convertView: View
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
        holder.goodsNameTv.text = data.name
        holder.goodsPriceTv.text = mContext.getString(R.string.price, getMoneyByYuan(data.price))
        loadImage(holder.goodsIv, data.squarePic)
        return convertView
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(mContext).load(url).into(imageView)
    }

    private class ViewHolder {
        lateinit var goodsNameTv: TextView
        lateinit var goodsPriceTv: TextView
        lateinit var goodsIv: ImageView
    }
}