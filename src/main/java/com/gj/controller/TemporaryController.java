package com.gj.controller;

import com.gj.pojo.Temporary;
import com.gj.pojo.dto.TemporaryDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.ITemporaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temporary")
public class TemporaryController {
    @Autowired
    ITemporaryService temporaryService;
    @PostMapping
    public ResponseMessage add(@RequestBody TemporaryDto temporary){
        Temporary temporaryNew= temporaryService.add(temporary);
        return ResponseMessage.success(temporaryNew);
    }
    @GetMapping("/admin")
    public ResponseMessage getTemporaryAdmin(){
        List<Temporary> temporaryNew = temporaryService.getAll();
        return ResponseMessage.success(temporaryNew);
    }
    @DeleteMapping("/admin/{shopId}")
    public ResponseMessage deleteTemporaryAdmin(@PathVariable("shopId") Integer shopId){
      temporaryService.delete(shopId);
      return ResponseMessage.success("删除成功");
    }
}
