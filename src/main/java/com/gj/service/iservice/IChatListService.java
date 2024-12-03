package com.gj.service.iservice;

import com.gj.pojo.ChatList;
import com.gj.pojo.dto.ChatListDto;

import java.util.List;

public interface IChatListService {
    List<ChatList> getChatList(Integer userId);

    List<ChatList> addChatList(List<ChatListDto> chatList);
}
