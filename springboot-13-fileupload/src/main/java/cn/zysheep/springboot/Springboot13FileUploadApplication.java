package cn.zysheep.springboot;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Springboot13FileUploadApplication
 * @Author: 三月三
 */
@SpringBootApplication
public class Springboot13FileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot13FileUploadApplication.class,args);
    }

}
