package cn.hsp.shop.controller;

import cn.hsp.login.bean.response.Resp;
import cn.hsp.login.utils.JwtUtils;
import cn.hsp.shop.bean.Goods;
import cn.hsp.shop.service.CartService;
import cn.hsp.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopRestController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CartService cartService;

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
    public Resp<Goods> queryGoods(@PathVariable int goodsId) {
        Resp<Goods> resp = new Resp<>();
        resp.setData(goodsService.queryById(goodsId));
        return resp;
    }

    @GetMapping("cart/add/{goodsId}")
    public Resp<Goods> addToCart(@PathVariable int goodsId, @RequestHeader("Authorization") String authorization) {
        long userId = getUserIdFromHeader(authorization);
        cartService.add(userId, goodsId);
        return new Resp<>();
    }

    private long getUserIdFromHeader(String authorization) {
        final String authTokenPrefix = "Bearer ";
        long userId = 0;
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
