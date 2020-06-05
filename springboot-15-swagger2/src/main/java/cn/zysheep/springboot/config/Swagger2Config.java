package cn.zysheep.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: Swagger2Config
 * @Author: 三月三
 */
@Configuration
public class Swagger2Config {
    //注意：RequestHandlerSelectors.basePackage("cn.zysheep.springboot.controller")为
    // Controller 包路径，不然生成的文档扫描不到接口
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.zysheep.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("iToken API 文档")
                .description("iToken API 网关接口，http://www.zysheep.cn")
                .termsOfServiceUrl("http://www.zysheep.cn")
                .version("1.0.0")
                .build();
    }
}
