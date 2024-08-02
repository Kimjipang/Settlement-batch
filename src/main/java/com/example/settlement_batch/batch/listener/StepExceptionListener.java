package com.example.settlement_batch.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StepExceptionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("동영상 통계 스텝 시작합니당 ~");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (stepExecution.getStatus() == BatchStatus.FAILED) {
            log.error("Step failed with status: {}", stepExecution.getStatus());
            stepExecution.getFailureExceptions().forEach(ex -> log.error("Step failed with exception: ", ex));
        }
        return stepExecution.getExitStatus();
    }
}

