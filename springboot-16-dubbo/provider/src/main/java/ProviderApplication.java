import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: ProviderApplication
 * @Author: 三月三
 */
// 提供服务的应用必须配置此项
@SpringBootApplication
@DubboComponentScan("cn.zysheep.provider.service")
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
