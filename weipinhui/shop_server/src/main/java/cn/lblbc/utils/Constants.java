package cn.lblbc.utils;

/**
 * 厦门大学计算机专业 | 前华为工程师
 * 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
 * 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
 * 公众号：蓝不蓝编程
 */
public class Constants {
    public interface OrderStatus {
        int ORDER_ALL = 0;//所有状态，用于查询
        int TO_PAY = 1;//待付款
        int TO_DELIVER = 2;//待发货
        int TO_RECEIVE = 3;//待收货
        int TO_COMMENT = 4;//待评价
        int TO_RETURN = 5;//退货中
        int CLOSED = 6;//关闭
    }
}
