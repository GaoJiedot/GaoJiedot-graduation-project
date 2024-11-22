package com.gj.controller;

import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @GetMapping("/shop/{shopPhone}")
    public ResponseMessage getShopByPhone(@PathVariable String shopPhone) {
        List<Shop> shopNew = shopService.getShopByPhone(shopPhone);
        return ResponseMessage.success(shopNew);
    }

    @GetMapping("/admin/all")
    public ResponseMessage getAll() {
       List<Shop> shopNew = shopService.getAll();
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
    @PutMapping("/uploadShopLogo")
    public ResponseMessage uploadShopLogo(@PathVariable Integer shopId, @RequestParam("file") MultipartFile file) {
        // 动态获取项目根目录
        String uploadDir = System.getProperty("user.dir") + "/shoplogo/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // 创建目录
        }

        // 为文件生成唯一文件名
        String fileName = shopId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);
        try {
            file.transferTo(destination); // 保存文件
        } catch (IOException e) {
            return ResponseMessage.error("上传失败: " + e.getMessage());
        }

        String baseUrl = "http://localhost:8080"; // 替换为你的实际服务器地址
        String avatarPath = "/shoplogo/" + fileName;
        shopService.uploadShopLogo(shopId, avatarPath); // 更新数据库
        return ResponseMessage.uploadsuccess("上传成功", baseUrl + avatarPath);

    }
    @PutMapping("/uploadShopImages")
    public ResponseMessage uploadShopImages(@PathVariable Integer shopId, @RequestParam("file") MultipartFile file) {
        // 动态获取项目根目录
        String uploadDir = System.getProperty("user.dir") + "/ShopImages/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // 创建目录
        }

        // 为文件生成唯一文件名
        String fileName = shopId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);
        try {
            file.transferTo(destination); // 保存文件
        } catch (IOException e) {
            return ResponseMessage.error("上传失败: " + e.getMessage());
        }

        String baseUrl = "http://localhost:8080"; // 替换为你的实际服务器地址
        String avatarPath = "/ShopImages/" + fileName;
        shopService.uploadShopImages(shopId, avatarPath); // 更新数据库
        return ResponseMessage.uploadsuccess("上传成功", baseUrl + avatarPath);

    }

}

