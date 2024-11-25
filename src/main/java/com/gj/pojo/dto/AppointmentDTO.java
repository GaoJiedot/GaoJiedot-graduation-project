package com.gj.pojo.dto;

import lombok.Data;

@Data
public class AppointmentDTO {

    private Integer appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String userPhone;
    private Integer shopId;
}
