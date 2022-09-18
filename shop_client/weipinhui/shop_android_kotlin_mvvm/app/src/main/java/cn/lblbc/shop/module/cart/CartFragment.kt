package cn.lblbc.shop.module.cart

import android.content.Intent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import cn.lblbc.lib.utils.getMoneyByYuan
import cn.lblbc.lib.utils.loadImage
import cn.lblbc.lib.view.selectRv.LblSelectRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.login.LoginManager
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity
import cn.lblbc.shop.network.CartItem
import cn.lblbc.shop.network.OrderDetail
import cn.lblbc.shop.utils.EXTRA_KEY_MONEY
import cn.lblbc.shop.utils.EXTRA_KEY_SIMPLE_ORDER
import cn.lblbc.shop.utils.calcSum2
import kotlinx.android.synthetic.main.item_cart_goods.view.*

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CartFragment : BaseVmFragment<CartViewModel>() {
    private lateinit var selectedCountTv: TextView
    private lateinit var cartPayLayout: View
    private lateinit var cartLoginLayout: View
    private lateinit var cartLoginTv: View
    private lateinit var moneyTv: TextView
    private lateinit var gotoCreateOrderTv: TextView
    private lateinit var lblRecyclerView: LblSelectRecyclerView<CartItem>

    override fun viewModelClass() = CartViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_cart

    override fun initView() {
        initRecyclerView()
        selectedCountTv = findViewById(R.id.selectedCountTv)
        cartPayLayout = findViewById(R.id.cartPayLayout)
        cartLoginLayout = findViewById(R.id.cartLoginLayout)
        cartLoginTv = findViewById(R.id.cartLoginTv)
        moneyTv = findViewById(R.id.moneyTv)
        gotoCreateOrderTv = findViewById(R.id.gotoCreateOrderTv)
    }

    private fun initRecyclerView() {
        lblRecyclerView = findViewById(R.id.lblRecyclerView)
        lblRecyclerView.setLayoutResId { R.layout.item_cart_goods }
        lblRecyclerView.setCheckBoxResId { R.id.cartItemCheckBox }
        lblRecyclerView.setonSelectionChanged { onSelectionChanged(it) }
        lblRecyclerView.setOnBind { itemView, data ->
            itemView.cartItemCheckBox.isChecked = false
            itemView.goodsNameTv.text = data.name
            itemView.quantityView.setData(data.quantity)
            itemView.goodsPriceTv.text = getMoneyByYuan(data.price)
            loadImage(itemView.goodsIv, data.squarePic)

            itemView.quantityView.setCallback {
                data.quantity = it
                onQuantityChanged(data, itemView.cartItemCheckBox.isChecked)
            }
        }
    }

    private fun onSelectionChanged(selectionItemList: List<CartItem>) {
        val count = selectionItemList.size
        moneyTv.text = calcSum2(selectionItemList)
        selectedCountTv.text = "已选($count)"
        if (count == 0) {
            gotoCreateOrderTv.isEnabled = false
            gotoCreateOrderTv.setBackgroundResource(R.drawable.capsule_bg_gray)
        } else {
            gotoCreateOrderTv.isEnabled = true
            gotoCreateOrderTv.setBackgroundResource(R.drawable.capsule_bg)
        }
    }

    private fun onQuantityChanged(cartItem: CartItem, isChecked: Boolean) {
        mViewModel.modifyCart(cartItem, isChecked)
//        launch(
//            action = { NetworkRepository.apiService.modifyCart(cartId, ModifyCartRequest(quantity)) },
//            onSuccess = { queryData() }
//        )
    }

    override fun initListeners() {
        cartLoginTv.setOnClickListener { gotoLogin() }
        gotoCreateOrderTv.setOnClickListener { gotoCreateOrder() }
    }

    private fun gotoLogin() {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    private fun gotoCreateOrder() {
        val intent = Intent(context, ConfirmOrderActivity::class.java)
        val cartItems = lblRecyclerView.getSelectedItems()
        val orderDetailList = cartItems.map { OrderDetail(it.goodsId, it.name, it.price, it.squarePic, it.quantity) }
        val orderInJson = cn.lblbc.lib.utils.JsonUtil.toJson(orderDetailList)
        intent.putExtra(EXTRA_KEY_SIMPLE_ORDER, orderInJson)
        intent.putExtra(EXTRA_KEY_MONEY, moneyTv.text)
        startActivity(intent)
    }

    private fun refreshPage() {
        if (LoginManager.isLoggedIn()) {
            queryData()
            cartLoginLayout.visibility = GONE
            cartPayLayout.visibility = VISIBLE
        } else {
            cartLoginLayout.visibility = VISIBLE
            cartPayLayout.visibility = GONE
        }
    }

    private fun queryData() {
        mViewModel.queryCart()
    }

    override fun observe() {
        mViewModel.dataList.observe(this) { lblRecyclerView.setData(it) }
        mViewModel.selectionChangeCount.observe(this) {
            var sum = 0L
            mViewModel.selectionItemList.forEach { sum += it.price * it.quantity }
            selectedCountTv.text =
                context?.getString(R.string.selected_count, mViewModel.selectionItemList.size)
            moneyTv.text = getMoneyByYuan(sum)
            if (mViewModel.selectionItemList.isEmpty()) {
                gotoCreateOrderTv.isEnabled = false
                gotoCreateOrderTv.setBackgroundResource(R.drawable.capsule_bg_gray)
            } else {
                gotoCreateOrderTv.isEnabled = true
                gotoCreateOrderTv.setBackgroundResource(R.drawable.capsule_bg)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}