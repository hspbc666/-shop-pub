package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.queryorder.FullOrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
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
