package cn.hsp.demo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.hsp.demo.base.BaseViewModel
import cn.hsp.demo.network.NoteRepo
import cn.hsp.demo.network.response.Note
import cn.hsp.demo.utils.Constants.SP_KEY_USER_ID
import cn.hsp.demo.utils.SpUtil
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class NoteListViewModel : BaseViewModel() {
    private val repo by lazy { NoteRepo() }
    val dataList: MutableLiveData<List<Note>> = MutableLiveData()

    fun queryDataList(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((msg: String) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        launch(
            {
                val userId = SpUtil.get(SP_KEY_USER_ID, 0L)
                Log.v("ddfd","query")
                dataList.value = repo.queryDataList(userId)?.data
                onSuccess?.invoke()
            },
            { onFailure?.invoke(it.message ?: "") },
            { onComplete?.invoke() })
    }
}