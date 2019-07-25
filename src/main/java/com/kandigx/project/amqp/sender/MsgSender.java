package com.kandigx.project.amqp.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kandigx
 * @date 2019-07-25 16:42
 */
@Component
public class MsgSender {

    private static final Logger log = LoggerFactory.getLogger(MsgSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        //未获得确认，则将数据打上标记后放入mongodb 中
        if (!ack) {
            //todo
        }
    };


    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        log.info("replyCode: " + replyCode + "replyText: " + replyText + "exchange: " + exchange + "routingKey: " + routingKey);
    };

    /**
     * 发送数据到对应的 exchange，并指定 routingKey
     * @param correlationId
     * @param message
     */
    public void stringMsgSender(String exchange,String routingKey,String correlationId,Object message) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        rabbitTemplate.convertAndSend(exchange, routingKey, message, new CorrelationData(correlationId));
    }


}
