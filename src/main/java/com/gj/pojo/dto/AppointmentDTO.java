package com.gj.pojo.dto;

import lombok.Data;

@Data
public class AppointmentDTO {
    private Integer id;
    private String date;
    private String timeSlot;
    private String userPhone;
    private String remark;
    private Integer shopId;
}