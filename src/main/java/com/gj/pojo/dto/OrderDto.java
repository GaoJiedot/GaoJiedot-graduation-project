package com.gj.pojo.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Integer orderId;
    private String orderName;
    private Integer orderPrice;
    private Integer orderStatus;
    private String orderTabs;
    private Integer orderType;
}
