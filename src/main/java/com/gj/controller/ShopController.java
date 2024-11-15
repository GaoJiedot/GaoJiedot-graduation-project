package com.gj.controller;

import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;
import com.gj.service.iservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@CrossOrigin(origins = "*")
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

    @DeleteMapping("/{shopId}")
    public ResponseMessage delete(@PathVariable Integer shopId) {
        shopService.delete(shopId);
       return ResponseMessage.success();
    }

}

