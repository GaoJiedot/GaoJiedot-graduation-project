package com.gj.repository;

import com.gj.pojo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {



    List<Chat> getChatListByUserId(Integer userId);

    @Query(value = "SELECT * FROM `tb_chat` WHERE (user_id =?1 AND friend_id =?2) OR (user_id =?2 AND friend_id =?1)", nativeQuery = true)
    List<Chat> findByUserIdAndFriendId(Integer userId, Integer friendId);

}
