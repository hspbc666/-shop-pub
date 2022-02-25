package cn.lblbc.shop.bean;

import lombok.Data;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Data
public class Address {
    private String id;
    private int userId;
    private String name;
    private String phone;
    private String region;//地区
    private String address;
    private boolean defaultAddress;
    private int addrType;//地址类型：1：家庭，2：公司，3：其他
}
