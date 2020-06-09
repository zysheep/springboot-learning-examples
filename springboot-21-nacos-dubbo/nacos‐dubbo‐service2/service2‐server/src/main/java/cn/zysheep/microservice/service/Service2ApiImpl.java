package cn.zysheep.microservice.service;

import cn.zysheep.microservice.api.Service2Api;
import org.apache.dubbo.config.annotation.Service;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Service2ApiImpl
 * @Description: 对内暴露dubbo协议给上层Application应用调用
 * @Author: 三月三
 */

@Service
public class Service2ApiImpl implements Service2Api {
    public String dubboService2() {
        return "dubboService2";
    }
}
