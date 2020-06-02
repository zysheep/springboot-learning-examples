package cn.zysheep.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "cn.zysheep.springboot.mapper")
public class Springboot06DataTkmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot06DataTkmybatisApplication.class, args);
    }

}
