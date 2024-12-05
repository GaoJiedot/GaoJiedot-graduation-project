package com.gj.controller;

import com.gj.pojo.Tabulate;
import com.gj.pojo.dto.TabulateDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.ITabulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tabulate")

public class TabulateController {
    @Autowired
    ITabulateService tabulateService;

    @PostMapping("/add")
    public ResponseMessage add(@RequestBody TabulateDto tabulate) {
        Tabulate tabulateNew = tabulateService.add(tabulate);
        return ResponseMessage.success(tabulateNew);
    }

    @GetMapping("/{tabulateId}")
    public ResponseMessage get(@PathVariable Integer tabulateId) {
        Tabulate tabulateNew = tabulateService.get(tabulateId);
        return ResponseMessage.success(tabulateNew);
    }

    @GetMapping("/type/{tabulateType}")
    public ResponseMessage getByType(
            @PathVariable Integer tabulateType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize) {
        Page<Tabulate> tabulatePage = tabulateService.getByTypeWithPagination(
                tabulateType,
                page - 1,
                pageSize
        );
        return ResponseMessage.success(tabulatePage);
    }


    @GetMapping("/search/{tabulateName}")
    public ResponseMessage getByTabulateName(@PathVariable String tabulateName) {
        List<Tabulate> tabulateNew = tabulateService.getByTabulateName(tabulateName);
        if (tabulateNew == null) {
            return ResponseMessage.error("没有找到店铺");
        }
        return ResponseMessage.success(tabulateNew);
    }

    @GetMapping("/getByShopId/{shopId}")
    public ResponseMessage getByShopId(@PathVariable Integer shopId) {
        List<Tabulate> tabulateNew = tabulateService.getByShopId(shopId);
        return ResponseMessage.success(tabulateNew);
    }

    @PutMapping("/update")
    public ResponseMessage update(@RequestBody TabulateDto tabulate) {
        Tabulate tabulateNew = tabulateService.update(tabulate);
        return ResponseMessage.success(tabulateNew);
    }

    @DeleteMapping("/{tabulateId}")
    public ResponseMessage delete(@PathVariable Integer tabulateId) {
        tabulateService.delete(tabulateId);
        return ResponseMessage.success("删除成功");
    }

    @PostMapping("/uploadTabulateImages/{tabulateId}")
    public ResponseMessage uploadTabulateImages(@PathVariable Integer tabulateId, @RequestParam("file") MultipartFile file) {
        // 动态获取项目根目录
        String uploadDir = System.getProperty("user.dir") + "/tabulateImages/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // 创建目录
        }

        // 为文件生成唯一文件名
        String fileName = tabulateId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);
        try {
            file.transferTo(destination); // 保存文件
        } catch (IOException e) {
            return ResponseMessage.error("上传失败: " + e.getMessage());
        }

        String baseUrl = "http://localhost:8080"; // 替换为你的实际服务器地址
        String avatarPath = "/tabulateImages/" + fileName;
        tabulateService.uploadTabulateImages(tabulateId, avatarPath); // 更新数据库
        return ResponseMessage.uploadsuccess("上传成功", baseUrl + avatarPath);

    }

}