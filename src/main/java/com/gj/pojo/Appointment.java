package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name = "tb_appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer appointmentId;
    @Column(name = "appointment_date")
    private String appointmentDate;
    @Column(name = "appointment_time")
    private String appointmentTime;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "shop_id")
    private Integer shopId;

}