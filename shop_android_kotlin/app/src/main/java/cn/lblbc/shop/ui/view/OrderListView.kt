package cn.lblbc.shop.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.request.SimpleOrderInfo
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.network.response.OrderDetail
import com.bumptech.glide.Glide

class OrderListView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        orientation = VERTICAL
    }

    fun setData(list: List<OrderDetail>?) {
        list?.forEach {
            createOrderDetailItem(it)
        }
    }

    fun setDataByCartItems(list: List<CartItem>?) {
        list?.map { cartItem ->
            OrderDetail().apply {
                goodsId =  cartItem.id
                squarePic =  cartItem.squarePic
                name =  cartItem.name
                price =  cartItem.price
                quantity =  cartItem.quantity
        } }?.forEach {
            createOrderDetailItem(it)
        }
    }

    fun setDataBySimpleOrderInfo(list: List<SimpleOrderInfo>?) {
        list?.map { orderInfo ->
            OrderDetail().apply {
                goodsId =  orderInfo.goodsId
                squarePic =  orderInfo.squarePic
                name =  orderInfo.goodsName
                price =  orderInfo.price
                quantity =  orderInfo.quantity
            } }?.forEach {
            createOrderDetailItem(it)
        }
    }

    private fun createOrderDetailItem(orderDetail: OrderDetail) {
        val view = LayoutInflater.from(context).inflate(R.layout.item_order_detail, this, false)
        val imageUrl = orderDetail.squarePic
        Glide.with(context).load(imageUrl).into(view.findViewById(R.id.orderDetailGoodsIv))
        view.findViewById<TextView>(R.id.orderDetailGoodsNameTv).text = orderDetail.name
        view.findViewById<TextView>(R.id.orderDetailPrice).text =
            context.getString(R.string.price, cn.lblbc.shop.utils.getMoneyByYuan(orderDetail.price))
        view.findViewById<TextView>(R.id.orderDetailQuantity).text = context.getString(R.string.amount_with_prefix, orderDetail.quantity)
        addView(view)
    }
}