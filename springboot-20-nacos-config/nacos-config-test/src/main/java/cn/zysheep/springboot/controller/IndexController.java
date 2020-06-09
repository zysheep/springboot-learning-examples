package cn.zysheep.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: IndexController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 三月三
 */
@RestController
public class IndexController {

    @Value("${common.name}")
    private String common_name;

    // 注入配置文件上下文,实现配置的动态更新
    @Autowired
    private ConfigurableApplicationContext applicationContext;


    @GetMapping("/")
    public String main(){
        return common_name;
    }

    //实现配置的动态更新
    @GetMapping("/dynamicUpdate")
    public String main1(){
        return applicationContext.getEnvironment().getProperty("common.name");
    }
}
