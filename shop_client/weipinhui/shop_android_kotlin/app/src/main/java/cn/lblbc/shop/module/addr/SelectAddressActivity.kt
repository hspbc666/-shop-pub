package cn.lblbc.shop.module.addr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.launch
import cn.lblbc.lib.utils.show
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS_ID
import kotlinx.android.synthetic.main.activity_select_address.*
import kotlinx.android.synthetic.main.item_select_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SelectAddressActivity : AppCompatActivity() {
    private var selectedAddressId: String? = null
    private lateinit var lblRecyclerView: LblRecyclerView<Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_address)
        initViews()
        initListeners()
    }

    private fun initViews() {
        selectedAddressId = intent.getStringExtra(EXTRA_KEY_ADDRESS_ID)
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_select_addr }
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.userNameTv.text = data.name
            itemView.phoneTv.text = data.phone
            itemView.addressTv.text = data.address
            itemView.defaultAddrTv.show(data.defaultAddress)
            itemView.selectAddrRadioBtn.isChecked = false
            if (data.id == selectedAddressId) {
                itemView.selectAddrRadioBtn.isChecked = true
            }
            itemView.selectAddrRadioBtn.setOnClickListener { onItemClick(data) }
        }
        lblRecyclerView.setOnItemClick { onItemClick(it) }
    }

    private fun initListeners() {
        toolbar.setOnClickListener { finish() }
        gotoAddAddrTv.setOnClickListener { gotoAddAddress() }
    }

    private fun gotoAddAddress() {
        startActivity(Intent(this, AddAddressActivity::class.java))
    }

    private fun onItemClick(data: Address) {
        val intent = Intent()
        intent.putExtra(EXTRA_KEY_ADDRESS, JsonUtil.toJson(data))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        queryData()
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryAddress() },
            onSuccess = { it?.data?.let { data -> lblRecyclerView.setData(data) } }
        )
    }
}
