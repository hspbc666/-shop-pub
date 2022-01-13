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
    private var searchHisBean = SearchHisBean()

    init {
        initView(context)
        initListeners()
        loadData()
    }

    private fun loadData() {
        val searchHisBeanStr = SpUtil.get(SP_KEY, null)
        searchHisBeanStr?.let {
            searchHisBean = JsonUtil.fromJson(searchHisBeanStr ?: "")
        }
    }

    private fun updateView() {
        searchHisContainer.removeAllViews()
        searchHisBean.keyWordList.forEach {
            searchHisContainer.addView(getItemView(it))
        }
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

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_search_his, this, true)
    }

    fun addKeyword(keyword: String) {
        searchHisBean.keyWordList.add(0, keyword)
        SpUtil.put(SP_KEY, JsonUtil.toJson(searchHisBean) ?: "")
        updateView()
    }
}