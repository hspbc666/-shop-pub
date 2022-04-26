package cn.lblbc.shop.service;

import cn.lblbc.shop.bean.Goods;
import cn.lblbc.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public Goods queryById(String goodsId) {
        return goodsMapper.queryById(goodsId);
    }

    public List<Goods> queryByName(String goodsName) {
        return goodsMapper.queryByName("%" + goodsName + "%");
    }

    public List<Goods> queryAll() {
        return goodsMapper.queryAll();
    }

    public List<Goods> queryByCategory(String categoryId) {
        return goodsMapper.queryByCategory(categoryId);
    }
}
