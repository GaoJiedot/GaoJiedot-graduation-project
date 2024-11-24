package com.gj.service.iservice;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDTO;

public interface IAppointmentService {
    Appointment saveAppointment(AppointmentDTO appointment);
}
