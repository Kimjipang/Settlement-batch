package com.example.settlement_batch.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public Job calculateDailyVideo(JobRepository jobRepository,
                                   @Qualifier("calculateVideoStat") Step calculateVideoStat,
                                   @Qualifier("calculateVideoAdjustment") Step calculateVideoAdjustment
                                   ) {
        return new JobBuilder("calculateDailyVideo", jobRepository)
                .preventRestart()
                .start(calculateVideoStat)
                .next(calculateVideoAdjustment)
                .build();

    }

    @Bean
    public Job calculateDailyAdvertisement(JobRepository jobRepository,
                                           @Qualifier("calculateAdStat") Step calculateAdStat,
                                           @Qualifier("calculateAdAdjustment") Step calculateAdAdjustment
                                           ) {
        return new JobBuilder("calculateDailyAdvertisement", jobRepository)
                .preventRestart()
                .start(calculateAdStat)
                .next(calculateAdAdjustment)
                .build();
    }

}
