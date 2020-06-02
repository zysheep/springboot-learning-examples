package cn.zysheep.springboot.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;


/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: MongoConfig
 * @Author: 三月三
 */
@Configuration
public class MongoConfig {
    //读取配置文件里的值，部署后IP或者数据库名变化，不需重新打包
    @Value("${spring.data.mongodb.host1}")
    private String mongohost1;
    @Value("${spring.data.mongodb.host2}")
    private String mongohost2;
    @Value("${spring.data.dbName.primaryDb}")
    private String primaryDb;
    @Value("${spring.data.dbName.secondaryDb}")
    private String secondaryDb;
    @Bean
    @Primary
    public MongoClient mongoClient() {
        return new MongoClient(mongohost1);
    }
    @Bean
    public  MongoClient mongoClient2() {
        return new MongoClient(mongohost2);
    }

    @Bean
    @Primary
    public   MongoTemplate primaryMongoTemplate() {
        return new MongoTemplate(mongoClient(), primaryDb);
    }
    @Bean
    public   MongoTemplate secondaryMongoTemplate() {
        return  new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), secondaryDb));
    }


}
