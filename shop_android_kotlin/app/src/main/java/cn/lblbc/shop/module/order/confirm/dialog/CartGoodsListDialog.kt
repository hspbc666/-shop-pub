package cn.lblbc.shop.module.order.confirm.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import cn.lblbc.shop.R
import cn.lblbc.shop.network.response.CartItem
import kotlinx.android.synthetic.main.dialog_goods_list.*

class CartGoodsListDialog(context: Context, cartItemList: List<CartItem>) : Dialog(context, R.style.common_dialog) {

    init {
        setContentView(R.layout.dialog_goods_list)
        changeDialogStyle()
        val adapter = CartGoodsListAdapter()
        adapter.setData(cartItemList)
        orderGoodsListRv.adapter = adapter
        goodsAmountTv.text = context.getString(R.string.package_count,cartItemList.size)
        closeDialogIv.setOnClickListener { dismiss() }
        showWithMoveAnim()
    }

    /**
     * 动画方式显示
     */
    private fun showWithMoveAnim() {
        window?.setWindowAnimations(R.style.dialogAnimBottomUp)
    }

    /**
     * 设置dialog居下占满屏幕
     */
    private fun changeDialogStyle() {
        window?.let {
            val params = it.attributes
            if (params != null) {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                params.gravity = Gravity.BOTTOM
                it.attributes = params
            }
        }
    }
}
