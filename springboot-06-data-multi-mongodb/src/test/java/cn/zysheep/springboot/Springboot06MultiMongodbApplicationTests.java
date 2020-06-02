package cn.zysheep.springboot;

import cn.zysheep.springboot.config.PrimaryMongoConfig;
import cn.zysheep.springboot.config.SecondaryMongoConfig;

import cn.zysheep.springboot.model.User;
import cn.zysheep.springboot.repository.primary.PrimaryRepository;
import cn.zysheep.springboot.repository.secondary.SecondaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Springboot06MultiMongodbApplicationTests {


    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    @Test
    void contextLoads() {
        System.out.println("************************************************************");
        System.out.println("测试开始");
        System.out.println("************************************************************");
        this.primaryRepository.save(new User("妲己", "123456"));
        this.secondaryRepository.save(new User("吕布", "654321"));
        List<User> primaries = this.primaryRepository.findAll();
        for (User primary : primaries) {
            System.out.println(primary.toString());
        }
        List<User> secondaries = this.secondaryRepository.findAll();
        for (User secondary : secondaries) {
            System.out.println(secondary.toString());
        }
        System.out.println("************************************************************");
        System.out.println("测试完成");
        System.out.println("************************************************************");
    }

}
