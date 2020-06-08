package service;

import entity.User;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: UserService
 * @Author: 三月三
 */
public interface UserService {
    User getUserById(Integer id);
}
