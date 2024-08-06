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
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class StepConfig {

    @Bean(name = "calculateVideoStat")
    @JobScope
    public Step calculateVideoStat(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            JpaItemWriter<VideoStatistics> videoStatisticsJpaItemWriter,
            VideoStatisticsProcessor videoStatisticsProcessor,
            JpaPagingItemReader<Video> jpaPagingItemReader,
            VideoItemReadListener videoItemReadListener,
            StepTimeListener stepTimeListener,
            StepExceptionListener stepExceptionListener,
            @Qualifier("batchTaskExecutor") TaskExecutor taskExecutor) {

        log.info("Creating Step: calculateVideoStat");
        if (log.isInfoEnabled()) {
            log.info("Using Reader: {}", jpaPagingItemReader.getClass().getName());
            log.info("Using Processor: {}", videoStatisticsProcessor.getClass().getName());
            log.info("Using Writer: {}", videoStatisticsJpaItemWriter.getClass().getName());
        }

        return new StepBuilder("calculateVideoStat", jobRepository)
                .<Video, VideoStatistics>chunk(10, transactionManager)
                .listener(stepTimeListener)
                .reader(jpaPagingItemReader)
                .processor(videoStatisticsProcessor)
                .writer(videoStatisticsJpaItemWriter)
                .listener(videoItemReadListener)
                .listener(stepExceptionListener)
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean(name = "calculateVideoAdjustment")
    public Step calculateVideoAdjustment(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            VideoAdjustmentProcessor videoAdjustmentProcessor,
            JpaPagingItemReader<Video> jpaPagingItemReader,
            VideoAdjustmentWriter videoAdjustmentWriter,
            StepTimeListener stepTimeListener,
            @Qualifier("batchTaskExecutor") TaskExecutor taskExecutor) {

        return new StepBuilder("calculateVideoAdjustment", jobRepository)
                .<Video, VideoAdjustment>chunk(10, transactionManager)
                .listener(stepTimeListener)
                .reader(jpaPagingItemReader)
                .processor(videoAdjustmentProcessor)
                .writer(videoAdjustmentWriter)
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean(name = "calculateAdStat")
    public Step calculateAdStat(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            AdStatisticsTasklet adStatisticsTasklet,
            StepTimeListener stepTimeListener) {

        return new StepBuilder("calculateAdStat", jobRepository)
                .listener(stepTimeListener)
                .tasklet(adStatisticsTasklet, transactionManager)
                .build();
    }

    @Bean(name = "calculateAdAdjustment")
    public Step calculateAdAdjustment(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            AdItemReader adItemReader,
            AdAdjustmentProcessor adAdjustmentProcessor,
            AdAdjustmentWriter adAdjustmentWriter,
            StepTimeListener stepTimeListener,
            @Qualifier("batchTaskExecutor") TaskExecutor taskExecutor
    ) {

        return new StepBuilder("calculateAdAdjustment", jobRepository)
                .<VideoAd, AdAdjustment>chunk(10, transactionManager)
                .listener(stepTimeListener)
                .reader(adItemReader)
                .processor(adAdjustmentProcessor)
                .writer(adAdjustmentWriter)
                .taskExecutor(taskExecutor)
                .build();
    }

}
