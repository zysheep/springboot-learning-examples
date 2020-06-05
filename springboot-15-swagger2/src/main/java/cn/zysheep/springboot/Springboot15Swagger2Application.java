package cn.zysheep.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Springboot15Swagger2Application
 * @Author: 三月三
 */
@EnableSwagger2
@SpringBootApplication
public class Springboot15Swagger2Application {
    public static void main(String[] args) {
        SpringApplication.run(Springboot15Swagger2Application.class,args);
    }
}
