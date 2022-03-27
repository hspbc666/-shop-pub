package cn.lblbc.shop.controller;

import cn.lblbc.login.bean.response.Resp;
import cn.lblbc.login.utils.JwtUtils;
import cn.lblbc.shop.bean.CartItem;
import cn.lblbc.shop.bean.CategoryInfo;
import cn.lblbc.shop.bean.Goods;
import cn.lblbc.shop.bean.Address;
import cn.lblbc.shop.bean.createorder.CreateOrderFromCartReq;
import cn.lblbc.shop.bean.createorder.CreateOrderReq;
import cn.lblbc.shop.bean.createorder.CreateOrderResp;
import cn.lblbc.shop.bean.queryorder.QueryOrderResp;
import cn.lblbc.shop.service.*;
import cn.lblbc.utils.LblIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.lblbc.utils.Constants.OrderStatus.TO_DELIVER;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

@RestController
@RequestMapping("/shop")
public class ShopRestController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("categories")
    public Resp<List<CategoryInfo>> queryCategory() {
        Resp<List<CategoryInfo>> resp = new Resp<>();
        resp.setData(categoryService.query());
        return resp;
    }

    @GetMapping("goods")
    public Resp<List<Goods>> queryGoodsByCategory(@RequestParam("categoryId") String categoryId) {
        Resp<List<Goods>> resp = new Resp<>();
        resp.setData(goodsService.queryByCategory(categoryId));
        return resp;
    }

    @GetMapping("goodsBySearch")
    public Resp<List<Goods>> searchGoods(@RequestParam("keyword") String keyword) {
        Resp<List<Goods>> resp = new Resp<>();
        resp.setData(goodsService.queryByName(keyword));
        return resp;
    }

    @GetMapping("goods/{goodsId}")
    public Resp<Goods> queryGoods(@PathVariable String goodsId) {
        Resp<Goods> resp = new Resp<>();
        resp.setData(goodsService.queryById(goodsId));
        return resp;
    }

    @GetMapping("cart")
    public Resp<List<CartItem>> queryCart(@RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<List<CartItem>> resp = new Resp<>();
        resp.setData(cartService.queryByUserId(userId));
        return resp;
    }

    @GetMapping("cart/{cartIds}")
    public Resp<List<CartItem>> queryCartByIds(@PathVariable String cartIds) {
        Resp<List<CartItem>> resp = new Resp<>();
        resp.setData(cartService.queryByCartIds(cartIds));
        return resp;
    }

    @PostMapping("cart/{goodsId}")
    public Resp<Goods> addToCart(@PathVariable String goodsId, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        cartService.addToCart(userId, goodsId, 1);
        return new Resp<>();
    }

    @PutMapping("cart/{cartId}/{quantity}")
    public Resp<Goods> modifyCart(@PathVariable String cartId, @PathVariable int quantity, @RequestHeader("Authorization") String authorization) {
        cartService.modifyCart(cartId, quantity);
        return new Resp<>();
    }

    @GetMapping("orders")
    public Resp<List<QueryOrderResp>> queryByStatus(@RequestParam("orderStatus") int orderStatus, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<List<QueryOrderResp>> resp = new Resp<>();
        resp.setData(orderService.queryOrder(userId, orderStatus));
        return resp;
    }

    @PostMapping("orders")
    public Resp<CreateOrderResp> createOrder(@RequestBody CreateOrderReq req, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String orderId = orderService.createOrder(userId, req.getGoodsId(), req.getAddressId());
        Resp<CreateOrderResp> resp = new Resp<>();
        resp.setData(CreateOrderResp.builder().orderId(orderId).build());
        return resp;
    }

    @PostMapping("ordersFromCart")
    public Resp<CreateOrderResp> createFromCart(@RequestBody CreateOrderFromCartReq req, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String orderId = orderService.createOrderFromCart(userId, req.getCartIdList(), req.getAddressId());
        Resp<CreateOrderResp> resp = new Resp<>();
        resp.setData(CreateOrderResp.builder().orderId(orderId).build());
        return resp;
    }

    @GetMapping("orders/{orderId}")
    public Resp<QueryOrderResp> queryOrder(@PathVariable String orderId) {
        Resp<QueryOrderResp> resp = new Resp<>();
        resp.setData(orderService.queryOrder(orderId));
        return resp;
    }

    @DeleteMapping("orders/{orderId}")
    public Resp<String> deleteOrder(@PathVariable String orderId) {
        orderService.delete(orderId);
        return new Resp<>();
    }

    @GetMapping("addrs")
    public Resp<List<Address>> queryAddress(@RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<List<Address>> resp = new Resp<>();
        resp.setData(addressService.query(userId));
        return resp;
    }

    @GetMapping("addrs/{addressId}")
    public Resp<Address> queryAddressById(@PathVariable String addressId) {
        Resp<Address> resp = new Resp<>();
        resp.setData(addressService.queryById(addressId));
        return resp;
    }

    @GetMapping("addr_default")
    public Resp<Address> queryDefaultAddress(@RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<Address> resp = new Resp<>();
        resp.setData(addressService.queryDefaultAddress(userId));
        return resp;
    }

    @PostMapping("addrs")
    public Resp<String> addAddress(@RequestBody Address address, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String addressId = LblIdGenerator.generateId();
        address.setId(addressId);
        address.setUserId(userId);
        addressService.add(userId, address);
        Resp<String> resp = new Resp<>();
        resp.setData(addressId);
        return new Resp<>();
    }

    @PutMapping("addrs")
    public Resp<List<Address>> modifyAddress(@RequestBody Address address, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        addressService.modify(userId, address);
        return new Resp<>();
    }

    @DeleteMapping("addrs/{addressId}")
    public Resp<String> deleteAddress(@PathVariable String addressId) {
        addressService.delete(addressId);
        return new Resp<>();
    }

    private int getUserIdFromHeader(String authorization) {
        final String authTokenPrefix = "Bearer ";
        int userId = 0;
        if (authorization != null && authorization.startsWith(authTokenPrefix)) {
            String token = authorization.substring(authTokenPrefix.length());
            userId = jwtUtils.getUserIdFromToken(token);
        }
        return userId;
    }
}