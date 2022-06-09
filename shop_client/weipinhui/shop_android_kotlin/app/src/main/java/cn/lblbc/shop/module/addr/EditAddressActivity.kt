package cn.lblbc.shop.module.addr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.launch
import cn.lblbc.shop.R
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS_INFO
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
class EditAddressActivity : AppCompatActivity() {
    private var address: Address? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)
        initListeners()
        initData()
    }

    private fun initData() {
        val addressInfoInJson = intent.getStringExtra(EXTRA_KEY_ADDRESS_INFO) ?: ""
        address = JsonUtil.fromJson(addressInfoInJson)
        address?.let {
            receiverNameEt.setText(it.name)
            receiverPhoneEt.setText(it.phone)
            detailedAddrEt.setText(it.address)
            defaultAddrSwitch.isChecked = it.defaultAddress
        }
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
        saveAddressTv.setOnClickListener {
            address?.let {
                it.name = receiverNameEt.text.toString()
                it.phone = receiverPhoneEt.text.toString()
                it.address = detailedAddrEt.text.toString()
                it.defaultAddress = defaultAddrSwitch.isChecked
                modifyAddress(it)
            }
        }
    }

    private fun modifyAddress(address: Address) {
        launch(
            action = { NetworkRepository.apiService.modifyAddress(address) },
            onSuccess = { finish() }
        )
    }
}