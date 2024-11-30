package com.gj.controller;

import com.gj.pojo.ChatList;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IChatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatList")
public class ChatListController {
    @Autowired
    IChatListService chatListService;
    @GetMapping("/getChatList/{userId}")
    public ResponseMessage getChatList(@PathVariable Integer userId){
       List<ChatList> chatLists= chatListService.getChatList(userId);
        return ResponseMessage.success(chatLists);
    }
}
