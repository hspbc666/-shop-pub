package cn.hsp.shop.service;

import cn.hsp.shop.bean.CartItem;
import cn.hsp.shop.bean.CartSimpleItem;
import cn.hsp.shop.mapper.CartMapper;
import cn.hsp.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public List<CartItem> queryByUserId(int userId) {
        return cartMapper.queryByUserId(userId);
    }

    public void addToCart(int userId, String goodsId, int quantity) {
        CartSimpleItem cartSimpleItem = cartMapper.query(userId, goodsId);
        if (cartSimpleItem != null) {
            cartMapper.updateQuantity(cartSimpleItem.getId(), quantity + cartSimpleItem.getQuantity());
        } else {
            String id = IdGenerator.generateId();
            cartMapper.add(id, userId, goodsId);
        }
    }

    public void modifyCart(String cartId, int quantity) {
        CartSimpleItem cartSimpleItem = cartMapper.queryByCartId(cartId);
        if (cartSimpleItem != null) {
            if (quantity <= 0) {
                cartMapper.delete(cartId);
            } else {
                cartMapper.updateQuantity(cartId, quantity);
            }
        }
    }
//
//    public void modify(long id, String title, String content) {
//        goodsMapper.modify(id, title, content);
//    }
//
//    public void del(long id) {
//        goodsMapper.del(id);
//    }

//    public Goods queryById(int goodsId) {
//        return cartMapper.queryById(goodsId);
//    }
//
//    public List<Goods> queryAll() {
//        return cartMapper.queryAll();
//    }
}
