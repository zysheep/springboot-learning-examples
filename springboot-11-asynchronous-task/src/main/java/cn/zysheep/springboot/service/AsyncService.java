package cn.zysheep.springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: AsyncService
 * @Author: 三月三
 */
@Service
public class AsyncService {

    //告诉Spring这是一个异步方法
    @Async
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中...");
    }
}
