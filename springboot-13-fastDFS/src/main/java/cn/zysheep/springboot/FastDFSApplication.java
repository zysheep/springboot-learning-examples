package cn.zysheep.springboot;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @version v1.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: FastDFSApplication
 * @desc
 * @Author: 三月三
 */

@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@SpringBootApplication
public class FastDFSApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastDFSApplication.class,args);
    }
}
