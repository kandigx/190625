package com.kandigx.project.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * rabbitmq 数据处理
 *
 * @author kandigx
 * @create 2019-07-04 16:42
 */
@Service
public class AmqpHandlerServiceImpl implements MsgHandlerService {

    private final String msgKey;

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public AmqpHandlerServiceImpl(AmqpTemplate amqpTemplate,
                                  @Value("${rabbitmq.routingKey.default}") String routingKey) {
        this.amqpTemplate = amqpTemplate;
        this.msgKey = routingKey;
    }


    @Override
    public boolean msgPub(String msg) {
        amqpTemplate.convertAndSend(this.msgKey, msg);
        return true;
    }
}
