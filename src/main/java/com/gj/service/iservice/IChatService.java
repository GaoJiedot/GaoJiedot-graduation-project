package com.gj.service.iservice;

import com.gj.pojo.Chat;
import com.gj.pojo.dto.ChatDto;

import java.util.List;

public interface IChatService {




    String getChat(Integer senderId, Integer receiverId);

    Chat sendChat(ChatDto chat);

    List<Chat> getChatListByUserId(Integer userId);
}
