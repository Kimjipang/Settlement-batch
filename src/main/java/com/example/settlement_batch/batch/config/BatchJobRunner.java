package com.example.settlement_batch.batch.config;

import jakarta.annotation.PostConstruct;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJobRunner {

    private final JobLauncher jobLauncher;
    private final Job calculateDailyVideo;
    private final Job calculateDailyAdvertisement;

    public BatchJobRunner(JobLauncher jobLauncher, @Qualifier("calculateDailyVideo") Job calculateDailyVideo, @Qualifier("calculateDailyAdvertisement") Job calculateDailyAdvertisement) {
        this.jobLauncher = jobLauncher;
        this.calculateDailyVideo = calculateDailyVideo;
        this.calculateDailyAdvertisement = calculateDailyAdvertisement;
    }

    @PostConstruct
    public void runAtStartup() throws Exception {
        System.out.println("Executing calculateDailyVideoJob at startup");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 매개변수 추가
                .toJobParameters();
        jobLauncher.run(calculateDailyVideo, jobParameters);

        System.out.println("Executing calculateDailyAdvertisementJob at startup");
        jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 매개변수 추가
                .toJobParameters();
        jobLauncher.run(calculateDailyAdvertisement, jobParameters);
    }

    @Scheduled(cron = "0 0 * * * ?") //
    public void runCalculateDailyVideoJob() throws Exception {
        System.out.println("Executing calculateDailyVideoJob");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 매개변수 추가
                .toJobParameters();
        jobLauncher.run(calculateDailyVideo, jobParameters);
    }

    @Scheduled(cron = "0 0 * * * ?") //
    public void runCalculateDailyAdJob() throws Exception {
        System.out.println("Executing calculateDailyAdvertisementJob");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()) // 고유한 매개변수 추가
                .toJobParameters();
        jobLauncher.run(calculateDailyAdvertisement, jobParameters);
    }
}
