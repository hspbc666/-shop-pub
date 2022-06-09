package cn.lblbc.shop.module.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.lblbc.lib.utils.*
import cn.lblbc.lib.view.selectRv.LblSelectRecyclerView
import cn.lblbc.shop.R
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.mine.LoginManager
import cn.lblbc.shop.module.order.confirm.ConfirmOrderActivity
import cn.lblbc.shop.network.CartItem
import cn.lblbc.shop.network.ModifyCartRequest
import cn.lblbc.shop.network.NetworkRepository
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
class CartFragment : Fragment() {
    private lateinit var selectedCountTv: TextView
    private lateinit var cartPayLayout: View
    private lateinit var cartLoginLayout: View
    private lateinit var cartLoginTv: View
    private lateinit var moneyTv: TextView
    private lateinit var gotoCreateOrderTv: TextView
    private lateinit var lblRecyclerView: LblSelectRecyclerView<CartItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        initViews(view)
        initListeners()
        return view
    }

    private fun initViews(view: View) {
        initRecyclerView(view)
        selectedCountTv = view.findViewById(R.id.selectedCountTv)
        cartPayLayout = view.findViewById(R.id.cartPayLayout)
        cartLoginLayout = view.findViewById(R.id.cartLoginLayout)
        cartLoginTv = view.findViewById(R.id.cartLoginTv)
        moneyTv = view.findViewById(R.id.moneyTv)
        gotoCreateOrderTv = view.findViewById(R.id.gotoCreateOrderTv)
    }

    private fun initRecyclerView(view: View) {
        lblRecyclerView = view.findViewById(R.id.lblRecyclerView)
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
                onQuantityChanged(data.id, it)
            }
        }
    }

    private fun initListeners() {
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
        val orderInJson = JsonUtil.toJson(orderDetailList)
        intent.putExtra(EXTRA_KEY_SIMPLE_ORDER, orderInJson)
        intent.putExtra(EXTRA_KEY_MONEY, moneyTv.text)
        startActivity(intent)
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

    private fun onQuantityChanged(cartId: String, quantity: Int) {
        launch(
            action = { NetworkRepository.apiService.modifyCart(cartId, ModifyCartRequest(quantity)) },
            onSuccess = { queryData() }
        )
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }

    private fun refreshPage() {
        if (LoginManager.isLoggedIn()) {
            queryData()
            cartLoginLayout.hide()
            cartPayLayout.show()
        } else {
            lblRecyclerView.clearData()
            cartLoginLayout.show()
            cartPayLayout.hide()
        }
    }

    private fun queryData() {
        launch(
            action = { NetworkRepository.apiService.queryCart() },
            onSuccess = { it?.data?.let { data -> processResponse(data) } }
        )
    }

    private fun processResponse(data: List<CartItem>) {
        lblRecyclerView.setData(data)
    }
}
