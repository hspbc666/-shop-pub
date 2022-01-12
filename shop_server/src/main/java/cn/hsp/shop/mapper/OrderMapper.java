package cn.hsp.shop.mapper;

import cn.hsp.shop.bean.CartItem;
import cn.hsp.shop.bean.UserOrder;
import cn.hsp.shop.bean.queryorder.FullOrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface OrderMapper {

    @Insert(value = "insert into orders(id,order_id,goods_id,quantity) values (#{id}, #{orderId}, #{goodsId}, #{quantity})")
    void add(@Param("id") String id, @Param("orderId") String orderId, @Param("goodsId") String goodsId, @Param("quantity") int quantity);

    @Select(value = "SELECT g.id as goods_id,g.name,g.price,g.square_pic,o.quantity,uo.status FROM user_order uo,orders o,goods g WHERE uo.id=o.order_id AND o.goods_id=g.id AND o.order_id=#{orderId}")
    List<FullOrderInfo> queryByOrderId(@Param("orderId") String orderId);

    @Delete("delete from orders where order_id = #{orderId}")
    void delete(@Param("orderId") String orderId);
}
