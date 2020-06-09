package cn.zysheep.springboot.controller;

import cn.zysheep.microservice.api.Service2Api;
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
public class DubboConsumerService2Controller {

    @Reference
    private Service2Api service2Api;

    @GetMapping("/service2")
    public String service2(){
        return  "consumer dubbo invoke | "+service2Api.dubboService2();
    }
}
