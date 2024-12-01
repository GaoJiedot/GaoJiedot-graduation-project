    package com.gj.controller;

    import com.gj.pojo.Chat;
    import com.gj.pojo.dto.ChatDto;
    import com.gj.pojo.responseMessage.ResponseMessage;
    import com.gj.service.iservice.IChatService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/chat")
    public class ChatController {
        @Autowired
        IChatService chatService;
        @PostMapping("/send")
        public ResponseMessage sendChat(@RequestBody ChatDto chat){
            Chat chatNew= chatService.sendChat(chat);
            return ResponseMessage.success(chatNew);
        }
        //获取聊天记录
        @GetMapping("/getChat")
        public String getChat(Integer senderId,Integer receiverId){
            return  chatService.getChat(senderId,receiverId);

        }
        @GetMapping("/getAllChat/{userId}")
        public ResponseMessage getChatList(@PathVariable Integer userId) {
            List<Chat> chatList = chatService.getChatListByUserId(userId);
            return ResponseMessage.success(chatList);
        }

    }
