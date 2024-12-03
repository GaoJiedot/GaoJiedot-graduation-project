package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "tb_chat")
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_avatar")
    private String userAvatar;
    @Column(name = "friend_id")
    private Integer friendId;
    @Column(name = "friend_name")
    private String friendName;
    @Column(name = "friend_avatar")
    private String friendAvatar;
    @Column(name = "sender_id")
    private Integer senderId;
    @Column(name = "content")
    private String content;
    @Column(name = "send_time")
    private String sendTime;
    @Column(name = "read_status")
    private Integer readStatus;
    @Column(name = "type")
    private String type;


}
