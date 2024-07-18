package com.example.settlement_batch.batch.tasklet;

import com.example.settlement_batch.advertisement.service.AdStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AdStatisticsTasklet implements Tasklet {

    private final AdStatisticsService adStatisticsService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        adStatisticsService.calculateAdDailyStat();
        return RepeatStatus.FINISHED;
    }
}
