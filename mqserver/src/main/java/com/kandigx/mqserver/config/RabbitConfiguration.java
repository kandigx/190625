package com.kandigx.mqserver.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kandigx
 * @date 2019-07-12 16:46
 */
@Configuration
public class RabbitConfiguration {

    private final int concurrentConsumerNum;


    public RabbitConfiguration(@Value("${rabbitmq.concurrent-consumer-num}") int concurrentConsumerNum) {
        this.concurrentConsumerNum = concurrentConsumerNum;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }


//    @Bean
//    public SimpleRabbitListenerContainerFactory listenerContainer() {
//
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//
//        factory.createListenerContainer();
//
//        return factory;
//    }



}
