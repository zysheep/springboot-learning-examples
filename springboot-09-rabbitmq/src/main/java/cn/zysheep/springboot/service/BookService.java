package cn.zysheep.springboot.service;

import cn.zysheep.springboot.entity.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @ProjectName: springboot-learning-examples
 * @ClassName: BookService
 * @Author: 三月三
 */
@Service
public class BookService {
    //要使该注解生效，必须要加@EnableRabbit注解
    @RabbitListener(queues = "zysheep.news")
    public void receive(Book book) {
        System.out.println("收到消息：" + book);
    }

    @RabbitListener(queues = "zysheep.emps")
    public void receive02(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
