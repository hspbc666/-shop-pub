package cn.lblbc.shop.module.cart

import android.content.Intent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.lblbc.shop.R
import cn.lblbc.shop.base.BaseVmFragment
import cn.lblbc.shop.module.goods_detail.GoodsActivity
import cn.lblbc.shop.module.login.LoginActivity
import cn.lblbc.shop.module.login.LoginManager
import cn.lblbc.shop.module.order.confirm.ConfirmOrderFromCartActivity
import cn.lblbc.shop.network.response.CartItem
import cn.lblbc.shop.utils.Constants
import cn.lblbc.shop.utils.JsonUtil
import cn.lblbc.shop.utils.getMoneyByYuan
/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
class CartFragment : BaseVmFragment<CartViewModel>() {
    private lateinit var adapter: CartAdapter
    private lateinit var goodsListRv: RecyclerView
    private lateinit var goodsListSrl: SwipeRefreshLayout
    private lateinit var selectedCountTv: TextView
    private lateinit var cartPayLayout: View
    private lateinit var cartLoginLayout: View
    private lateinit var cartLoginTv: View
    private lateinit var sumTv: TextView
    private lateinit var gotoCreateOrderTv: TextView
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
        gotoCreateOrderTv = findViewById(R.id.gotoCreateOrderTv)
        goodsListRv.adapter = adapter
    }

    override fun initListeners() {
        adapter.setOnItemClick(this::onItemClick)
        goodsListSrl.setOnRefreshListener { refreshPage() }
        cartLoginTv.setOnClickListener {
            startActivity(Intent(context, LoginActivity::class.java))
        }
        gotoCreateOrderTv.setOnClickListener {
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
        mViewModel.dataList.observe(this, { adapter.setData(it) })
        mViewModel.selectionChangeCount.observe(this, {
            var sum = 0L
            mViewModel.selectionItemList.forEach { sum += it.price * it.quantity }
            selectedCountTv.text =
                context?.getString(R.string.selected_count, mViewModel.selectionItemList.size)
            sumTv.text = context?.getString(R.string.price, getMoneyByYuan(sum))
            if (mViewModel.selectionItemList.isEmpty()) {
                gotoCreateOrderTv.isEnabled = false
                gotoCreateOrderTv.setBackgroundResource(R.drawable.shape_btn_grey_bg)
            } else {
                gotoCreateOrderTv.isEnabled = true
                gotoCreateOrderTv.setBackgroundResource(R.drawable.shape_btn_bg)
            }
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