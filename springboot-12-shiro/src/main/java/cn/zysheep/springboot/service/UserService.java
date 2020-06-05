package cn.zysheep.springboot.service;

import cn.zysheep.springboot.entity.User;

public interface UserService {
    User findUserById(String  username);
}
