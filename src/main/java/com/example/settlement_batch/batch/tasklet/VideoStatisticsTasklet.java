package com.example.settlement_batch.batch.tasklet;

import com.example.settlement_batch.video.service.VideoStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class VideoStatisticsTasklet implements Tasklet {

    private final VideoStatisticsService videoStatisticsService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        videoStatisticsService.calculateVideoDailyStat();
        return RepeatStatus.FINISHED;
    }
}