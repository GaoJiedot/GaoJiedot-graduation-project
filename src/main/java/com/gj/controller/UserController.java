package com.gj.controller;

import com.gj.pojo.User;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.pojo.dto.UserDto;


import com.gj.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseMessage add(@RequestBody UserDto user) {
        User userNew = userService.add(user);
        return ResponseMessage.success(userNew);
    }

    @GetMapping("/{userId}")
    public ResponseMessage get(@PathVariable("userId") Integer userId) {
        User userNew = userService.getUser(userId);
        return ResponseMessage.success(userNew);
    }
    @PutMapping
    public ResponseMessage update(@RequestBody UserDto user) {
        User userNew = userService.update(user);
        return ResponseMessage.success(userNew);
    }
    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable Integer userId) {
         userService.delete(userId);
       return ResponseMessage.success();
    }
}
