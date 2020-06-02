package cn.zysheep.springboot.config;


import cn.zysheep.springboot.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "cn.zysheep.springboot.repository.primary",
		mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
}
