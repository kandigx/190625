package com.kandigx.mqserver.service.impl;

import com.kandigx.mqserver.service.MsgListenerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author kandigx
 * @date 2019-07-10 15:46
 */
public class RabbitMqMsgListenerServiceImpl implements MsgListenerService {

    @RabbitListener(queues = "${rabbitmq.queue.default}")
    public void listener() {
        //todo
    }

}
