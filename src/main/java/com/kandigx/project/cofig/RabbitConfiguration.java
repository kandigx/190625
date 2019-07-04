package com.kandigx.project.cofig;

import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置
 *
 * @author kandigx
 * @create 2019-07-04 16:10
 */
@Configuration
public class RabbitConfiguration {

    private static final Log logger = LogFactory.getLog(RabbitConfiguration.class);

    private final ConnectionFactory connectionFactory;

    @Autowired
    public RabbitConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        connectionFactory.addConnectionListener(new ConnectionListener() {
            @Override
            public void onCreate(Connection connection) {
                logger.warn("RabbitMQ Connection Created : ");
            }

            @Override
            public void onShutDown(ShutdownSignalException signal) {
                logger.warn("RabbitMQ Connection Shutdown : ");
            }
        });
    }

    @Bean
    public Queue queue() {
        return new Queue("nice");
    }



}