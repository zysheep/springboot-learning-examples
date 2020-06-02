package cn.zysheep.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot04LoggingApplicationTests {

    Logger logger = LoggerFactory.getLogger(Springboot04LoggingApplicationTests.class);

    @Test
    void contextLoads() {
        //日志级别
        //由低到高 trace<debug<info<warn<error
        //可以调整输出的日志级别;日志就只会在这个级别以后的高级别生效
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        //SpringBoot默认给我们使用的是info级别
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

}
