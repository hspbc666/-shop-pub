package cn.lblbc.shop.controller;

import cn.lblbc.login.bean.response.Resp;
import cn.lblbc.login.utils.JwtUtils;
import cn.lblbc.shop.bean.CartItem;
import cn.lblbc.shop.bean.CategoryInfo;
import cn.lblbc.shop.bean.Goods;
import cn.lblbc.shop.bean.UserAddr;
import cn.lblbc.shop.bean.createorder.CreateOrderFromCartReq;
import cn.lblbc.shop.bean.createorder.CreateOrderReq;
import cn.lblbc.shop.bean.createorder.CreateOrderResp;
import cn.lblbc.shop.bean.queryorder.QueryOrderReq;
import cn.lblbc.shop.bean.queryorder.QueryOrderResp;
import cn.lblbc.shop.service.*;
import cn.lblbc.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.lblbc.utils.Constants.OrderStatus.TO_DELIVER;

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
    private UserAddrService userAddrService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("goods/list")
    public Resp<List<Goods>> queryAllGoods() {
        Resp<List<Goods>> resp = new Resp<>();
        resp.setData(goodsService.queryAll());
        return resp;
    }

    @GetMapping("category")
    public Resp<List<CategoryInfo>> queryCategory() {
        Resp<List<CategoryInfo>> resp = new Resp<>();
        resp.setData(categoryService.query());
        return resp;
    }

    @GetMapping("goods/category/{categoryId}")
    public Resp<List<Goods>> queryGoodsByCategory(@PathVariable String categoryId) {
        Resp<List<Goods>> resp = new Resp<>();
        resp.setData(goodsService.queryByCategory(categoryId));
        return resp;
    }

    @GetMapping("goods/query/{goodsId}")
    public Resp<Goods> queryGoods(@PathVariable String goodsId) {
        Resp<Goods> resp = new Resp<>();
        resp.setData(goodsService.queryById(goodsId));
        return resp;
    }

    @GetMapping("goods/search/{keyword}")
    public Resp<List<Goods>> searchGoods(@PathVariable String keyword) {
        Resp<List<Goods>> resp = new Resp<>();
        resp.setData(goodsService.queryByName(keyword));
        return resp;
    }

    @GetMapping("cart/list")
    public Resp<List<CartItem>> queryCart(@RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<List<CartItem>> resp = new Resp<>();
        resp.setData(cartService.queryByUserId(userId));
        return resp;
    }

    @GetMapping("cart/add/{goodsId}")
    public Resp<Goods> addToCart(@PathVariable String goodsId, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        cartService.addToCart(userId, goodsId, 1);
        return new Resp<>();
    }

    @GetMapping("cart/modify/{cartId}/{quantity}")
    public Resp<Goods> modifyCart(@PathVariable String cartId, @PathVariable int quantity, @RequestHeader("Authorization") String authorization) {
        cartService.modifyCart(cartId, quantity);
        return new Resp<>();
    }

    @PostMapping("order/createFromCart")
    public Resp<CreateOrderResp> createFromCart(@RequestBody CreateOrderFromCartReq req, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String orderId = orderService.createOrderFromCart(userId, req.getCartIdList());
        Resp<CreateOrderResp> resp = new Resp<>();
        resp.setData(CreateOrderResp.builder().orderId(orderId).build());
        return resp;
    }

    @PostMapping("order/create")
    public Resp<CreateOrderResp> createOrder(@RequestBody CreateOrderReq req, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String orderId = orderService.createOrder(userId, req.getSimpleOrderInfoList());
        Resp<CreateOrderResp> resp = new Resp<>();
        resp.setData(CreateOrderResp.builder().orderId(orderId).build());
        return resp;
    }

    @GetMapping("order/pay/{orderId}")
    public Resp<CreateOrderResp> payForOrder(@PathVariable String orderId) {
        orderService.changeOrderStatus(orderId, TO_DELIVER);
        return new Resp<>();
    }

    @PostMapping("order/query")
    public Resp<List<QueryOrderResp>> queryOrder(@RequestBody QueryOrderReq req, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<List<QueryOrderResp>> resp = new Resp<>();
        resp.setData(orderService.queryOrder(userId, req.getOrderStatus()));
        return resp;
    }

    @GetMapping("order/query/{orderId}")
    public Resp<QueryOrderResp> queryOrder(@PathVariable String orderId) {
        Resp<QueryOrderResp> resp = new Resp<>();
        resp.setData(orderService.queryOrder(orderId));
        return resp;
    }

    @GetMapping("order/del/{orderId}")
    public Resp<String> deleteOrder(@PathVariable String orderId) {
        orderService.delete(orderId);
        return new Resp<>();
    }

    @GetMapping("addr/query")
    public Resp<List<UserAddr>> queryAddr(@RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<List<UserAddr>> resp = new Resp<>();
        resp.setData(userAddrService.query(userId));
        return resp;
    }

    @GetMapping("addr/query_default")
    public Resp<UserAddr> queryDefaultAddress(@RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        Resp<UserAddr> resp = new Resp<>();
        resp.setData(userAddrService.queryDefaultAddress(userId));
        return resp;
    }

    @PostMapping("addr/add")
    public Resp<String> addAddr(@RequestBody UserAddr userAddr, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String userAddrId = IdGenerator.generateId();
        userAddr.setId(userAddrId);
        userAddr.setUserId(userId);
        userAddrService.add(userId, userAddr);
        Resp<String> resp = new Resp<>();
        resp.setData(userAddrId);
        return new Resp<>();
    }

    @PostMapping("addr/modify")
    public Resp<List<UserAddr>> modifyAddr(@RequestBody UserAddr userAddr, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        userAddrService.modify(userId, userAddr);
        return new Resp<>();
    }

    @GetMapping("addr/del/{userAddrId}")
    public Resp<String> deleteAddr(@PathVariable String userAddrId) {
        userAddrService.delete(userAddrId);
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
