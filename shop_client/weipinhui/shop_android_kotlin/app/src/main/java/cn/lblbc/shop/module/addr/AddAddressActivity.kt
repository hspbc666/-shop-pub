package cn.lblbc.shop.module.addr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.launch
import cn.lblbc.shop.R
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS
import kotlinx.android.synthetic.main.activity_add_address.*
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
class AddAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)
        initListeners()
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
        saveAddressTv.setOnClickListener { addAddress() }
    }

    private fun addAddress() {
        val address = Address()
        address.name = receiverNameEt.text.toString()
        address.phone = receiverPhoneEt.text.toString()
        address.address = detailedAddrEt.text.toString()
        address.defaultAddress = defaultAddrSwitch.isChecked

        launch(
            action = { NetworkRepository.apiService.addAddress(address) },
            onSuccess = { it?.data?.let { data -> processResponse(data, address) } }
        )
    }

    private fun processResponse(data: String, address: Address) {
        address.id = data
        sendResultForConfirmOrder(address)
        finish()
    }

    private fun sendResultForConfirmOrder(address: Address) {
        val intent = Intent()
        intent.putExtra(EXTRA_KEY_ADDRESS, JsonUtil.toJson(address))
        setResult(Activity.RESULT_OK, intent)
    }
}