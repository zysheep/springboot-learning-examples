package cn.zysheep.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Springboot11AsynchronousTaskApplication
 * @Author: 三月三
 */
@EnableAsync  //开启异步注解功能
@SpringBootApplication
public class Springboot11AsynchronousTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot11AsynchronousTaskApplication.class,args);
    }
}
