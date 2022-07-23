package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  http://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Repository
public interface GoodsMapper {
    @Select(value = "select * from goods")
    List<Goods> queryAll();

    @Select(value = "SELECT g.* FROM goods_category gc,goods g WHERE gc.goods_id=g.id AND gc.category_id=#{categoryId}")
    List<Goods> queryByCategory(@Param("categoryId") String categoryId);

    @Select(value = "select * from goods where id = #{goodsId}")
    Goods queryById(@Param("goodsId") String goodsId);

    @Select(value = "select * from goods where name like #{goodsName}")
    List<Goods> queryByName(@Param("goodsName") String goodsName);

}