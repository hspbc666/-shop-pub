package cn.lblbc.shop.service;

import cn.lblbc.shop.bean.CartItem;
import cn.lblbc.shop.bean.CartSimpleItem;
import cn.lblbc.shop.mapper.CartMapper;
import cn.lblbc.utils.LblIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  https://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public List<CartItem> queryByUserId(int userId) {
        return cartMapper.queryByUserId(userId);
    }

    public List<CartItem> queryByCartIds(String cartIds) {
        return cartMapper.queryByCartIds(cartIds.split(","));
    }

    public void addToCart(int userId, String goodsId, int quantity) {
        CartSimpleItem cartSimpleItem = cartMapper.query(userId, goodsId);
        if (cartSimpleItem != null) {
            cartMapper.modifyQuantity(cartSimpleItem.getId(), quantity + cartSimpleItem.getQuantity());
        } else {
            String id = LblIdGenerator.generateId();
            cartMapper.add(id, userId, goodsId);
        }
    }

    public void modifyCart(String cartId, int quantity) {
        CartSimpleItem cartSimpleItem = cartMapper.queryByCartId(cartId);
        if (cartSimpleItem != null) {
            if (quantity <= 0) {
                cartMapper.delete(cartId);
            } else {
                cartMapper.modifyQuantity(cartId, quantity);
            }
        }
    }
}
