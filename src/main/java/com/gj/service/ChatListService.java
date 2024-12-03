package com.gj.service;

import com.gj.pojo.ChatList;
import com.gj.pojo.dto.ChatListDto;
import com.gj.repository.ChatListRepository;
import com.gj.service.iservice.IChatListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatListService implements IChatListService {
    @Autowired
    ChatListRepository chatListRepository;


    @Override
    public List<ChatList> getChatList(Integer userId) {
        return chatListRepository.findByuserId(userId);
    }

    @Override
    public List<ChatList> addChatList(List<ChatListDto> chatListDtos) {
        List<ChatList> result = new ArrayList<>();

        for (ChatListDto dto : chatListDtos) {
            ChatList chatList = new ChatList();
            BeanUtils.copyProperties(dto, chatList);
            chatListRepository.save(chatList);
            result.add(chatList);
        }

        return result;
    }


}
