package cn.hsp.shop.module.addr.select

import android.content.Intent
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmActivity
import cn.hsp.shop.module.addr.AddAddressActivity
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_goods.toolbar

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
class SelectAddressActivity : BaseVmActivity<SelectAddressViewModel>() {
    private lateinit var adapterSelect: SelectAddressAdapter
    override fun viewModelClass() = SelectAddressViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_address_list
    override fun initView() {
        initToolbar()
        adapterSelect = SelectAddressAdapter(mViewModel)
        addrListRv.adapter = adapterSelect
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
            adapterSelect.setData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}
