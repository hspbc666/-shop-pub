package cn.lblbc.shop.module.addr

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.launch
import cn.lblbc.lib.utils.show
import cn.lblbc.lib.utils.showDeleteDialog
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.network.NetworkRepository
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS_INFO
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.item_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddressListActivity : AppCompatActivity() {
    private lateinit var lblRecyclerView: LblRecyclerView<Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        initViews()
        initListeners()
    }

    private fun initViews() {
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_addr }
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.userNameTv.text = data.name
            itemView.phoneTv.text = data.phone
            itemView.addressTv.text = data.address
            itemView.defaultAddrTv.show(data.defaultAddress)
            itemView.addrDelTv.setOnClickListener { onDeleteBtnClick(data.id) }
            itemView.addrEditTv.setOnClickListener { gotoEditAddressPage(data) }
        }
    }

    private fun initListeners() {
        gotoAddAddrTv.setOnClickListener { gotoAddAddress() }
        toolbar.setOnClickListener { finish() }
    }

    private fun gotoAddAddress() {
        startActivity(Intent(this, AddAddressActivity::class.java))
    }

    private fun onDeleteBtnClick(addressId: String) {
        val content = "确认删除当前收货地址么？"
        showDeleteDialog(content) { deleteAddress(addressId) }
    }

    private fun deleteAddress(addressId: String) {
        launch(
            action = { NetworkRepository.apiService.deleteAddress(addressId) },
            onSuccess = { queryData() }
        )
    }

    private fun gotoEditAddressPage(data: Address) {
        val intent = Intent(this, EditAddressActivity::class.java)
        intent.putExtra(EXTRA_KEY_ADDRESS_INFO, JsonUtil.toJson(data))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        queryData()
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryAddress() },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: List<Address>?) {
        data?.let { lblRecyclerView.setData(it) }
    }
}