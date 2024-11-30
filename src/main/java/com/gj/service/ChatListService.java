package com.gj.service;

import com.gj.pojo.ChatList;
import com.gj.repository.ChatListRepository;
import com.gj.service.iservice.IChatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatListService implements IChatListService {
    @Autowired
    ChatListRepository chatListRepository;


    @Override
    public List<ChatList> getChatList(Integer userId) {
        return chatListRepository.findByuserId(userId);
    }
}
