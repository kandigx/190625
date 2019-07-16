package com.kandigx.mqserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@MapperScan("com.kandigx.mqserver.dao")
public class MQserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MQserverApplication.class, args);
    }

}
