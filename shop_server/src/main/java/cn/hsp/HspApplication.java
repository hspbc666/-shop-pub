package cn.hsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@MapperScan({"cn.hsp.login.mapper", "cn.hsp.note.mapper", "cn.hsp.shop.mapper", "cn.hsp.blog.mapper"}) //扫描的mapper
@SpringBootApplication
public class HspApplication {

    public static void main(String[] args) {
        SpringApplication.run(HspApplication.class, args);
    }
}
