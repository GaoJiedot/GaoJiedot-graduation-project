package com.gj.controller;

import com.gj.pojo.Order;
import com.gj.pojo.dto.OrderDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping
    public ResponseMessage add(@RequestBody OrderDto order) {
        Order orderNew = orderService.add(order);
        return  ResponseMessage.success(orderNew);
    }
    @GetMapping("/{orderId}")
    public ResponseMessage get(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.get(orderId);
        return  ResponseMessage.success(order);
    }
    @PutMapping
    public ResponseMessage update(@RequestBody OrderDto order) {
        Order orderNew = orderService.update(order);
        return  ResponseMessage.success(orderNew);
    }
    @DeleteMapping("/{orderId}")
    public ResponseMessage delete(@PathVariable("orderId") Integer orderId) {
        orderService.delete(orderId);
        return ResponseMessage.success();
    }
}
