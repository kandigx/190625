package com.kandigx.project.service.impl;

import com.kandigx.project.amqp.sender.MsgSender;
import com.kandigx.project.service.MsgHandlerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * rabbitmq 数据处理
 *
 * @author kandigx
 * @create 2019-07-04 16:42
 */
@Service("amqpHandlerService")
public class AmqpHandlerServiceImpl implements MsgHandlerService {

    private final MsgSender msgSender;
    private final String exchange;
    private final String routingKey;

    @Autowired
    public AmqpHandlerServiceImpl(MsgSender msgSender,
                                  @Value("${rabbitmq.exchange.default}") String exchange,
                                  @Value("${rabbitmq.routingKey.default}") String routingKey) {
        this.msgSender = msgSender;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }


    @Override
    public boolean msgPub(String msg) {
        try {
            msgSender.stringMsgSender(exchange,routingKey, UUID.randomUUID().toString(),msg);
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
