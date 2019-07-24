package com.kandigx.mqserver.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.Message;

/**
 * @author kandigx
 * @date 2019-07-24 14:39
 */
@ComponentScan
public class RabbitReceiver {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue-1", durable = "true"),
                    exchange = @Exchange(value = "exchange-1",
                            durable = "true", type = "topic",
                            ignoreDeclarationExceptions = "true"),
                            key = "springboot.*"
            )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception{
        System.out.println("消费到的Playload：" + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
