package cn.hsp.shop.module.addr

import android.content.Intent
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_goods.toolbar

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class AddressActivity : BaseVmActivity<AddressViewModel>() {
    private lateinit var adapter: AddressAdapter
    override fun viewModelClass() = AddressViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_address
    override fun initView() {
        initToolbar()
        adapter = AddressAdapter(mViewModel)
        addrListRv.adapter = adapter
    }

    override fun initData() {
        mViewModel.query()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initListeners() {
        modifyAddrTv.setOnClickListener { startActivity(Intent(this, AddAddressActivity::class.java)) }
    }

    override fun observe() {
        mViewModel.userAddrList.observe(this, {
            adapter.setData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}
