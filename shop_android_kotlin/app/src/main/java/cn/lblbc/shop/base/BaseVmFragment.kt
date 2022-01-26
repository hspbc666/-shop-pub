package cn.lblbc.shop.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {

    protected open lateinit var mViewModel: VM
    private lateinit var mRootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layoutResId(), container, false)
        mRootView = view
        initViewModel()
        initView()
        observe()
        // 因为Activity恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        if (savedInstanceState == null) {
            initData()
        }
        initListeners()
        return view
    }

    protected fun <T : View?> findViewById(resId: Int): T {
        return mRootView.findViewById<T>(resId)
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    protected abstract fun viewModelClass(): Class<VM>
    protected abstract fun layoutResId(): Int

    open fun initView() {
        // Override if need
    }

    open fun observe() {
        // Override if need
    }

    open fun initData() {
        // Override if need
    }

    open fun initListeners() {
        // Override if need
    }
}
