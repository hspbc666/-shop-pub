package cn.lblbc.shop.module.addr

import android.content.Intent
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.show
import cn.lblbc.lib.utils.showDeleteDialog
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS_INFO
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.item_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class AddressListActivity : BaseVmActivity<AddressViewModel>() {
    private lateinit var lblRecyclerView: LblRecyclerView<Address>
    override fun viewModelClass() = AddressViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_address_list
    override fun initView() {
        initToolbar()
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

    override fun initData() {
        mViewModel.query()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initListeners() {
        gotoAddAddrTv.setOnClickListener { startActivity(Intent(this, AddAddressActivity::class.java)) }
    }

    override fun observe() {
        mViewModel.addressList.observe(this) {
            lblRecyclerView.setData(it)
        }
    }

    private fun onDeleteBtnClick(addressId: String) {
        val content = "确认删除当前收货地址么？"
        showDeleteDialog(content) { deleteAddress(addressId) }
    }

    private fun deleteAddress(addressId: String) {
        mViewModel.deleteAddress(addressId){
            mViewModel.query()
        }
    }

    private fun gotoEditAddressPage(data: Address) {
        val intent = Intent(this, EditAddressActivity::class.java)
        intent.putExtra(EXTRA_KEY_ADDRESS_INFO, JsonUtil.toJson(data))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.query()
    }
}
