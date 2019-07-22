package com.kandigx.project.service.impl;

import com.kandigx.project.service.MsgHandlerService;
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
@Service("amqpHandlerService")
public class AmqpHandlerServiceImpl implements MsgHandlerService {

    private final String msgKey;
    private final String queue;

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public AmqpHandlerServiceImpl(AmqpTemplate amqpTemplate,
                                  @Value("${rabbitmq.routingKey.default}") String routingKey,
                                  @Value("${rabbitmq.queue.default}") String queue) {
        this.amqpTemplate = amqpTemplate;
        this.msgKey = routingKey;
        this.queue = queue;
    }


    @Override
    public boolean msgPub(String msg) {
        try {
            amqpTemplate.convertAndSend(this.msgKey, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean msgSub(String queue) {
        //todo
        return true;
    }

}
