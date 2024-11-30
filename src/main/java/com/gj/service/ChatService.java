package com.gj.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.pojo.Chat;
import com.gj.pojo.dto.ChatDto;
import com.gj.repository.ChatRepository;
import com.gj.service.iservice.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ChatService implements IChatService {
    @Autowired
    private ChatRepository chatRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String getChat(Integer senderId, Integer receiverId) {
        try {
            List<Chat> chatHistory = chatRepository.findChatHistory(senderId, receiverId);
            return objectMapper.writeValueAsString(chatHistory);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }
    }

    @Override
    public Chat sendChat(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setSendTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        chat.setReadStatus(0);

        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getChatListByUserId(Integer userId) {

        return chatRepository.getChatListByUserId(userId);
    }
}