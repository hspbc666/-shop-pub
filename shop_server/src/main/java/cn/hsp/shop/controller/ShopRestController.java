package cn.hsp.shop.controller;

import cn.hsp.login.bean.response.Resp;
import cn.hsp.login.utils.JwtUtils;
import cn.hsp.shop.bean.CartItem;
import cn.hsp.shop.bean.CreateOrderReq;
import cn.hsp.shop.bean.CreateOrderResp;
import cn.hsp.shop.bean.Goods;
import cn.hsp.shop.bean.queryorder.QueryOrderResp;
import cn.hsp.shop.bean.queryorder.QueryOrderReq;
import cn.hsp.shop.service.CartService;
import cn.hsp.shop.service.GoodsService;
import cn.hsp.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.hsp.utils.Constants.OrderStatus.TO_DELIVER;

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
    private JwtUtils jwtUtils;

//    @GetMapping(value = "api/del/{id}")
//    public Resp<String> delBlog(@PathVariable long id) {
//        blogService.del(id);
//        return new Resp<>();
//    }
//
//    @PostMapping(value = "api/add")
//    public Resp<String> addBlog(@RequestBody Blog blog, @RequestHeader("Authorization") String authorization) {
//        final String authTokenPrefix = "Bearer ";
//        long userId = 0;
//        if (authorization != null && authorization.startsWith(authTokenPrefix)) {
//            String token = authorization.substring(authTokenPrefix.length());
//            userId = jwtUtils.getUserIdFromToken(token);
//        }
//        blogService.add(userId, blog.getTitle(), blog.getContent());
//        return new Resp<>();
//    }
//
//    @PostMapping(value = "api/modify")
//    public Resp<String> modifyBlog(@RequestBody Blog blog) {
//        blogService.modify(blog.getId(), blog.getTitle(), blog.getContent());
//        return new Resp<>();
//    }

    @GetMapping("goods/list")
    public Resp<List<Goods>> queryAllGoods() {
        Resp<List<Goods>> resp = new Resp<>();
        resp.setData(goodsService.queryAll());
        return resp;
    }

    @GetMapping("goods/query/{goodsId}")
    public Resp<Goods> queryGoods(@PathVariable String goodsId) {
        Resp<Goods> resp = new Resp<>();
        resp.setData(goodsService.queryById(goodsId));
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
        cartService.modifyCart(userId, goodsId, 1);
        return new Resp<>();
    }

    @GetMapping("cart/modify/{goodsId}/{quantity}")
    public Resp<Goods> modifyCart(@PathVariable String goodsId, @PathVariable int quantity, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        cartService.modifyCart(userId, goodsId, quantity);
        return new Resp<>();
    }

    @PostMapping("order/create")
    public Resp<CreateOrderResp> createOrder(@RequestBody CreateOrderReq req, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
        String orderId = orderService.createOrder(userId, req.getCartIdList());
        Resp<CreateOrderResp> resp = new Resp<>();
        resp.setData(CreateOrderResp.builder().orderId(orderId).build());
        return resp;
    }

    @GetMapping("order/pay/{orderId}")
    public Resp<CreateOrderResp> payForOrder(@PathVariable String orderId, @RequestHeader("Authorization") String authorization) {
        int userId = getUserIdFromHeader(authorization);
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

    private int getUserIdFromHeader(String authorization) {
        final String authTokenPrefix = "Bearer ";
        int userId = 0;
        if (authorization != null && authorization.startsWith(authTokenPrefix)) {
            String token = authorization.substring(authTokenPrefix.length());
            userId = jwtUtils.getUserIdFromToken(token);
        }
        return userId;
    }

//    @GetMapping("api/goods/list/{goodsId}")
//    public Resp<Goods> queryGoods(@PathVariable String goodsId) {
//        Resp<Goods> resp = new Resp<>();
//        resp.setData(goodsService.queryById(goodsId));
//        return resp;
//    }

//    @GetMapping("api/list/{userId}")
//    public Resp<List<Blog>> list(@PathVariable long userId) {
//        Resp<List<Blog>> resp = new Resp<>();
//        resp.setData(blogService.queryByUserId(userId));
//        return resp;
//    }
//
//    @GetMapping("api/query/{blogId}")
//    public Resp<Blog> query(@PathVariable long blogId) {
//        Resp<Blog> resp = new Resp<>();
//        resp.setData(blogService.query(blogId));
//        return resp;
//    }

}
