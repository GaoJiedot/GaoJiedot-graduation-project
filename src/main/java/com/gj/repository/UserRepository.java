package com.gj.repository;

import com.gj.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {


    User findByUserAccount(Long userAccount);
}
