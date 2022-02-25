package cn.lblbc.shop.service;

import cn.lblbc.shop.bean.CartSimpleItem;
import cn.lblbc.shop.bean.UserAddr;
import cn.lblbc.shop.bean.UserOrder;
import cn.lblbc.shop.bean.queryorder.FullOrderInfo;
import cn.lblbc.shop.bean.queryorder.QueryOrderResp;
import cn.lblbc.shop.mapper.CartMapper;
import cn.lblbc.shop.mapper.OrderMapper;
import cn.lblbc.shop.mapper.UserAddrMapper;
import cn.lblbc.shop.mapper.UserOrderMapper;
import cn.lblbc.utils.LblIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static cn.lblbc.utils.Constants.OrderStatus.*;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Service
public class OrderService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserAddrMapper userAddrMapper;

    @Transactional
    public String createOrderFromCart(int userId, List<String> cartIdList, String userAddrId) {
        String orderId = LblIdGenerator.generateId();
        addUserOder(userId, orderId, userAddrId);

        for (String cartId : cartIdList) {
            if (cartId.isEmpty()) {
                continue;
            }
            CartSimpleItem cartItem = cartMapper.queryByCartId(cartId);
            String id = LblIdGenerator.generateId();
            orderMapper.add(id, orderId, cartItem.getGoodsId(), cartItem.getQuantity());
            cartMapper.delete(cartItem.getId());
        }
        return orderId;
    }

    @Transactional
    public String createOrder(int userId, String goodsId, String userAddrId) {
        String orderId = LblIdGenerator.generateId();
        addUserOder(userId, orderId, userAddrId);
        String id = LblIdGenerator.generateId();
        orderMapper.add(id, orderId, goodsId, 1);
        return orderId;
    }

    private void addUserOder(int userId, String orderId, String userAddrId) {
        userOrderMapper.add(orderId, userId, TO_DELIVER, System.currentTimeMillis(), userAddrId);
    }

    public void changeOrderStatus(String orderId, int status) {
        userOrderMapper.modify(orderId, status);
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
        UserAddr userAddr = userAddrMapper.queryByOrderId(orderId);
        return QueryOrderResp.builder()
                .orderId(orderId).status(status).createTime(createTime)
                .userAddr(userAddr)
                .list(fullOrderInfoList).build();
    }

    public QueryOrderResp queryOrder(String orderId) {
        UserOrder userOrder = userOrderMapper.query(orderId);
        return queryForUserOrder(userOrder);
    }

    @Transactional
    public void delete(String orderId) {
        orderMapper.delete(orderId);
        userOrderMapper.delete(orderId);
    }
}
