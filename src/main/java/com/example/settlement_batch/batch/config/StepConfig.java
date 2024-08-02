package com.example.settlement_batch.batch.config;

import com.example.settlement_batch.advertisement.entity.AdAdjustment;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.batch.chunk.itemProcessor.AdAdjustmentProcessor;
import com.example.settlement_batch.batch.chunk.itemProcessor.VideoAdjustmentProcessor;
import com.example.settlement_batch.batch.chunk.itemProcessor.VideoStatisticsProcessor;
import com.example.settlement_batch.batch.chunk.itemReader.AdItemReader;
import com.example.settlement_batch.batch.chunk.itemWriter.AdAdjustmentWriter;
import com.example.settlement_batch.batch.chunk.itemWriter.VideoAdjustmentWriter;
import com.example.settlement_batch.batch.listener.StepExceptionListener;
import com.example.settlement_batch.batch.listener.VideoItemReadListener;
import com.example.settlement_batch.batch.tasklet.AdStatisticsTasklet;
import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.entity.VideoAdjustment;
import com.example.settlement_batch.video.entity.VideoStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class StepConfig {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private JpaItemWriter<VideoStatistics> videoStatisticsJpaItemWriter;
    @Autowired
    private VideoAdjustmentProcessor videoAdjustmentProcessor;
    @Autowired
    private VideoStatisticsProcessor videoStatisticsProcessor;
    @Autowired
    private VideoAdjustmentWriter videoAdjustmentWriter;
    @Autowired
    private AdStatisticsTasklet adStatisticsTasklet;
    @Autowired
    private AdItemReader adItemReader;
    @Autowired
    private AdAdjustmentProcessor adAdjustmentProcessor;
    @Autowired
    private AdAdjustmentWriter adAdjustmentWriter;
    @Autowired
    private StepTimeListener stepTimeListener;
    @Autowired
    private JpaPagingItemReader<Video> jpaPagingItemReader;
    @Autowired
    private VideoItemReadListener videoItemReadListener;
    @Autowired
    private StepExceptionListener stepExceptionListener;
    @Autowired
    private TaskExecutorConfig taskExecutorConfig;

    @Bean
    @JobScope
    public Step calculateVideoStat() {
        return new StepBuilder("calculateVideoStat", jobRepository)
                .<Video, VideoStatistics>chunk(10, transactionManager)
                .listener(stepTimeListener)
                .reader(jpaPagingItemReader)
                .processor(videoStatisticsProcessor)
                .writer(videoStatisticsJpaItemWriter)
                .listener(videoItemReadListener)
                .listener(stepExceptionListener)
                .taskExecutor(taskExecutorConfig.taskExecutor())
                .build();
    }

    @Bean
    public Step calculateVideoAdjustment() {
        return new StepBuilder("calculateVideoAdjustment", jobRepository)
                .<Video, VideoAdjustment>chunk(10, transactionManager)
                .listener(stepTimeListener)
                .reader(jpaPagingItemReader)
                .processor(videoAdjustmentProcessor)
                .writer(videoAdjustmentWriter)
//                .taskExecutor(taskExecutor())
                .build();

    }

    @Bean
    public Step calculateAdStat() {
        return new StepBuilder("calculateAdStat", jobRepository)
                .listener(stepTimeListener)
                .tasklet(adStatisticsTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step calculateAdAdjustment() {
        return new StepBuilder("calculateAdAdjustment", jobRepository)
                .<VideoAd, AdAdjustment>chunk(10, transactionManager)
                .listener(stepTimeListener)
                .reader(adItemReader)
                .processor(adAdjustmentProcessor)
                .writer(adAdjustmentWriter)
//                .taskExecutor(taskExecutor())
                .build();
    }

//    @Bean(name = "batchTaskExecutor")
//    public TaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(4);
//        executor.setMaxPoolSize(4);
//        executor.setQueueCapacity(25);
//        executor.setThreadNamePrefix("batchTaskExecutor-");
//        executor.initialize();
//
//
//        new Thread(() -> {
//            while (true) {
//                log.info("Active Threads: {}", executor.getActiveCount());
//                log.info("Pool Size: {}", executor.getPoolSize());
//                log.info("Queue Size: {}", executor.getThreadPoolExecutor().getQueue().size());
//                try {
//                    Thread.sleep(30000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    break;
//                }
//            }
//        }).start();
//        return executor;
//    }

}
