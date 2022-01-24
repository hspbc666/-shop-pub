package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.UserOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface UserOrderMapper {

    @Select(value = "select * from user_order where id = #{orderId}")
    UserOrder query(@Param("orderId") String orderId);

    @Select(value = "select * from user_order where user_id = #{userId} order by create_time desc")
    List<UserOrder> queryByUserId(@Param("userId") int userId);

    @Select(value = "select * from user_order where user_id = #{userId} AND status = #{status} order by create_time desc")
    List<UserOrder> queryByUserIdAndStatus(@Param("userId") int userId,@Param("status") int status);

    @Insert(value = "insert into user_order(id,user_id,status,create_time) values (#{orderId}, #{userId}, #{status}, #{create_time})")
    void add(@Param("orderId") String orderId, @Param("userId") int userId, @Param("status") int status, @Param("create_time") long createTime);

    @Update(value = "update user_order set status=#{status} where id = #{orderId}")
    void modify(@Param("orderId") String orderId, @Param("status") int status);

    @Delete("delete from user_order where id = #{orderId}")
    void delete(@Param("orderId") String orderId);

}