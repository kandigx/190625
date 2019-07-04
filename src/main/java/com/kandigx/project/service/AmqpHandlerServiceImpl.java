package com.kandigx.project.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * rabbitmq 数据处理
 *
 * @author kandigx
 * @create 2019-07-04 16:42
 */
@Service
public class AmqpHandlerServiceImpl implements MsgHandlerService {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public AmqpHandlerServiceImpl(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }


    @Override
    public boolean msgPub(String msg) {
        amqpTemplate.convertAndSend("rounteKye", msg);
        return true;
    }
}
