package com.gj.controller;

import com.gj.pojo.ChatList;
import com.gj.pojo.dto.ChatListDto;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.IChatListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/createChatList")
    public ResponseMessage addChatList(@RequestBody List<ChatListDto> chatList) {

        List<ChatList> chatListNew = chatListService.addChatList(chatList);

        return ResponseMessage.success(chatListNew);
    }
}
