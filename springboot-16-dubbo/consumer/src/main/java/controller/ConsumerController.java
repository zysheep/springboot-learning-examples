package controller;

import entity.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: ConsumerController
 * @Author: 三月三
 */
@RestController
@RequestMapping("/user")
public class ConsumerController {

    @Reference
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
}
