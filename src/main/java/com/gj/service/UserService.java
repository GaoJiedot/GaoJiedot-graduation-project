package com.gj.service;

import com.gj.config.JwtTokenUtils;
import com.gj.pojo.User;
import com.gj.pojo.dto.UserDto;
import com.gj.repository.UserRepository;
import com.gj.service.iservice.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
           String token= JwtTokenUtils.genToken(existingUser.getUserId().toString(),existingUser.getPassword());
            existingUser.setToken(token);
            return existingUser;
//            String token=JwtTokenUtils.generateToken(existingUser.getUserAccount().toString());
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
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("用户未找到"));
        user.setUserAvatar(avatarPath);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }



    @Override
    public User get(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("用户未找到"));


    }

    @Override
    public User findById(Integer Id) {
        return userRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("用户未找到"));
    }

    @Override
    public User updateAdmin(Integer userId, UserDto user) {
        User userPojo = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));

        if (user.getUserName() != null) {
            userPojo.setUserName(user.getUserName());
        }
        if (user.getPassword() != null) {
            userPojo.setPassword(user.getPassword());
        }
        if (user.getUserType() != null) {
            userPojo.setUserType(user.getUserType());
        }
        return userRepository.save(userPojo);
    }


    @Override
    public User updateApply(Integer userId, UserDto user) {
        User userPojo = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("用户未找到"));

        userPojo.setUserType(user.getUserType());
        userPojo.setShopId(user.getShopId());

        return userRepository.save(userPojo);
    }

//    @Override
//    public void deleteBatch(List<Integer> userIds) {
//        if (userIds == null || userIds.isEmpty()) {
//            throw new IllegalArgumentException("用户ID列表不能为空");
//        }
//
//        // 确保不包含ID为1的用户
//        if (userIds.contains(1)) {
//            throw new IllegalArgumentException("不能删除管理员用户");
//        }
//
//        // 批量删除用户
//        userRepository.deleteBatchByIds(userIds);
//    }


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
