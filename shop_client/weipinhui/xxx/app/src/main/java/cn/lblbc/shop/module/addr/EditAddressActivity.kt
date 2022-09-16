package cn.lblbc.shop.module.addr

import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.utils.EXTRA_KEY_ADDR_INFO
import kotlinx.android.synthetic.main.activity_edit_address.*
import kotlinx.android.synthetic.main.part_addr_default.*
import kotlinx.android.synthetic.main.part_addr_detail.*
import kotlinx.android.synthetic.main.part_addr_receiver_name.*
import kotlinx.android.synthetic.main.part_addr_receiver_phone.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class EditAddressActivity : BaseVmActivity<AddressViewModel>() {
    private var address: Address? = null
    override fun layoutResId(): Int = R.layout.activity_edit_address
    override fun viewModelClass() = AddressViewModel::class.java
    override fun initView() {
        initToolbar()
        val addrInfoInJson = intent.getStringExtra(EXTRA_KEY_ADDR_INFO) ?: ""
        address = JsonUtil.fromJson(addrInfoInJson)
        address?.let {
            receiverNameEt.setText(it.name)
            receiverPhoneEt.setText(it.phone)
            detailedAddrEt.setText(it.address)
            defaultAddrSwitch.isChecked = it.defaultAddress
        }
    }

    override fun initListeners() {
        saveAddressTv.setOnClickListener {
            address?.let {
                it.name = receiverNameEt.text.toString()
                it.phone = receiverPhoneEt.text.toString()
                it.address = detailedAddrEt.text.toString()
                it.defaultAddress = defaultAddrSwitch.isChecked
                mViewModel.modifyAddress(it) { finish() }
            }
        }
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

}