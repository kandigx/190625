package com.kandigx.project.cofig;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author kandigx
 * @date 2019-07-10 16:00
 */
@Configuration
public class ValidThreadPoolConfiguration {

    private final int coreSize;

    private final int maxSize;

    private final long keepAlive;

    public ValidThreadPoolConfiguration(@Value("${valid.thread-pool.core-size}")int coreSize,
                                        @Value("${valid.thread-pool.max-size}")int maxSize,
                                        @Value("${valid.thread-pool.keep-alive}")long keepAlive) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAlive = keepAlive;
    }

    @Bean
    public ExecutorService executorService() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("consumer-queue-thread-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(coreSize, maxSize, keepAlive,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        return pool;
    }

}
