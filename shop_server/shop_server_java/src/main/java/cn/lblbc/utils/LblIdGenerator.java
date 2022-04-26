package cn.lblbc.utils;

import java.util.UUID;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》  https://lblbc.cn/blog
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */

public class LblIdGenerator {
    public static String generateId() {
        String uuid = "lbl"+UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
