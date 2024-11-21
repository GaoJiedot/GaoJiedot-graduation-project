package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_account")
    private Long userAccount;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_type" ,columnDefinition= "int default 2")
    private Integer userType;
    @Column(name = "email")
    private String email;
    @Column(name = "user_avatar")
    private String userAvatar;
    private  transient String token;
    @JoinColumn(name = "shop_id")
    private Integer shopId;

}
