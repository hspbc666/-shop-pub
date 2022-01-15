package cn.hsp.shop.mapper;

import cn.hsp.shop.bean.UserAddr;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface UserAddrMapper {

    @Select(value = "SELECT * FROM user_addr where user_id = #{userId} order by is_default desc")
    List<UserAddr> query(int userId);

    @Insert(value = "insert into user_addr(id,user_id,name,phone,region,address,is_default) values (#{id},#{userId},#{name}, #{phone}, #{region}, #{address}, #{isDefault})")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("name") String name, @Param("phone") String phone, @Param("region") String region, @Param("address") String address, @Param("isDefault") boolean isDefault);

    @Update(value = "update user_addr set name=#{name},phone=#{phone},address=#{address},region=#{region},is_default=#{isDefault} where id = #{id}")
    void modify(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("region") String region,@Param("address") String address, @Param("isDefault") boolean isDefault);

    @Update(value = "update user_addr set is_default=0 where user_id = #{userId} ")
    void modifyAsUnDefault(@Param("userId") int userId);

    @Delete("delete from user_addr where id = #{id}")
    void delete(@Param("id") String id);
}