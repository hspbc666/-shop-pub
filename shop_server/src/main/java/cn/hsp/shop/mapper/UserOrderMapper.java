package cn.hsp.shop.mapper;

import cn.hsp.shop.bean.CartItem;
import cn.hsp.shop.bean.CartSimpleItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface CartMapper {

    @Select(value = "SELECT * FROM cart WHERE user_id=#{userId} AND goods_id= #{goodsId}")
    CartSimpleItem query(@Param("userId") int userId, @Param("goodsId") String goodsId);

    @Select(value = "SELECT * FROM cart WHERE id=#{cartId}")
    CartItem queryByCartId( @Param("cartId") String cartId);

    @Select(value = "SELECT g.*,c.quantity FROM cart c,goods g WHERE c.goods_id=g.id AND c.user_id=#{userId}")
    List<CartItem> queryByUserId(@Param("userId") int userId);

    @Insert(value = "insert into cart(id,user_id,goods_id,quantity) values (#{id},#{userId}, #{goodsId}, 1)")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("goodsId") String goodsId);

    @Update(value = "update cart set quantity=#{quantity} where id = #{id}")
    void updateQuantity(@Param("id") String id, @Param("quantity") int quantity);

    @Delete("delete from cart where id = #{id}")
    void delete(@Param("id") String id);
}
