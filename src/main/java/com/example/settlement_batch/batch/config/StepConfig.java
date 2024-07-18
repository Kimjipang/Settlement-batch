package com.example.settlement_batch.batch.config;


import com.example.settlement_batch.advertisement.entity.AdAdjustment;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.batch.chunk.*;
import com.example.settlement_batch.batch.tasklet.AdStatisticsTasklet;
import com.example.settlement_batch.batch.tasklet.VideoStatisticsTasklet;
import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.entity.VideoAdjustment;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class StepConfig {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private VideoStatisticsTasklet videoStatisticsTasklet;
    @Autowired
    private VideoItemReader videoItemReader;
    @Autowired
    private VideoAdjustmentProcessor videoAdjustmentProcessor;
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

    @Bean
    @JobScope
    public Step calculateVideoStat() {
        return new StepBuilder("calculateVideoStat", jobRepository)
                .tasklet(videoStatisticsTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step calculateVideoAdjustment() {
        return new StepBuilder("calculateVideoAdjustment", jobRepository)
                .<Video, VideoAdjustment>chunk(10, transactionManager)
                .reader(videoItemReader)
                .processor(videoAdjustmentProcessor)
                .writer(videoAdjustmentWriter)
                .build();

    }

    @Bean
    public Step calculateAdStat() {
        return new StepBuilder("calculateAdStat", jobRepository)
                .tasklet(adStatisticsTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step calculateAdAdjustment() {
        return new StepBuilder("calculateAdAdjustment", jobRepository)
                .<VideoAd, AdAdjustment>chunk(10, transactionManager)
                .reader(adItemReader)
                .processor(adAdjustmentProcessor)
                .writer(adAdjustmentWriter)
                .build();
    }
}
