package com.gj.service;

import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;
import com.gj.repository.UserRepository;
import com.gj.service.iservice.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        return userRepository.save(userPojo);
    }

    public User login(UserDto user) {
        User existingUser = userRepository.findByUserAccount(user.getUserAccount());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        }
        return null;
    }

    @Override
    public User updatePassword(UserDto user) {
        User existingUser = userRepository.findByUserAccount(user.getUserAccount());
        if (existingUser != null) {
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }
            return null;
    }

    @Override
    public User updateAvatar(Integer userId, String avatarPath) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户未找到"));
        user.setUserAvatar(avatarPath);
       return userRepository.save(user);
    }

    @Override
    public User getUser(Long userAccount) {
        return userRepository.findByUserAccount(userAccount);
    }


    @Override
    public User update(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user, userPojo);
        return userRepository.save(userPojo);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);

    }


}
