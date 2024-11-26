package com.gj.service.iservice;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDto;

import java.util.List;

public interface IAppointmentService {
    Appointment saveAppointment(AppointmentDto appointment);
    List<Appointment> getReservedTime(Integer shopId);
}