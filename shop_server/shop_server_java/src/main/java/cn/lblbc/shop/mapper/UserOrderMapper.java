package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.UserOrder;
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
public interface UserOrderMapper {

    @Select(value = "select * from user_order where id = #{orderId}")
    UserOrder query(@Param("orderId") String orderId);

    @Select(value = "select * from user_order where user_id = #{userId} order by create_time desc")
    List<UserOrder> queryByUserId(@Param("userId") int userId);

    @Select(value = "select * from user_order where user_id = #{userId} AND status = #{status} order by create_time desc")
    List<UserOrder> queryByUserIdAndStatus(@Param("userId") int userId,@Param("status") int status);

    @Insert(value = "insert into user_order(id,user_id,status,create_time,user_addr_id) values (#{orderId}, #{userId}, #{status}, #{createTime}, #{addressId})")
    void add(@Param("orderId") String orderId, @Param("userId") int userId, @Param("status") int status, @Param("createTime") long createTime,@Param("addressId") String addressId);

    @Update(value = "update user_order set status=#{status} where id = #{orderId}")
    void modify(@Param("orderId") String orderId, @Param("status") int status);

    @Delete("delete from user_order where id = #{orderId}")
    void delete(@Param("orderId") String orderId);

}
