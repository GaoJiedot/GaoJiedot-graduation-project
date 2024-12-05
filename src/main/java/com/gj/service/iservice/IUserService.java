package com.gj.service.iservice;

import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

public interface IUserService  {
    User add(UserDto user);

    User getUser(Long userAccount);

    User update(UserDto user);

    void delete(Integer userId);


    User login(UserDto user);

    User updatePassword(UserDto user);

    User updateAvatar(Integer userId, String avatarPath);

    User get(Integer userId);


    User findById(Integer Id);


    User updateAdmin(Integer userId, UserDto user);

    User updateApply(Integer userId, UserDto user);

    User getUserId(Integer shopId);


    void deleteBatch(List<Integer> userId);


    Page<User> findAll(PageRequest pageRequest);
}
