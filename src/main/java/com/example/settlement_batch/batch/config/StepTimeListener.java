package com.example.settlement_batch.batch.config;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepTimeListener implements StepExecutionListener {

    private long startTime;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        startTime = System.currentTimeMillis();
        System.out.println(stepExecution.getStepName() + " 스텝이 시작된 시간 : " + startTime);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println(stepExecution.getStepName() + " 스텝이 끝난 시간 : " + endTime);
        System.out.println(stepExecution.getStepName() + " 스텝을 수행하는데 걸린 시간 : " + duration + " ms");
        return stepExecution.getExitStatus();
    }
}
