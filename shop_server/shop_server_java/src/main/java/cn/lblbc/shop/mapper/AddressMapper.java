package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.Address;
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
public interface AddressMapper {

    @Select(value = "SELECT * FROM user_addr where user_id = #{userId} order by default_address desc")
    List<Address> query(int userId);

    @Select(value = "SELECT * FROM user_addr where id = #{addressId}")
    Address queryById(@Param("addressId") String addressId);

    @Select(value = "SELECT ua.* FROM user_addr ua,user_order uo where uo.id = #{orderId} and uo.user_addr_id=ua.id ")
    Address queryByOrderId(@Param("orderId") String orderId);

    @Insert(value = "insert into user_addr(id,user_id,name,phone,address,default_address) values (#{id},#{userId},#{name}, #{phone}, #{address}, #{isDefault})")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("name") String name, @Param("phone") String phone, @Param("address") String address, @Param("isDefault") boolean isDefault);

    @Update(value = "update user_addr set name=#{name},phone=#{phone},address=#{address},default_address=#{isDefault} where id = #{id}")
    void modify(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address, @Param("isDefault") boolean isDefault);

    @Update(value = "update user_addr set default_address=0 where user_id = #{userId} ")
    void modifyAsUnDefault(@Param("userId") int userId);

    @Delete("delete from user_addr where id = #{id}")
    void delete(@Param("id") String id);
}
