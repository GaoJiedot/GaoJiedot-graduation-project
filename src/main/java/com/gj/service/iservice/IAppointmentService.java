package com.gj.service.iservice;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDTO;

import java.util.List;

public interface IAppointmentService {
    Appointment saveAppointment(AppointmentDTO appointment);
    List<Appointment> getReservedTime(Integer shopId);
}