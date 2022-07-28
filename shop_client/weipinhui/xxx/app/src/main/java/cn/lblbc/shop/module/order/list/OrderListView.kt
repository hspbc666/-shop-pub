package cn.lblbc.shop.module.order.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import cn.lblbc.lib.utils.getMoneyByYuan
import cn.lblbc.lib.utils.loadImage
import cn.lblbc.shop.R
import cn.lblbc.shop.network.OrderDetail

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class OrderListView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        orientation = VERTICAL
    }

    fun setData(list: List<OrderDetail>) {
        removeAllViews()
        list.forEach {
            createOrderDetailItem(it)
        }
    }

    private fun createOrderDetailItem(orderDetail: OrderDetail) {
        val view = LayoutInflater.from(context).inflate(R.layout.view_order_list, this, false)
        val imageUrl = orderDetail.squarePic
        loadImage(context, view.findViewById(R.id.orderDetailGoodsIv), imageUrl)
        view.findViewById<TextView>(R.id.orderDetailGoodsNameTv).text = orderDetail.name
        view.findViewById<TextView>(R.id.orderDetailPrice).text = getMoneyByYuan(orderDetail.price)
        view.findViewById<TextView>(R.id.orderDetailQuantity).text = "×" + orderDetail.quantity
        addView(view)
    }
}