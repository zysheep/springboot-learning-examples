package cn.zysheep.microservice.service1.service;

import cn.zysheep.microservice.api.Service2Api;
import cn.zysheep.microservice.service1.api.Service1Api;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Service1ApiImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 三月三
 */

@Service
public class Service1ApiImpl implements Service1Api {

    @Reference
    private Service2Api service2Api;

    public String dubboService1() {
        return "dubboService1 invoke"+service2Api.dubboService2();
    }
}
