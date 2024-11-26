package com.gj.controller;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    IAppointmentService appointmentService;

    @PostMapping
    public ResponseMessage createAppointment(@RequestBody AppointmentDto appointment) {
        Appointment appointmentNew = appointmentService.saveAppointment(appointment);
        return ResponseMessage.success(appointmentNew);
    }

    @GetMapping("/reserved/{shopId}")
    public ResponseMessage getReservedTimeS(@PathVariable Integer shopId) {
        try {
            List<Appointment> appointments = appointmentService.getReservedTime(shopId);
            return ResponseMessage.success(appointments);
        } catch (Exception e) {
            return ResponseMessage.error("获取预约时间失败: " + e.getMessage());
        }
    }
}