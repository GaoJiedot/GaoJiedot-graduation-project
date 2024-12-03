    package com.gj.controller;

    import com.gj.pojo.Chat;
    import com.gj.pojo.dto.ChatDto;
    import com.gj.pojo.responseMessage.ResponseMessage;
    import com.gj.service.iservice.IChatService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.File;
    import java.io.IOException;
    import java.util.List;
    import java.util.UUID;

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

        @GetMapping("/getAllChat/{userId}")
        public ResponseMessage getChatList(@PathVariable Integer userId) {
            List<Chat> chatList = chatService.getChatListByUserId(userId);
            return ResponseMessage.success(chatList);
        }
        @GetMapping("/getHistoryChat/{userId}/{friendId}")
        public ResponseMessage getHistoryChat(@PathVariable Integer userId,@PathVariable Integer friendId) {
            List<Chat> chatList = chatService.getHistoryChat(userId,friendId);
            return ResponseMessage.success(chatList);
        }
        @PostMapping("/upload/{userId}")
        public ResponseMessage uploadShopLogo(@PathVariable Integer userId, @RequestParam("file") MultipartFile file) {
            // 动态获取项目根目录
            String uploadDir = System.getProperty("user.dir") + "/chatImage/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // 创建目录
            }

            // 为文件生成唯一文件名
            String fileName = userId + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            try {
                file.transferTo(destination); // 保存文件
            } catch (IOException e) {
                return ResponseMessage.error("上传失败: " + e.getMessage());
            }

            String baseUrl = "http://localhost:8080"; // 替换为你的实际服务器地址
            String avatarPath = "/chatImage/" + fileName;
            chatService.uploadShopLogo(userId, avatarPath); // 更新数据库
            return ResponseMessage.uploadsuccess("上传成功", baseUrl + avatarPath);

        }

    }
