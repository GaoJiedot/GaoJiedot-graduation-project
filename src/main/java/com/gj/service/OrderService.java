package com.gj.service;

import com.gj.pojo.Order;
import com.gj.pojo.dto.OrderDto;
import com.gj.repository.OrderRepository;
import com.gj.service.iservice.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order add(OrderDto order) {
        Order orderPojo = new Order();
        BeanUtils.copyProperties(order, orderPojo);
        return orderRepository.save(orderPojo);
    }

    @Override
    public Order get(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow( ()-> new IllegalArgumentException("订单不存在"));
    }

    @Override
    public Order update(OrderDto order) {
        Order orderPojo = new Order();
        BeanUtils.copyProperties(order, orderPojo);
        return orderRepository.save(orderPojo);
    }

    @Override
    public void delete(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
