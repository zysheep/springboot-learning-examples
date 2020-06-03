package cn.zysheep.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Springboot08RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08RedisApplication.class, args);
    }

}
