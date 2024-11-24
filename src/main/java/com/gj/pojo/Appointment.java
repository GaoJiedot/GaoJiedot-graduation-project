package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    private String date;
    @Column(name = "time_slot")
    private String timeSlot;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "remark")
    private String remark;
    @Column(name = "shop_id")
    private Integer shopId;

}