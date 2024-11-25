package com.gj.repository;

import com.gj.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserAccount(Long userAccount);

    List<User> findByApplyStatus(Integer applyStatus);


//
//    void deleteBatchByIds(List<Integer> userIds);
}
