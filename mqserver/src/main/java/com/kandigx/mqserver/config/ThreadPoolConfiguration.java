package com.kandigx.mqserver.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kandigx
 * @date 2019-07-12 16:54
 */
@Configuration
public class ThreadPoolConfiguration {

    private final int coreSize;

    private final int maxSize;

    private final int keepAlive;


    public ThreadPoolConfiguration(@Value("${thread.pool.core-size}")int coreSize,
                                  @Value("${thread.pool.max-size}")int maxSize,
                                  @Value("${thread.pool.keep-alive}")int keepAlive) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAlive = keepAlive;
    }

    /**
     * 使用java executor
     * @return
     */
    @Bean("nativeExecutor")
    public Executor nativeExecutor() {
        return new ThreadPoolExecutor(coreSize,
                maxSize,
                keepAlive,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadFactoryBuilder().setNameFormat("native-executor-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }

}
