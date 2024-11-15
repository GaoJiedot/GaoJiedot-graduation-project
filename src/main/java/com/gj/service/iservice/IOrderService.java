package com.gj.service.iservice;

import com.gj.pojo.Order;
import com.gj.pojo.dto.OrderDto;

public interface IOrderService {
    Order add(OrderDto order);

    Order get(Integer orderId);

    Order update(OrderDto order);

    void delete(Integer orderId);
}
