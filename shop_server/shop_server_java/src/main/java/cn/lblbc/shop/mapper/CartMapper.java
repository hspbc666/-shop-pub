package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.CartItem;
import cn.lblbc.shop.bean.CartSimpleItem;
import cn.lblbc.shop.bean.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  https://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Repository
public interface CartMapper {

    @Select(value = "SELECT * FROM cart WHERE user_id=#{userId} AND goods_id= #{goodsId}")
    CartSimpleItem query(@Param("userId") int userId, @Param("goodsId") String goodsId);

    @Select(value = "SELECT * FROM cart WHERE id=#{cartId}")
    CartSimpleItem queryByCartId( @Param("cartId") String cartId);

    @Select({"<script> " +
            " SELECT g.id as goods_id,g.name,g.price,g.long_pic,g.square_pic,c.quantity,c.id FROM cart c,goods g WHERE c.goods_id=g.id " +
            " <if test = \" cartIds != null \"> " +
            " AND c.id in " +
            " <foreach item = 'item' index = 'index' collection = 'cartIds' open = '(' separator = ',' close = ')' > " +
            " #{item} " +
            " </foreach> " +
            " </if> " +
            "</script>"})
    List<CartItem> queryByCartIds(@Param("cartIds") String[] cartIds);

    @Select(value = "SELECT g.id as goods_id,g.name,g.price,g.long_pic,g.square_pic,c.quantity,c.id FROM cart c,goods g WHERE c.goods_id=g.id AND c.user_id=#{userId}")
    List<CartItem> queryByUserId(@Param("userId") int userId);

    @Insert(value = "insert into cart(id,user_id,goods_id,quantity) values (#{id},#{userId}, #{goodsId}, 1)")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("goodsId") String goodsId);

    @Update(value = "update cart set quantity=#{quantity} where id = #{id}")
    void modifyQuantity(@Param("id") String id, @Param("quantity") int quantity);

    @Delete("delete from cart where id = #{id}")
    void delete(@Param("id") String id);
}
