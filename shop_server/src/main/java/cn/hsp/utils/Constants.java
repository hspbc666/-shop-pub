package cn.hsp.utils;

public class Constants {
    public interface OrderStatus {
        int CLOSED = 0;//关闭
        int TO_PAY = 1;//待付款
        int TO_DELIVER = 2;//待发货
        int TO_RECEIVE = 3;//待收货
        int TO_COMMENT = 4;//待评价
        int TO_RETURN = 5;//退货中
    }
}
