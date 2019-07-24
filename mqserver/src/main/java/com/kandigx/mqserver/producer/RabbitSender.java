package com.kandigx.mqserver.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author kandigx
 * @date 2019-07-24 14:50
 */
@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("CorrelationData : " + correlationData);
            if (!ack) {
                System.out.println("异常处理...");
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange : " + exchange + "replyCode" + replyCode + "replyText" + replyText + "routingKey" + routingKey);
        }
    };

    public void send(Object message, Map<String,Object> properties) throws Exception {
        MessageHeaders headers = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, headers);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData cd = new CorrelationData();
        cd.setId("1234567890");
        rabbitTemplate.convertAndSend("exchange-1", "spring.hello", msg,cd);
    }

}
