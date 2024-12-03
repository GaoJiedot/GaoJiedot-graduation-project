package com.gj.pojo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ChatListDto {

    private Integer userId;
    private String userName;
    private String userAvatar;
    private Integer friendId;
    private String friendName;
    private String friendAvatar;
}
