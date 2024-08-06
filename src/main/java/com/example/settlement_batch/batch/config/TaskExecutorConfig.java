package com.example.settlement_batch.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Slf4j
public class TaskExecutorConfig {

    @Bean(name = "batchTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("batchTaskExecutor-");
        executor.initialize();

        new Thread(() -> {
            while (true) {
                log.info("Active Threads: {}", executor.getActiveCount());
                log.info("Pool Size: {}", executor.getPoolSize());
                log.info("Queue Size: {}", executor.getThreadPoolExecutor().getQueue().size());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();

        return executor;
    }
}