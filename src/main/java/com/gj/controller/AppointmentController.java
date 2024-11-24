package com.gj.controller;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDTO;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    @Autowired
     IAppointmentService appointmentService;

    @GetMapping("/time-slots")
    public List<String> getTimeSlots(@RequestParam String date) {
        // 假设时间段是固定的，可以直接返回静态数据
        return Arrays.asList(
                "9:00-10:00","10:00-11:00","11:00-12:00","14:00-15:00","15:00-16:00", "16:00-17:00",
                "17:00-18:00","18:00-19:00", "19:00-20:00","20:00-21:00", "21:00-22:00");
    }

    // 提交预约
    @PostMapping
    public ResponseMessage createAppointment(@RequestBody AppointmentDTO appointment) {
        Appointment appointmentNew=appointmentService.saveAppointment(appointment);
        return ResponseMessage.success(appointmentNew);
    }

}