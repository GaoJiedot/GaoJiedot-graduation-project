package com.gj.service;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDto;
import com.gj.repository.AppointmentRepository;
import com.gj.service.iservice.IAppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.hutool.core.date.DateUtil.today;

@Service
public class AppointmentService implements IAppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(AppointmentDto appointment) {
        Appointment appointmentPojo = new Appointment();
        BeanUtils.copyProperties(appointment, appointmentPojo);
        return appointmentRepository.save(appointmentPojo);
    }

    @Override
    public List<Appointment> getReservedTime(Integer shopId) {
        String currentDate = today();
        return appointmentRepository.getReservedTime(shopId, currentDate);
    }
}