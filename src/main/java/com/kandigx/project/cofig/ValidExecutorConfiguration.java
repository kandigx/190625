package com.kandigx.project.cofig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线城池配置
 * @author kandigx
 * @date 2019-07-10 16:00
 */
@Configuration
@EnableAsync
public class ValidExecutorConfiguration extends AsyncConfigurerSupport {

    private final int coreSize;

    private final int maxSize;

    private final int keepAlive;

    private final int queueCapacity;

    public ValidExecutorConfiguration(@Value("${valid.thread-pool.core-size}")int coreSize,
                                      @Value("${valid.thread-pool.max-size}")int maxSize,
                                      @Value("${valid.thread-pool.keep-alive}")int keepAlive,
                                      @Value("${valid.thread-pool.queue-capacity}")int queueCapacity) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAlive = keepAlive;
        this.queueCapacity = queueCapacity;
    }

    /**
     * 使用 spring task executor
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("async-executor-");
        executor.initialize();
        return executor;
    }

}
