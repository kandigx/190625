package com.kandigx.mqserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQserverApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testMessage() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().put("desc", "这是一个信息描述");
        messageProperties.getHeaders().put("type", "自定义消息类型");
        Message message = new Message("hello RabbitMQ".getBytes(), messageProperties);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", message, (msg -> {
            System.out.println("-----添加额外设置----");
            msg.getMessageProperties().getHeaders().put("desc", "修改desc之后的信息");
            msg.getMessageProperties().getHeaders().put("attr", "添加额外的信息");
            return msg;
        }
        ));
    }

    @Test
    public void testMessage2() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        Message message = new Message("mq 消息123".getBytes(), messageProperties);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", "hello object message send 001");
        rabbitTemplate.convertAndSend("topic002", "rabbit.abc", "hello object message send 002");

        rabbitTemplate.convertAndSend("topic001", "spring.abc", message);
    }

}
