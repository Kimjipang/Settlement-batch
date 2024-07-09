package com.example.settlement_batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Autowired
    private StepConfig stepConfig;


    @Bean
    public Job simpleJob(JobRepository jobRepository) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(stepConfig.step())
                .build();

    }
}
