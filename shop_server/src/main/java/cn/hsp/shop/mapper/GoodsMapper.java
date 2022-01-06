package cn.hsp.shop.mapper;

import cn.hsp.shop.bean.Goods;
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

    @Select(value = "select * from goods where id = #{goodsId}")
    Goods queryById(@Param("goodsId") String goodsId);

//    @Insert(value = "insert into blog(userId,title,content) values (#{userId}, #{title}, #{content})")
//    void add(@Param("userId") long userId, @Param("title") String title, @Param("content") String content);
//
//    @Update(value = "update blog set title=#{title},content=#{content},lastUpdateTime=now() where id = #{id}")
//    void modify(@Param("id") long id, @Param("title") String title, @Param("content") String content);
//
//    @Delete("delete from blog where id = #{id}")
//    void del(@Param("id") long id);
}