package com.gj.service.iservice;

import com.gj.pojo.ChatList;

import java.util.List;

public interface IChatListService {
    List<ChatList> getChatList(Integer userId);
}
