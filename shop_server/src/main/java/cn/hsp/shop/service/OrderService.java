package cn.hsp.shop.service;

import cn.hsp.shop.bean.CartSimpleItem;
import cn.hsp.shop.mapper.CartMapper;
import cn.hsp.shop.mapper.OrderMapper;
import cn.hsp.shop.mapper.UserOrderMapper;
import cn.hsp.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.hsp.utils.Constants.OrderStatus.TO_PAY;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Service
public class OrderService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    public String createOrder(int userId, List<String> cartIdList) {
        String userOrderId = IdGenerator.generateId();
        String orderId = IdGenerator.generateId();
        userOrderMapper.add(userOrderId, userId, orderId, TO_PAY);

        for (String cartId : cartIdList) {
            CartSimpleItem cartItem = cartMapper.queryByCartId(cartId);
            String id = IdGenerator.generateId();
            orderMapper.add(id, orderId, userId, cartItem.getGoodsId(), cartItem.getQuantity());
            cartMapper.delete(cartItem.getId());
        }
        return orderId;
    }

    public void changeOrderStatus(String orderId,int status) {
        userOrderMapper.update(orderId,status);
    }
}
