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
    @Column(name = "sender_id")
    private Integer senderId;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "content")
    private String content;
    @Column(name = "send_time")
    private String sendTime;
    @Column(name = "read_status")
    private Integer readStatus;
    @Column(name = "type")
    private String type;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "sender_avatar")
    private String senderAvatar;
    @Column(name = "receiver_avatar")
    private String receiverAvatar;
}
