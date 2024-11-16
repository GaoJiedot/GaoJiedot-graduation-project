package com.gj.controller;

import com.gj.pojo.Tabulate;
import com.gj.pojo.dto.TabulateDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.ITabulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabulate")
@CrossOrigin(origins = "*")
public class TabulateController {
    @Autowired
    ITabulateService tabulateService;

    @PostMapping
    public ResponseMessage add(@RequestBody TabulateDto tabulate) {
        Tabulate tabulateNew = tabulateService.add(tabulate);
        return  ResponseMessage.success(tabulateNew);
    }
    @GetMapping("/{tabulateId}")
    public ResponseMessage get(@PathVariable Integer tabulateId) {
        Tabulate tabulateNew = tabulateService.get(tabulateId);
        return  ResponseMessage.success(tabulateNew);
    }
    @GetMapping("/type/{tabulateType}")
    public ResponseMessage getByType(@PathVariable Integer tabulateType) {
        List<Tabulate> tabulateNew = tabulateService.getByType(tabulateType);
        return  ResponseMessage.success(tabulateNew);
    }
    @PutMapping
    public ResponseMessage update(@RequestBody TabulateDto tabulate) {
        Tabulate tabulateNew = tabulateService.update(tabulate);
        return  ResponseMessage.success(tabulateNew);
    }
    @DeleteMapping("/{tabulateId}")
    public ResponseMessage delete(@PathVariable Integer tabulateId) {
        tabulateService.delete(tabulateId);
        return ResponseMessage.success();
    }
}