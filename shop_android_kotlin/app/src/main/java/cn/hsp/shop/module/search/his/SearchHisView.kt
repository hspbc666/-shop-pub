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
    private val SP_KEY_SEARCH_HIS = "search_his"
    private var searchHisBean = SearchHisBean()
    private var isEditing = false

    init {
        initView(context)
        initListeners()
        loadData()
        updateView()
    }

    private fun loadData() {
        val searchHisBeanStr = SpUtil.get(SP_KEY_SEARCH_HIS, null)
        searchHisBeanStr?.let {
            searchHisBean = JsonUtil.fromJson(searchHisBeanStr ?: "")
        }
    }

    private fun updateView() {
        searchHisContainer.removeAllViews()
        searchHisBean.keyWordList.forEach {
            searchHisContainer.addView(getItemView(it))
        }
        if (isEditing) {
            searchHisDelIv.visibility = GONE
            searchHisDelAllLayout.visibility = VISIBLE
        } else {
            searchHisDelIv.visibility = VISIBLE
            searchHisDelAllLayout.visibility = GONE
        }
    }

    private fun getItemView(keyword: String): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_his, null)
        view.findViewById<TextView>(R.id.keyWordTv).text = keyword
        val delHisIv = view.findViewById<View>(R.id.delHisIv)
        if (isEditing) {
            delHisIv.visibility = VISIBLE
        } else {
            delHisIv.visibility = GONE
        }
        return view
    }

    fun setCallback(callback: (quantity: Int) -> Unit) {
        mCallback = callback
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_search_his, this, true)
    }

    private fun initListeners() {
        searchHisDelIv.setOnClickListener {
            isEditing = true
            updateView()
        }
        delAllSearchHisTv.setOnClickListener {
            isEditing = false
            updateView()
        }
        searchHisDoneTv.setOnClickListener {
            isEditing = false
            updateView()
        }
    }

    fun addKeyword(keyword: String) {
        searchHisBean.keyWordList.add(0, keyword)
        SpUtil.put(SP_KEY_SEARCH_HIS, JsonUtil.toJson(searchHisBean) ?: "")
        updateView()
    }
}