package com.gj.pojo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChatDto {

    private Integer id;

    private Integer senderId;
    private String senderName;
    private Integer receiverId;
    private String receiverName;
    private String content;
    private String sendTime;
    private Integer readStatus;
    private String type;
    private Integer userId;
    private String senderAvatar;
    private String receiverAvatar;


}
