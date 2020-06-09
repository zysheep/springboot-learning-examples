package cn.zysheep.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zysheep.springboot.mapper") //设置mapper接口的扫描包
public class Springboot06DataMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06DataMybatisPlusApplication.class, args);
    }

}
