package com.gj.controller;

import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shop")

public class ShopController {
    @Autowired
    IShopService shopService;

    @PostMapping
    public ResponseMessage add(@RequestBody ShopDto shop) {
        Shop shopNew = shopService.add(shop);
        return ResponseMessage.success(shopNew);
    }

    @GetMapping("/{shopId}")
    public ResponseMessage get(@PathVariable Integer shopId) {
        Shop shopNew = shopService.get(shopId);
        return ResponseMessage.success(shopNew);
    }

    @PutMapping
    public ResponseMessage update(@RequestBody ShopDto shop) {
        Shop shopNew = shopService.update(shop);
        return ResponseMessage.success(shopNew);
    }

    @PatchMapping("/status")
    public ResponseMessage updateShopStatus(@RequestBody Map<String, Object> params) {
        Integer shopId = (Integer) params.get("shopId");
        Integer shopStatus = (Integer) params.get("shopStatus");
        shopService.updateShopStatus(shopId, shopStatus);
        if (shopStatus == 1) {
            return ResponseMessage.success("今天辛苦了");
        } else {
            return ResponseMessage.success("上班成功");
        }
    }

    @DeleteMapping("/{shopId}")
    public ResponseMessage delete(@PathVariable Integer shopId) {
        shopService.delete(shopId);
        return ResponseMessage.success("删除成功");
    }

}

