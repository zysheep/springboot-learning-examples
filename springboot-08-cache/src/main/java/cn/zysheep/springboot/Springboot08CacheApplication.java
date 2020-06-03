package cn.zysheep.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@MapperScan("cn.zysheep.springboot.mapper")
@SpringBootApplication
@EnableCaching  //开启基于注解的缓存
public class Springboot08CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08CacheApplication.class, args);
    }

}
