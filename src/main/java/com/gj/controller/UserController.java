package com.gj.controller;

import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.VerificationCodeService;
import com.gj.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    VerificationCodeService verificationCodeService;

    @PostMapping
    public ResponseMessage add(@RequestBody UserDto user) {
        User userNew = userService.add(user);
        return ResponseMessage.success(userNew);
    }

    @PostMapping("/login")
    public ResponseMessage login(@RequestBody UserDto user) {
        User userNew = userService.login(user);
        if (userNew != null) {
            return ResponseMessage.success(userNew);
        }
        return ResponseMessage.error("用户名或密码错误");
    }

    @GetMapping("/sendCode")
    public ResponseMessage sendCode(@RequestParam String email) {
        verificationCodeService.sendCode(email);
        return ResponseMessage.success("验证码已发送");
    }

    @PostMapping("/verifycode")
    public ResponseMessage verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean isValid = verificationCodeService.verifyCode(email, code);
        if (isValid) {
            return ResponseMessage.success("验证成功");
        } else {
            return ResponseMessage.error("验证码无效或已过期");
        }
    }

    @GetMapping("userId/{userId}")
    public ResponseMessage get(@PathVariable Integer userId) {
        User userNew = userService.get(userId);
        if (userNew != null) {
            return ResponseMessage.success(userNew);
        } else {
            return ResponseMessage.error("用户不存在");
        }
    }

    @GetMapping("/userAccount/{userAccount}")
    public ResponseMessage getUser(@PathVariable Long userAccount) {
        User userNew = userService.getUser(userAccount);
        if (userNew != null) {
            return ResponseMessage.success(userNew);
        } else {
            return ResponseMessage.error("用户不存在");
        }
    }

    @PutMapping
    public ResponseMessage update(@RequestBody UserDto user) {
        User userNew = userService.update(user);
        return ResponseMessage.success(userNew);
    }

    @PutMapping("/updatePassword")
    public ResponseMessage updatePassword(@RequestBody UserDto user) {
        User userNew = userService.updatePassword(user);
        if (userNew != null) {
            return ResponseMessage.success(userNew);
        }
        return ResponseMessage.error("");
    }

    @PostMapping("/uploadAvatar/{userId}")
    public ResponseMessage uploadAvatar(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) {
        // 动态获取项目根目录
        String uploadDir = System.getProperty("user.dir") + "/avatar/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs(); // 创建目录
        }

        // 为文件生成唯一文件名
        String fileName = userId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        File destination = new File(uploadDir + fileName);
        try {
            file.transferTo(destination); // 保存文件
        } catch (IOException e) {
            return ResponseMessage.error("上传失败: " + e.getMessage());
        }

        String baseUrl = "http://localhost:8080"; // 替换为你的实际服务器地址
        String avatarPath = "/avatar/" + fileName;
        userService.updateAvatar(userId, avatarPath); // 更新数据库
        return ResponseMessage.uploadsuccess("上传成功", baseUrl + avatarPath);

    }


    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable Integer userId) {
        userService.delete(userId);
        return ResponseMessage.success("删除成功");
    }

    @GetMapping("/getFriendId/{shopId}")
    public ResponseMessage getUserId(@PathVariable Integer shopId) {
        User userNew = userService.getUserId(shopId);
        return ResponseMessage.success(userNew);
    }

    @GetMapping("/admin")
    public ResponseMessage findall(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize) {
        // 创建分页对象 注意这里不需要减1，因为 PageRequest.of 参数从0开始
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);

        // 获取分页数据
        Page<User> userPage = userService.findAll(pageRequest);

        // 构建返回的数据结构
        Map<String, Object> result = new HashMap<>();
        result.put("records", userPage.getContent());
        result.put("current", page);
        result.put("pages", userPage.getTotalPages());
        result.put("total", userPage.getTotalElements());

        return ResponseMessage.success(result);
    }

    @PatchMapping("/update/{userId}")
    public ResponseMessage updateAdmin(@PathVariable Integer userId, @RequestBody UserDto user) {
        User userNew = userService.updateAdmin(userId, user);
        return ResponseMessage.success(userNew);
    }

    @PatchMapping("/updateApply/{userId}")
    public ResponseMessage updateApply(@PathVariable Integer userId, @RequestBody UserDto user) {
        User userNew = userService.updateApply(userId, user);
        return ResponseMessage.success(userNew);
    }

    @DeleteMapping("/batch")
    public ResponseMessage deleteBatch(@RequestBody List<Integer> userId) {
        userService.deleteBatch(userId);
        return ResponseMessage.success("批量删除成功");

    }
}
