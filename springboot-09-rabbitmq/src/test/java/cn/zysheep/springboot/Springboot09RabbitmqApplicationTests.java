package cn.zysheep.springboot;

import cn.zysheep.springboot.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot09RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
        //创建交换器
        //amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        //System.out.println("创建完成");

        //创建队列
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        //durable:是否持久化

        //创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.haha", null));
         //String destination:目的地
        // Binding.DestinationType destinationType:目的地类型
        // String exchange: 交换器
        // String routingKey:路由键
        // @Nullable Map<String, Object> arguments) :参数头信息

        //删除操作
       // amqpAdmin.declareQueue();
       // amqpAdmin.declareExchange();
       // amqpAdmin.declareBinding();
    }

    /**
     * 1、单播（点对点）
     */
    @Test
    public void test1() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "zysheep.news",map);
        //rabbitTemplate.convertAndSend("exchange.direct", "zysheep.news", new Book("西游记", "吴承恩"));
    }

    //接受数据,如何将数据自动的转为json发送出去,编写自己的配置覆盖Springboot自动的MessageConverter序列化规则
    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("zysheep.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("三月三", "zysheep"));
    }

}
