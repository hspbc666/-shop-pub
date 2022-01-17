package cn.hsp.shop.module.order.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.hsp.shop.R
import cn.hsp.shop.network.response.FullOrderInfo
import cn.hsp.shop.network.response.QueryOrderResp
import cn.hsp.shop.utils.Constants.OrderStatus.Companion.TO_DELIVER
import cn.hsp.shop.utils.Constants.OrderStatus.Companion.TO_PAY
import cn.hsp.shop.utils.Constants.OrderStatus.Companion.TO_RECEIVE
import cn.hsp.shop.utils.Constants.OrderStatus.Companion.TO_RETURN
import cn.hsp.shop.utils.getMoneyByYuan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_order.view.*


/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class OrderAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var onItemClick: (queryOrderResp: QueryOrderResp) -> Unit
    private var dataList = mutableListOf<QueryOrderResp>()
    private lateinit var mContext: Context
    fun setData(list: List<QueryOrderResp>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.itemView.orderStatusTv.text = getOrderStatus(data.status ?: 0)
        holder.itemView.goodsCountTv.text =
            mContext.getString(R.string.goods_count, data.list?.size)
        holder.itemView.sumTv.text = calcSum(data.list)
        createOrderDetailItems(data.list, holder.itemView.ordersDetailContainer)
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    private fun calcSum(data: List<FullOrderInfo>?): CharSequence {
        val sum = data?.sumOf { it.price * it.quantity } ?: 0L
        return mContext.getString(R.string.price, getMoneyByYuan(sum))
    }

    private fun createOrderDetailItems(list: List<FullOrderInfo>?, ordersContainer: LinearLayout) {
        ordersContainer.removeAllViews()
        list?.forEach {
            createOrderDetailItem(mContext, it, ordersContainer)
        }
    }

    private fun getOrderStatus(orderStatus: Int): String {
        return when (orderStatus) {
            TO_PAY -> "待付款"
            TO_DELIVER -> "待发货"
            TO_RECEIVE -> "待收货"
            TO_RETURN -> "退货中"
            else -> "已完成"
        }
    }

    private fun createOrderDetailItem(context: Context, orderInfo: FullOrderInfo, ordersContainer: LinearLayout) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_detail, ordersContainer, false)
        val imageUrl = orderInfo.squarePic
        Glide.with(context).load(imageUrl).into(view.findViewById(R.id.orderDetailGoodsIv))
        view.findViewById<TextView>(R.id.orderDetailGoodsNameTv).text = orderInfo.name
        view.findViewById<TextView>(R.id.orderDetailPrice).text =
            context.getString(R.string.price, getMoneyByYuan(orderInfo.price))
        view.findViewById<TextView>(R.id.orderDetailQuantity).text = context.getString(R.string.amount_with_prefix, orderInfo.quantity)
        ordersContainer.addView(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (QueryOrderResp: QueryOrderResp) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}