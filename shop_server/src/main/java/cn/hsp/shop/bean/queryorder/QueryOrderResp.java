package cn.hsp.shop.bean.queryorder;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryOrderResp {
    private String orderId;
    private List<FullOrderInfo> list;
}