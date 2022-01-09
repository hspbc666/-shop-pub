package cn.hsp.shop.module.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.hsp.shop.R
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {
    private var index = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = "this Tab $index"
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            val fragment = OrderFragment()
            fragment.index = position
            return fragment
        }
    }
}