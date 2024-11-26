package com.gj.pojo.dto;

import lombok.Data;

@Data
public class AppointmentDto {

    private Integer appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String userPhone;
    private Integer shopId;
}
