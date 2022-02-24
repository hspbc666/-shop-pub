package cn.lblbc.shop.module.addr

import android.content.Intent
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity
import cn.lblbc.shop.network.response.Address
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.Constants.AddrType.Companion.ADDR_TYPE_COMPANY
import cn.lblbc.shop.utils.Constants.AddrType.Companion.ADDR_TYPE_HOME
import cn.lblbc.shop.utils.Constants.AddrType.Companion.ADDR_TYPE_OTHER
import cn.lblbc.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.part_addr_detail.*
import kotlinx.android.synthetic.main.part_addr_name_phone.*
import kotlinx.android.synthetic.main.part_addr_type_info.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddAddressActivity : BaseVmActivity<AddressViewModel>() {
    private var addType = 0
    override fun layoutResId(): Int = R.layout.activity_add_address
    override fun viewModelClass() = AddressViewModel::class.java
    override fun initView() {
        initToolbar()
    }

    override fun initListeners() {
        addrTypeRadioGroup.setOnCheckedChangeListener { _, checkedButton ->
            when (checkedButton) {
                R.id.addrTypeHomeRb -> addType = ADDR_TYPE_HOME
                R.id.addrTypeCompanyRb -> addType = ADDR_TYPE_COMPANY
                R.id.addrTypeOtherRb -> addType = ADDR_TYPE_OTHER
            }
        }
        addAddrTv.setOnClickListener {
            val userAddr = Address()
            userAddr.name = receiverNameEt.text.toString()
            userAddr.phone = receiverPhoneEt.text.toString()
            userAddr.region = regionEt.text.toString()
            userAddr.address = detailedAddrEt.text.toString()
            userAddr.defaultAddress = defaultAddrSwitch.isChecked
            userAddr.addrType = addType
            mViewModel.addAddress(userAddr, onSuccess = {
                sendResultForConfirmOrder(it)
                finish()
            })
        }
    }

    /**
     * 确定订单页面需要返回数据
     */
    private fun sendResultForConfirmOrder(it: Address) {
        val intent = Intent()
        intent.putExtra(Constants.EXTRA_KEY_USER_ADDR, JsonUtil.toJson(it))
        setResult(ConfirmOrderActivity.requestCodeForSelectAddr, intent)
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

}
