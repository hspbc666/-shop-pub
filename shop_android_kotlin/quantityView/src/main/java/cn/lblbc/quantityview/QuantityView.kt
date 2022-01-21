package cn.lblbc.quantityview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.qv_view_quantity.view.*

class QuantityView(
    context: Context,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {
    private var mQuantity = 1
    private var mCallback: ((quantity: Int) -> Unit)? = null

    init {
        initView(context)
        initListeners()
    }

    private fun initListeners() {
        decreaseQuantityIv.setOnClickListener { changeQuantity(-1) }
        increaseQuantityIv.setOnClickListener { changeQuantity(1) }
    }

    fun setCallback(callback: (quantity: Int) -> Unit) {
        mCallback = callback
    }

    private fun changeQuantity(num: Int) {
        mQuantity += num
        quantityEt.text = mQuantity.toString()
        mCallback?.invoke(mQuantity)
    }


    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.qv_view_quantity, this, true)
    }

    fun setData(quantity: Int) {
        mQuantity = quantity
        quantityEt.setText(mQuantity.toString())
    }
}