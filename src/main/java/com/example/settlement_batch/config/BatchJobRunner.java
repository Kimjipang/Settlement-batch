//package com.example.settlement_batch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BatchJobRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job simpleJob;
//
//    public void runBatchJob() throws Exception {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("time", System.currentTimeMillis()) // 고유한 매개변수 추가
//                .toJobParameters();
//        jobLauncher.run(simpleJob, jobParameters);
//    }
//}
