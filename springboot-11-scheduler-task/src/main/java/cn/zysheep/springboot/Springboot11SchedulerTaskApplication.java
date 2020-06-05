package cn.zysheep.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Springboot11SchedulerTaskApplication
 * @Author: 三月三
 */
@EnableScheduling //开启基于注解的定时任务
@SpringBootApplication
public class Springboot11SchedulerTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot11SchedulerTaskApplication.class,args);
    }
}
