package com.gj.service;

import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;
import com.gj.repository.UserRepository;
import com.gj.service.iservice.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User add(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user,userPojo);
        return userRepository.save(userPojo);
    }

    @Override
    public User getUser(Integer userId) {
       return userRepository.findById(userId).orElseThrow( ()-> new IllegalArgumentException("用户不存在"));
    }

    @Override
    public User update(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user,userPojo);
        return userRepository.save(userPojo);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);

    }
}
