package com.kandigx.mqserver.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;


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
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        container.setQueues(queue001(),queue002());
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(5);
        container.setDefaultRequeueRejected(false);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setConsumerTagStrategy((queue) -> queue + "_" + UUID.randomUUID().toString());
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            String msg = new String(message.getBody());
            System.out.println("消费者：" + msg );
        });

        return container;
    }

    @Bean
    public TopicExchange exchange001(){
        return new TopicExchange("topic001", true, false);
    }

    @Bean
    public Queue queue001(){
        return new Queue("queue001", true);
    }

    @Bean
    public Binding binding001(){
        return BindingBuilder.bind(queue001()).to(exchange001()).with("spring.*");
    }

    @Bean
    public Queue queue002(){
        return new Queue("queue002", true);
    }

    @Bean
    public TopicExchange exchange002(){
        return new TopicExchange("topic002", true, false);
    }

    @Bean
    public Binding binding002(){
        return BindingBuilder.bind(queue002()).to(exchange002()).with("rabbit.*");
    }



}
