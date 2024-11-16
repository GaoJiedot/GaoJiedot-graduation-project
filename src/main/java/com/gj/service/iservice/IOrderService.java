package com.gj.service.iservice;

import com.gj.pojo.Order;
import com.gj.pojo.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    Order add(OrderDto order);

    Order get(Integer orderId);

    Order update(OrderDto order);

    void delete(Integer orderId);



    List<Order> getStatus(Integer orderStatus);
}
