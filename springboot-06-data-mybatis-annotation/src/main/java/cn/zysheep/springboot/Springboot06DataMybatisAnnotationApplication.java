package cn.zysheep.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.zysheep.springboot.mapper")
@SpringBootApplication
public class Springboot06DataMybatisAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06DataMybatisAnnotationApplication.class, args);
    }

}
