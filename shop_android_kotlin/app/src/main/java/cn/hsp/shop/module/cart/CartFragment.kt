package cn.hsp.shop.module.cart

import android.content.Intent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment
import cn.hsp.shop.module.goods_detail.GoodsActivity
import cn.hsp.shop.module.login.LoginActivity
import cn.hsp.shop.module.login.LoginManager
import cn.hsp.shop.module.order.ConfirmOrderFromCartActivity
import cn.hsp.shop.network.response.CartItem
import cn.hsp.shop.utils.Constants
import cn.hsp.shop.utils.JsonUtil
import cn.hsp.shop.utils.getMoneyByYuan

class CartFragment : BaseVmFragment<CartViewModel>() {
    private lateinit var adapter: CartAdapter
    private lateinit var goodsListRv: RecyclerView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    private lateinit var selectedCountTv: TextView
    private lateinit var cartPayLayout: View
    private lateinit var cartLoginLayout: View
    private lateinit var cartLoginTv: View
    private lateinit var sumTv: TextView
    private lateinit var createOrderTv: TextView
    override fun viewModelClass() = CartViewModel::class.java
    override fun layoutResId(): Int = R.layout.fragment_cart

    override fun initView() {
        adapter = CartAdapter(mViewModel)
        goodsListRv = findViewById(R.id.goodsListRv)
        goodsListSrl = findViewById(R.id.goodsListSrl)
        selectedCountTv = findViewById(R.id.selectedCountTv)
        cartPayLayout = findViewById(R.id.cartPayLayout)
        cartLoginLayout = findViewById(R.id.cartLoginLayout)
        cartLoginTv = findViewById(R.id.cartLoginTv)
        sumTv = findViewById(R.id.sumTv)
        createOrderTv = findViewById(R.id.createOrderTv)
        goodsListRv.adapter = adapter
    }

    override fun initListeners() {
        adapter.setOnItemClick(this::onItemClick)
        goodsListSrl.setOnRefreshListener { refreshPage() }
        cartLoginTv.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
        createOrderTv.setOnClickListener {
            val intent = Intent(context, ConfirmOrderFromCartActivity::class.java)
            val cartItems = mViewModel.selectionItemList
            val cartItemsInJson = JsonUtil.toJson(cartItems)
            intent.putExtra(Constants.EXTRA_KEY_CART_ITEMS, cartItemsInJson)
            intent.putExtra(Constants.EXTRA_KEY_COST_SUM, sumTv.text)
            startActivity(intent)
        }
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
        mViewModel.queryCart(
            onComplete = {
                goodsListSrl.isRefreshing = false
            })
    }

    override fun observe() {
        mViewModel.dataList.observe(this, Observer { adapter.setData(it) })
        mViewModel.selectionChangeCount.observe(this, Observer {
            var sum = 0L
            mViewModel.selectionItemList.forEach { sum += it.price * it.quantity }
            selectedCountTv.text =
                context?.getString(R.string.selected_count, mViewModel.selectionItemList.size)
            sumTv.text = context?.getString(R.string.price, getMoneyByYuan(sum))
        })
    }

    private fun onItemClick(cartItem: CartItem) {
        val intent = Intent(context, GoodsActivity::class.java)
        intent.putExtra(Constants.EXTRA_KEY_GOODS_ID, cartItem.goodsId)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        refreshPage()
    }
}