package cn.lblbc.shop.module.addr

import android.content.Intent
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_goods.toolbar

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddressListActivity : BaseVmActivity<AddressViewModel>() {
    private lateinit var adapter: AddressAdapter
    override fun viewModelClass() = AddressViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_address_list
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
        mViewModel.addressList.observe(this) {
            adapter.setData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.query()
    }
}
