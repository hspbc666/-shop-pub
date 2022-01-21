package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface GoodsMapper {
    @Select(value = "select * from goods")
    List<Goods> queryAll();

    @Select(value = "SELECT g.* FROM goods_category gc,goods g WHERE gc.goods_id=g.id AND gc.category_id=#{categoryId}")
    List<Goods> queryByCategory(@Param("categoryId") String categoryId);

    @Select(value = "select * from goods where id = #{goodsId}")
    Goods queryById(@Param("goodsId") String goodsId);

    @Select(value = "select * from goods where name like \"%\"#{goodsName}\"%\"")
    List<Goods> queryByName(@Param("goodsName") String goodsName);

}