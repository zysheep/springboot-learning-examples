package cn.zysheep.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: TestController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 三月三
 */

@RestController
public class TestController {


    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/configs")
    public String getValue(){
        String name = applicationContext.getEnvironment().getProperty("common.name");
        String address = applicationContext.getEnvironment().getProperty("common.addr");

        return name+":"+address;
    }
}
