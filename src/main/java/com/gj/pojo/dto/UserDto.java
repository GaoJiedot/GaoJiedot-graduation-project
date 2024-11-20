package com.gj.pojo.dto;


import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private Long userAccount;
    private String password;
    private String userName;
    private String email;
    private String userAvatar;
}
