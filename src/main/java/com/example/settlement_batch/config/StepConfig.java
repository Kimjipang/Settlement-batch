package com.example.settlement_batch.config;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step started");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
