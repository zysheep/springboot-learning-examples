package cn.zysheep.springboot.service.impl;

import cn.zysheep.springboot.entity.User;
import cn.zysheep.springboot.mapper.UserMapper;
import cn.zysheep.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserServiceImpl
 * @Author: 三月三
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(String username) {
        return userMapper.findUserById(username);
    }
}