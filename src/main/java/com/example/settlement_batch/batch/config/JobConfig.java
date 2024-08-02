package com.example.settlement_batch.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class JobConfig {

    @Autowired
    private StepConfig stepConfig;


    @Bean
    public Job calculateDailyVideo(JobRepository jobRepository) {
        return new JobBuilder("calculateDailyVideo", jobRepository)
                .preventRestart()
                .start(stepConfig.calculateVideoStat())
                .next(stepConfig.calculateVideoAdjustment())
                .build();

    }

    @Bean
    public Job calculateDailyAdvertisement(JobRepository jobRepository) {
        return new JobBuilder("calculateDailyAdvertisement", jobRepository)
                .preventRestart()
                .start(stepConfig.calculateAdStat())
                .next(stepConfig.calculateAdAdjustment())
                .build();
    }

}
