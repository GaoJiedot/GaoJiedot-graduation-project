package com.gj.controller;

import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.VerificationCodeService;
import com.gj.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sendcode")
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

    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable Integer userId) {
        userService.delete(userId);
        return ResponseMessage.success("删除成功");
    }
}
