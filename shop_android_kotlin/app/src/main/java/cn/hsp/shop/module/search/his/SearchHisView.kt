package cn.hsp.shop.module.search.his

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.hsp.shop.R
import cn.hsp.shop.utils.JsonUtil
import cn.hsp.shop.utils.SpUtil
import kotlinx.android.synthetic.main.view_search_his.view.*

class SearchHisView(
    context: Context,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {
    private var mQuantity = 1
    private var mCallback: ((quantity: Int) -> Unit)? = null
    private val SP_KEY = "search_his"
    private var searchHisBean: SearchHisBean? = null

    init {
        initView(context)
        initListeners()
        loadData()
    }

    private fun loadData() {
        searchHisBean = SearchHisBean()
//        searchHisBean?.keyWordList = listOf("fdafafa", "dafafafa", "22222", "333")
        val searchHisBeanStr = SpUtil.get(SP_KEY, null)
        searchHisBeanStr?.let {
            searchHisBean = JsonUtil.fromJson(searchHisBeanStr ?: "")
        }
//        searchHisContainer.addView(getItemView("dddddddd"))
//        searchHisContainer.addView(getItemView("55555"))
//        searchHisContainer.addView(getItemView("dddddddd"))
//        searchHisContainer.addView(getItemView("99999"))
        searchHisBean?.keyWordList?.forEach {
            searchHisContainer.addView(getItemView(it))
//            SpUtil.put(SP_KEY, JsonUtil.toJson(it) ?: "")
        }

//        val searchHisBeanStr = SpUtil.get(SP_KEY, "")

    }

    private fun getItemView(keyword: String): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_his, null)
        view.findViewById<TextView>(R.id.keyWordTv).text = keyword
        return view
    }

    private fun initListeners() {
//        decreaseQuantityIv.setOnClickListener { changeQuantity(-1) }
//        increaseQuantityIv.setOnClickListener { changeQuantity(1) }
    }

    fun setCallback(callback: (quantity: Int) -> Unit) {
        mCallback = callback
    }

    private fun changeQuantity(num: Int) {
//        mQuantity += num
//        quantityEt.text = mQuantity.toString()
//        mCallback?.invoke(mQuantity)
    }


    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_search_his, this, true)
    }

    fun setData(quantity: Int) {
        mQuantity = quantity
//        quantityEt.setText(mQuantity.toString())
    }
}