package cn.lblbc.shop.module.addr

import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.network.response.UserAddr
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.activity_modify_address.*
import kotlinx.android.synthetic.main.part_addr_detail.*
import kotlinx.android.synthetic.main.part_addr_name_phone.*
import kotlinx.android.synthetic.main.part_addr_type_info.*


/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class ModifyAddressActivity : BaseVmActivity<AddressViewModel>() {
    private var addType = 0
    private var userAddr: UserAddr? = null
    override fun layoutResId(): Int = R.layout.activity_modify_address
    override fun viewModelClass() = AddressViewModel::class.java
    override fun initView() {
        initToolbar()
        val addrInfoInJson = intent.getStringExtra(Constants.EXTRA_KEY_ADDR_INFO)
        userAddr = JsonUtil.fromJson(addrInfoInJson!!)
        userAddr?.let {
            receiverNameEt.setText(it.name)
            receiverPhoneEt.setText(it.phone)
            regionEt.setText(it.region)
            detailedAddrEt.setText(it.address)
            defaultAddrSwitch.isChecked = it.defaultAddress
            selectAddrRadioButton(it.addrType)
        }
    }

    private fun selectAddrRadioButton(addrType: Int) {
        when (addrType) {
            Constants.AddrType.ADDR_TYPE_HOME -> addrTypeHomeRb.isChecked = true
            Constants.AddrType.ADDR_TYPE_COMPANY -> addrTypeCompanyRb.isChecked = true
            Constants.AddrType.ADDR_TYPE_OTHER -> addrTypeOtherRb.isChecked = true
        }
    }

    override fun initListeners() {
        addrTypeRadioGroup.setOnCheckedChangeListener { _, checkedButton ->
            when (checkedButton) {
                R.id.addrTypeHomeRb -> addType = Constants.AddrType.ADDR_TYPE_HOME
                R.id.addrTypeCompanyRb -> addType = Constants.AddrType.ADDR_TYPE_COMPANY
                R.id.addrTypeOtherRb -> addType = Constants.AddrType.ADDR_TYPE_OTHER
            }
        }
        modifyAddrTv.setOnClickListener {
            userAddr?.let {
                it.name = receiverNameEt.text.toString()
                it.phone = receiverPhoneEt.text.toString()
                it.region = regionEt.text.toString()
                it.address = detailedAddrEt.text.toString()
                it.defaultAddress = defaultAddrSwitch.isChecked
                it.addrType = addType
                mViewModel.modifyAddress(it, onSuccess = {
                    finish()
                })
            }
        }
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

}
