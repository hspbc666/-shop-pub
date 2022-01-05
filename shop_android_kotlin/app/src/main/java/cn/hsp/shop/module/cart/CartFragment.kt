package cn.hsp.shop.module.cart

import cn.hsp.shop.R
import cn.hsp.shop.base.BaseVmFragment

class CartFragment : BaseVmFragment<CartViewModel>() {
    override fun viewModelClass() = CartViewModel::class.java
    override fun layoutResId() = R.layout.fragment_cart
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_cart, container, false)
//    }
}