package com.gj.pojo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChatDto {

    private Integer id;
    private Integer userId;
    private String userName;
    private String userAvatar;
    private Integer friendId;
    private String friendName;
    private String friendAvatar;
    private Integer senderId;
    private String content;
    private String sendTime;
    private Integer readStatus;
    private String type;
    private String fileName;



}
