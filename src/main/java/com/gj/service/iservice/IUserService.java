package com.gj.service.iservice;

import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;

public interface IUserService  {
    User add(UserDto user);


    User getUser(String userName);

    User update(UserDto user);

    void delete(Integer userId);

}
