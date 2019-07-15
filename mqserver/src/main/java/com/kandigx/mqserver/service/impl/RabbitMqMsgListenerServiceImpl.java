package com.kandigx.mqserver.service.impl;

import com.kandigx.mqserver.service.MsgListenerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author kandigx
 * @date 2019-07-10 15:46
 */
@Service
public class RabbitMqMsgListenerServiceImpl implements MsgListenerService {

    /**
     * 异步接收数据
     */
    @Async("nativeExecutor")
    @RabbitListener(queues = "${rabbitmq.queue.default}")
    public void listener() {
        //todo
    }

}
