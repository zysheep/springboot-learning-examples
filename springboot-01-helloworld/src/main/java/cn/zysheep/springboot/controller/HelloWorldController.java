package cn.zysheep.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: HelloWorldController
 * @Author: 三月三
 */
@RestController
public class HelloWorldController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello Spring Boot";
    }
}
