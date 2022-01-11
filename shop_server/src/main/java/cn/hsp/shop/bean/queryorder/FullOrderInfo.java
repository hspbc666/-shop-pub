package cn.hsp.shop.bean.queryorder;

import lombok.Data;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：花生皮编程
 */
@Data
public class FullOrderInfo {
    private String orderId;
    private int status;
    private String goodsId;
    private String name;
    private long price;
    private String squarePic;
    private int quantity;
}