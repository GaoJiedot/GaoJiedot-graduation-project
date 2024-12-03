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

public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping
    public ResponseMessage add(@RequestBody OrderDto order) {
        Order orderNew = orderService.add(order);
        return ResponseMessage.success(orderNew);
    }

    @GetMapping("/{orderId}")
    public ResponseMessage get(@PathVariable Integer orderId) {
        Order orderNew = orderService.get(orderId);
        return ResponseMessage.success(orderNew);
    }

    @GetMapping("/status/{orderStatus}")
    public ResponseMessage getorderStatus(@PathVariable Integer orderStatus) {
        List<Order> orderNew = orderService.getStatus(orderStatus);
        return ResponseMessage.success(orderNew);
    }

    @PutMapping
    public ResponseMessage update(@RequestBody OrderDto order) {
        Order orderNew = orderService.update(order);
        return ResponseMessage.success(orderNew);
    }

    @DeleteMapping("/{orderId}")
    public ResponseMessage delete(@PathVariable Integer orderId) {
        orderService.delete(orderId);
        return ResponseMessage.success("删除成功");
    }

    @PatchMapping("/finishOrder/{orderId}")
    public ResponseMessage finishOrder(@PathVariable Integer orderId ,@RequestBody OrderDto order) {
        Order orderNew = orderService.finishOrder(orderId,order);
        return ResponseMessage.success(orderNew);
    }
    @PatchMapping("/rating/{orderId}")
    public ResponseMessage ratingOrder(@PathVariable Integer orderId ,@RequestBody OrderDto order) {
        Order orderNew = orderService.updaterating(orderId,order);
        return ResponseMessage.success(orderNew);
    }
    @GetMapping("/getRating/{shopId}")
    public ResponseMessage updateShopRating(@PathVariable Integer shopId) {
        List<Order> orderNew = orderService.getShopRating(shopId);
        return ResponseMessage.success(orderNew);


    }
}
