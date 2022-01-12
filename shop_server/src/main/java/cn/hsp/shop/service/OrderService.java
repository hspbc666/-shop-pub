package cn.hsp.shop.service;

import cn.hsp.shop.bean.CartSimpleItem;
import cn.hsp.shop.bean.SimpleOrderInfo;
import cn.hsp.shop.bean.UserOrder;
import cn.hsp.shop.bean.queryorder.FullOrderInfo;
import cn.hsp.shop.bean.queryorder.QueryOrderResp;
import cn.hsp.shop.mapper.CartMapper;
import cn.hsp.shop.mapper.OrderMapper;
import cn.hsp.shop.mapper.UserOrderMapper;
import cn.hsp.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static cn.hsp.utils.Constants.OrderStatus.ORDER_ALL;
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

    @Transactional
    public String createOrderFromCart(int userId, List<String> cartIdList) {
        String orderId = IdGenerator.generateId();
        addUserOder(userId, orderId);

        for (String cartId : cartIdList) {
            CartSimpleItem cartItem = cartMapper.queryByCartId(cartId);
            String id = IdGenerator.generateId();
            orderMapper.add(id, orderId, cartItem.getGoodsId(), cartItem.getQuantity());
            cartMapper.delete(cartItem.getId());
        }
        return orderId;
    }

    @Transactional
    public String createOrder(int userId, List<SimpleOrderInfo> simpleOrderInfoList) {
        String orderId = IdGenerator.generateId();
        addUserOder(userId, orderId);

        for (SimpleOrderInfo simpleOrderInfo : simpleOrderInfoList) {
            String id = IdGenerator.generateId();
            orderMapper.add(id, orderId, simpleOrderInfo.getGoodsId(), simpleOrderInfo.getQuantity());
        }
        return orderId;
    }

    private void addUserOder(int userId, String orderId) {
        userOrderMapper.add(orderId, userId, TO_PAY, System.currentTimeMillis());
    }

    public void changeOrderStatus(String orderId, int status) {
        userOrderMapper.update(orderId, status);
    }


    public List<QueryOrderResp> queryOrder(int userId, int orderStatus) {
        List<UserOrder> list;
        if (orderStatus == ORDER_ALL) {
            list = userOrderMapper.queryByUserId(userId);
        } else {
            list = userOrderMapper.queryByUserIdAndStatus(userId, orderStatus);
        }
        List<QueryOrderResp> queryOrderRespList = new ArrayList<>();
        for (UserOrder userOrder : list) {
            queryOrderRespList.add(queryForUserOrder(userOrder));
        }
        return queryOrderRespList;
    }

    private QueryOrderResp queryForUserOrder(UserOrder userOrder) {
        String orderId = userOrder.getId();
        int status = userOrder.getStatus();
        long createTime = userOrder.getCreateTime();
        List<FullOrderInfo> fullOrderInfoList = orderMapper.queryByOrderId(orderId);
        return QueryOrderResp.builder()
                .orderId(orderId).status(status).createTime(createTime)
                .list(fullOrderInfoList).build();
    }

    public QueryOrderResp queryOrder(String orderId) {
        UserOrder userOrder = userOrderMapper.query(orderId);
        return queryForUserOrder(userOrder);
    }

    @Transactional
    public void deleteOrder(String orderId) {
        orderMapper.delete(orderId);
        userOrderMapper.delete(orderId);
    }
}
