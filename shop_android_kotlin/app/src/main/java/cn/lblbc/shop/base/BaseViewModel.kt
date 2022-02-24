package cn.lblbc.shop.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.lblbc.shop.utils.toast
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

typealias Block<T> = suspend () -> T
typealias Complete<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     */
    protected fun launch(block: Block<Unit>, error: Error? = null, complete: Complete<Unit>? = null, cancel: Cancel? = null): Job {
        return viewModelScope.launch {
            try {
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> cancel?.invoke(e)
                    else -> error?.invoke(e)
                }
                if (e.message?.isNotEmpty() == true) {
                    toast(e.message!!)
                }
            } finally {
                complete?.invoke()
            }
        }
    }
}