package cn.lblbc.shop.bean.queryorder;

import cn.lblbc.shop.bean.UserAddr;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
@Data
@Builder
public class QueryOrderResp {
    private String orderId;
    private int status;
    private long createTime;
    private List<FullOrderInfo> list;
    private UserAddr userAddr;
}