package com.gj.repository;

import com.gj.pojo.ChatList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatListRepository  extends CrudRepository<ChatList, Integer> {


    List<ChatList> findByuserId(Integer userId);
}
