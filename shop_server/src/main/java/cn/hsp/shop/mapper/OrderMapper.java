package cn.hsp.shop.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface UserOrderMapper {

    @Insert(value = "insert into user_order(id,user_id,order_id) values (#{id},#{userId}, #{orderId})")
    void add(@Param("id") String id, @Param("userId") int userId, @Param("orderId") String orderId);

}
