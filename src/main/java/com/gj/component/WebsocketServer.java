package com.gj.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gj.pojo.Chat;
import com.gj.pojo.ChatList;
import com.gj.pojo.dto.ChatDto;
import com.gj.service.ChatService;
import com.gj.service.iservice.IChatListService;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{userId}")
public class WebsocketServer {
    private static final Map<String, Session> ONLINE_SESSIONS = new ConcurrentHashMap<>();

    // 使用静态变量存储服务实例
    private static ChatService chatService;
    private static IChatListService chatListService;

    // 提供静态setter方法进行注入
    @Autowired
    public void setChatService(ChatService chatService) {
        WebsocketServer.chatService = chatService;
    }

    @Autowired
    public void setChatListService(IChatListService chatListService) {
        WebsocketServer.chatListService = chatListService;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            if (chatListService == null) {
                throw new IllegalStateException("chatListService has not been initialized");
            }

            ONLINE_SESSIONS.put(userId, session);
            broadcastUserStatus(userId, "online");

            List<ChatList> chatList = chatListService.getChatList(Integer.parseInt(userId));
            session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                    "type", "init",
                    "status", "success",
                    "chatList", chatList
            )));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                        "type", "error",
                        "message", "Failed to initialize chat: " + e.getMessage()
                )));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") Integer userId) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode dataNode = jsonNode.get("data");

            if (dataNode == null) {
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                        "type", "error",
                        "message", "Message format error: missing 'data' field"
                )));
                return;
            }

            ChatDto chatDto = objectMapper.treeToValue(dataNode, ChatDto.class);
            chatDto.setSenderId(userId);

            Chat savedChat = chatService.sendChat(chatDto);

            // 更新发送者的聊天列表
            updateChatList(userId.toString());

            // 更新并通知接收者
            String receiverId = chatDto.getReceiverId().toString();
            updateChatList(receiverId);

            Session receiverSession = ONLINE_SESSIONS.get(receiverId);
            if (receiverSession != null && receiverSession.isOpen()) {
                receiverSession.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                        "type", "message",
                        "data", savedChat
                )));
            }

            // 发送成功响应给发送者
            session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                    "type", "messageStatus",
                    "status", "success",
                    "messageId", savedChat.getId()
            )));

        } catch (Exception e) {
            e.printStackTrace();
            try {
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(Map.of(
                        "type", "error",
                        "message", "Failed to process message: " + e.getMessage()
                )));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
