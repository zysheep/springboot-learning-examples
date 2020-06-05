package cn.zysheep.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: HelloController
 * @Author: 三月三
 */
@RestController
public class DockerController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/")
    public String index(){
        return String.format("hello docker: %s",port);
    }

}