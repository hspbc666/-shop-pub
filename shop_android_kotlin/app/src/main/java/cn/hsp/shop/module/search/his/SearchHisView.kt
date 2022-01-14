package cn.hsp.shop.module.search.his

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import cn.hsp.shop.R
import cn.hsp.shop.utils.JsonUtil
import cn.hsp.shop.utils.SpUtil
import kotlinx.android.synthetic.main.view_search_his.view.*

class SearchHisView(
    mContext: Context,
    attrs: AttributeSet?
) : LinearLayout(mContext, attrs) {
    private var mCallback: ((keyword: String) -> Unit)? = null
    private val SP_KEY_SEARCH_HIS = "search_his"
    private var searchHisBean = SearchHisBean()
    private var isEditing = false

    init {
        initView(mContext)
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
        if (searchHisBean.keyWordList.isEmpty()) {
            homeSearchLayout.visibility = GONE
        } else {
            homeSearchLayout.visibility = VISIBLE
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
        view.setOnClickListener { onClick(keyword) }
        return view
    }

    private fun onClick(keyword: String) {
        if (isEditing) {
            deleteKeyword(keyword)
        } else {
            mCallback?.invoke(keyword)
        }
    }

    fun setCallback(callback: (keyword: String) -> Unit) {
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
            showClearHisDialog()
        }
        searchHisDoneTv.setOnClickListener {
            isEditing = false
            updateView()
        }
    }

    fun addKeyword(keyword: String) {
        if (keyword.isBlank() || searchHisBean.keyWordList.contains(keyword)) {
            return
        }
        searchHisBean.keyWordList.add(0, keyword)
        saveToSP()
        updateView()
    }

    private fun deleteKeyword(keyword: String) {
        searchHisBean.keyWordList.remove(keyword)
        saveToSP()
        updateView()
    }

    private fun clearKeyword() {
        searchHisBean.keyWordList.clear()
        saveToSP()
        updateView()
    }

    private fun showClearHisDialog() {
        val message = "确认要删除全部搜索历史么？"
        val alertDialog = AlertDialog.Builder(context).setMessage(message).setCancelable(false)
            .setPositiveButton(R.string.delete) { _, _ ->
                isEditing = false
                clearKeyword()
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
        alertDialog.show()
    }

    private fun saveToSP() {
        SpUtil.put(SP_KEY_SEARCH_HIS, JsonUtil.toJson(searchHisBean) ?: "")
    }
}