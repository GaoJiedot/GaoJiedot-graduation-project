package com.gj.repository;

import com.gj.pojo.User;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserAccount(Long userAccount);

    User findByShopId(Integer shopId);

}
