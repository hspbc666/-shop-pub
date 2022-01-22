package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.UserAddr;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface UserAddrMapper {

    @Select(value = "SELECT * FROM user_addr where user_id = #{userId} order by default_address desc")
    List<UserAddr> query(int userId);

    @Select(value = "SELECT * FROM user_addr where id = #{userAddrId}")
    UserAddr queryById(@Param("userAddrId") String userAddrId);

    @Insert(value = "insert into user_addr(id,user_id,name,phone,region,address,default_address,addr_type) values (#{id},#{userId},#{name}, #{phone}, #{region}, #{address}, #{isDefault}, #{addrType})")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("name") String name, @Param("phone") String phone, @Param("region") String region, @Param("address") String address, @Param("isDefault") boolean isDefault, @Param("addrType") int addrType);

    @Update(value = "update user_addr set name=#{name},phone=#{phone},address=#{address},region=#{region},default_address=#{isDefault},addr_type=#{addrType} where id = #{id}")
    void modify(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("region") String region, @Param("address") String address, @Param("isDefault") boolean isDefault, @Param("addrType") int addrType);

    @Update(value = "update user_addr set default_address=0 where user_id = #{userId} ")
    void modifyAsUnDefault(@Param("userId") int userId);

    @Delete("delete from user_addr where id = #{id}")
    void delete(@Param("id") String id);
}
