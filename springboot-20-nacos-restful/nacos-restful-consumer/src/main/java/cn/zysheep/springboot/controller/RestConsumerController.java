package cn.zysheep.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: RestConsumerController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 三月三
 */
@RestController
public class RestConsumerController {

    //服务id即注册中心的中的服务名
    private String serviceId="nacos-restful-provider";

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping(value = "/service2")
    public String service2(){

        RestTemplate restTemplate = new RestTemplate();

        ServiceInstance instance = loadBalancerClient.choose(serviceId);
        URI uri = instance.getUri();
        System.out.println(uri);
        //调用服务
        String providerResult = restTemplate.getForObject(uri+ "/service",String.class);
        return "consumer invoke | " + providerResult+"port:"+uri.getPort();
    }
}
