package com.gj.repository;

import com.gj.pojo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query(value = "SELECT * FROM tb_chat WHERE (sender_id =?1 AND receiver_id =?2) OR (sender_id =?2 AND receiver_id =?1) ORDER BY send_time", nativeQuery = true)

    List<Chat> findChatHistory(Integer senderId, Integer receiverId);

    List<Chat> getChatListByUserId(Integer userId);
}
