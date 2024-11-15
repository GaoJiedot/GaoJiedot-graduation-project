package com.gj.controller;

import com.gj.pojo.Order;
import com.gj.pojo.dto.OrderDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
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
        Order orderNew = orderService.get(orderId);
        return  ResponseMessage.success(orderNew);
    }
    @GetMapping("/type/{orderType}")
    public ResponseMessage getType(@PathVariable("orderType") Integer orderType) {
        List<Order> orderNew = orderService.getType(orderType);
        return  ResponseMessage.success(orderNew);
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
