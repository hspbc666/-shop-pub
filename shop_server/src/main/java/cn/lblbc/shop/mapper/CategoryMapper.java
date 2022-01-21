package cn.lblbc.shop.mapper;

import cn.lblbc.shop.bean.CategoryInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 花生皮编程(CSDN 、 简书 、 掘金 、 今日头条 、 微信公众号 、 抖音 、 快手 、 B站 、 西瓜视频)
 * 编程学习资料及开源项目见：https://juejin.cn/post/7002792005688360968
 */
@Repository
public interface CategoryMapper {

    @Select(value = "SELECT * FROM category")
    List<CategoryInfo> query();

}
