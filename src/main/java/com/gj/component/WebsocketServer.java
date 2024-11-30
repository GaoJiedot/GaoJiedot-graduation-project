package com.gj.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.pojo.Chat;
import com.gj.pojo.ChatList;
import com.gj.pojo.dto.ChatDto;
import com.gj.service.ChatService;
import com.gj.service.iservice.IChatListService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{userId}")
public class WebsocketServer {
    private static final Map<String, Session> ONLINE_SESSIONS = new ConcurrentHashMap<>();
    private static ChatService chatService;
    private static IChatListService chatListService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 注入所需的服务
    public static void setChatService(ChatService service) {
        WebsocketServer.chatService = service;
    }

    public static void setChatListService(IChatListService service) {
        WebsocketServer.chatListService = service;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            ONLINE_SESSIONS.put(userId, session);
            broadcastUserStatus(userId, "online");

            // 获取并发送初始聊天列表
            List<ChatList> chatList = chatListService.getChatList(Integer.parseInt(userId));  // 修改这里
            session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                    "type", "init",
                    "status", "success",
                    "chatList", chatList
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") Integer userId) {
        try {
            // 将消息转换为ChatDto对象
            ChatDto chatDto = objectMapper.readValue(message, ChatDto.class);
            chatDto.setSenderId(userId);

            // 保存消息到数据库
            Chat savedChat = chatService.sendChat(chatDto);

            // 更新发送者和接收者的聊天列表
            updateChatList(userId.toString());
            updateChatList(chatDto.getReceiverId().toString());

            // 发送消息给接收者
            Session receiverSession = ONLINE_SESSIONS.get(chatDto.getReceiverId().toString());
            if (receiverSession != null && receiverSession.isOpen()) {
                // 发送新消息
                receiverSession.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                        "type", "message",
                        "data", savedChat
                )));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateChatList(String userId) {
        try {
            Session userSession = ONLINE_SESSIONS.get(userId);
            if (userSession != null && userSession.isOpen()) {
                // 获取最新的聊天列表
                List<ChatList> chatList = chatListService.getChatList(Integer.parseInt(userId));

                // 发送更新后的聊天列表
                userSession.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                        "type", "chatListUpdate",
                        "data", chatList
                )));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        ONLINE_SESSIONS.remove(userId);
        broadcastUserStatus(userId, "offline");
    }

    @OnError
    public void onError(Session session, @PathParam("userId") String userId, Throwable error) {
        error.printStackTrace();
        try {
            session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                    "type", "error",
                    "message", "Connection error occurred"
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastUserStatus(String userId, String status) {
        try {
            String statusMessage = objectMapper.writeValueAsString(Map.of(
                    "type", "status",
                    "userId", userId,
                    "status", status
            ));

            for (Session session : ONLINE_SESSIONS.values()) {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(statusMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}