package cn.zysheep.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: RestProviderController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 三月三
 */
@RestController
public class RestProviderController {
    @GetMapping("/service")//暴露服务
    public String service(){
        System.out.println("provider invoke");
        return "provider invoke";
    }
}
