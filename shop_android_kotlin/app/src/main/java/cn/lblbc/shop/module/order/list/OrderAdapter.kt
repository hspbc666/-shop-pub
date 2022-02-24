package cn.lblbc.shop.module.order.list

import androidx.recyclerview.widget.RecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.OrderDetail
import cn.lblbc.shop.network.response.OrderInfo
import cn.lblbc.shop.ui.view.BaseAdapter
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_COMMENT
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_DELIVER
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_PAY
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_RECEIVE
import cn.lblbc.shop.utils.Constants.OrderStatus.Companion.ORDER_STATUS_TO_RETURN
import cn.lblbc.shop.utils.getMoneyByYuan
import kotlinx.android.synthetic.main.item_order.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderAdapter : BaseAdapter<OrderInfo>() {
    override fun layoutResId() = R.layout.item_order
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        holder.itemView.orderStatusTv.text = getOrderStatus(data.status ?: 0)
        holder.itemView.goodsCountTv.text =
            mContext.getString(R.string.goods_count, data.list?.size)
        holder.itemView.sumTv.text = calcSum(data.list)
        holder.itemView.orderListView.setData(data.list)
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    private fun calcSum(data: List<OrderDetail>?): CharSequence {
        val sum = data?.sumOf { it.price * it.quantity } ?: 0L
        return mContext.getString(R.string.price, getMoneyByYuan(sum))
    }

    private fun getOrderStatus(orderStatus: Int): String {
        return when (orderStatus) {
            ORDER_STATUS_TO_PAY -> "待付款"
            ORDER_STATUS_TO_DELIVER -> "待发货"
            ORDER_STATUS_TO_RECEIVE -> "待收货"
            ORDER_STATUS_TO_COMMENT -> "待评价"
            ORDER_STATUS_TO_RETURN -> "退货中"
            else -> "已完成"
        }
    }
}