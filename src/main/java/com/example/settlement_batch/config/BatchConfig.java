package com.example.settlement_batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    private Job simpleJob;
    @Autowired
    private JobLauncher jobLauncher;


//    @Bean
//    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
//        return new ThreadPoolTaskScheduler();
//    }
//
//    @Scheduled(fixedRate = 60000) // 1분 ms기준
//    public void runJob() throws Exception {
//        jobLauncher.run(simpleJob, new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis())
//                .toJobParameters());
//    }
}
