package cn.lblbc.shop.module.addr.select

import android.content.Intent
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmActivity
import cn.lblbc.shop.module.addr.AddAddressActivity
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity.Companion.requestCodeForSelectAddr
import cn.lblbc.shop.network.response.UserAddr
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.Constants.EXTRA_KEY_USER_ADDR_ID
import cn.lblbc.shop.utils.JsonUtil
import kotlinx.android.synthetic.main.activity_address_list.*
import kotlinx.android.synthetic.main.activity_goods.toolbar

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class SelectAddressActivity : BaseVmActivity<SelectAddressViewModel>() {
    private lateinit var adapter: SelectAddressAdapter
    private var selectedUserAddrId: String? = null
    override fun viewModelClass() = SelectAddressViewModel::class.java
    override fun layoutResId(): Int = R.layout.activity_address_list

    override fun initView() {
        initToolbar()
        selectedUserAddrId = intent.getStringExtra(EXTRA_KEY_USER_ADDR_ID)
        adapter = SelectAddressAdapter(mViewModel, selectedUserAddrId)
        addrListRv.adapter = adapter
    }

    override fun initData() {
        mViewModel.query()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initListeners() {
        adapter.setOnItemClick(this::onItemClick)
        modifyAddrTv.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddAddressActivity::class.java
                )
            )
        }
    }

    private fun onItemClick(data: UserAddr) {
        val intent = Intent()
        intent.putExtra(Constants.EXTRA_KEY_USER_ADDR, JsonUtil.toJson(data))
        setResult(requestCodeForSelectAddr, intent)
        finish()
    }

    override fun observe() {
        mViewModel.userAddrList.observe(this, {
            adapter.setData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        initData()
    }
}
