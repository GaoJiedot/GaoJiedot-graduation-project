package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "user_type")
    private Integer userType;


}
