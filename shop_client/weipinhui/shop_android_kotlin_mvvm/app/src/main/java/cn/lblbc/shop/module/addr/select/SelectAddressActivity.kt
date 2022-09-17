package cn.lblbc.shop.module.addr.select

import android.content.Intent
import cn.lblbc.lib.utils.JsonUtil
import cn.lblbc.lib.utils.show
import cn.lblbc.lib.view.LblRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.addr.AddAddressActivity
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity.Companion.requestCodeForSelectAddr
import cn.lblbc.shop.network.Address
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS
import cn.lblbc.shop.utils.EXTRA_KEY_ADDRESS_ID
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_goods.toolbar
import kotlinx.android.synthetic.main.item_select_addr.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SelectAddressActivity : BaseVmActivity<SelectAddressViewModel>() {
    private lateinit var lblRecyclerView: LblRecyclerView<Address>
    private var selectedAddressId: String? = null
    override fun viewModelClass() = SelectAddressViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_address_list

    override fun initData() {
        mViewModel.query()
    }

    override fun initView() {
        initToolbar()
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

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initListeners() {
        gotoAddAddrTv.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }
    }

    private fun onItemClick(data: Address) {
        val intent = Intent()
        intent.putExtra(EXTRA_KEY_ADDRESS, JsonUtil.toJson(data))
        setResult(requestCodeForSelectAddr, intent)
        finish()
    }

    override fun observe() {
        mViewModel.addressList.observe(this) {
            lblRecyclerView.setData(it)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.query()
    }
}
