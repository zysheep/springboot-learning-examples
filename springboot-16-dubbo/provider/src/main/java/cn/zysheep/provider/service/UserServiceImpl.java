package cn.zysheep.provider.service;

import entity.User;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import service.UserService;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserServiceImpl
 * @Author: 三月三
 */
@Component
// 该service是org.apache.dubbo.config.annotation.Service
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(1);
        user.setAge(12);
        user.setName("三月三");
        return user;
    }
}
