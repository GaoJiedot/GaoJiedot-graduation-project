package com.gj.service;

import com.gj.pojo.Appointment;
import com.gj.pojo.dto.AppointmentDTO;
import com.gj.repository.AppointmentRepository;
import com.gj.service.iservice.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
  @Autowired
  AppointmentRepository appointmentRepository;


  @Override
  public Appointment saveAppointment(AppointmentDTO appointment) {
    Appointment appointmentPojo = new Appointment();
    BeanUtils.copyProperties(appointment, appointmentPojo);
    return appointmentRepository.save(appointmentPojo);
  }
}