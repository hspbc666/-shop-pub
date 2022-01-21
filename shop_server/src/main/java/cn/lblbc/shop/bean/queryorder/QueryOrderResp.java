package cn.lblbc.shop.bean.queryorder;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryOrderResp {
    private String orderId;
    private int status;
    private long createTime;
    private List<FullOrderInfo> list;
}