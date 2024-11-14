package com.gj.service.iservice;

import com.gj.pojo.User;
import com.gj.pojo.dto.ResponseMessage;
import com.gj.pojo.dto.UserDto;

public interface IUserService  {
    User add(UserDto user);
}
