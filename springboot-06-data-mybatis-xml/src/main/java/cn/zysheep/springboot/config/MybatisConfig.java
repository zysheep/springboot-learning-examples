package cn.zysheep.springboot.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: MybatisConfig
 * @Author: 三月三
 */
//自定义MyBatis的配置规则；给容器中添加一个ConfigurationCustomizer；
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
