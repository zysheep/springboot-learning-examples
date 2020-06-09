package cn.zysheep.springboot.controller;

import cn.zysheep.microservice.service1.api.Service1Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: DubboConsumerController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 三月三
 */
@RestController
public class DubboConsumerService1Controller {

    @Reference
    private Service1Api service1Api;

    @GetMapping("/service1")
    public String service1(){
        return  "consumer dubbo invoke | "+service1Api.dubboService1();
    }
}
