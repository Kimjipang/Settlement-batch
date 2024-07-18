package com.example.settlement_batch.batch.chunk;

import com.example.settlement_batch.common.service.CalculateDailyView;
import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.entity.VideoAdjustment;
import com.example.settlement_batch.video.repository.read.VideoStatisticsReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class VideoAdjustmentProcessor implements ItemProcessor<Video, VideoAdjustment> {

    private final VideoStatisticsReadRepository videoStatisticsReadRepository;
    private final CalculateDailyView calculateDailyView;

    @Override
    public VideoAdjustment process(Video video) throws Exception {
        int accumulateView = video.getView_count();
        Integer getTodayView = videoStatisticsReadRepository.getDailyVideoView(video.getId(), LocalDate.now().minusDays(1));
        int todayView = getTodayView == null ? 0 : getTodayView;

        BigDecimal videoDailyAmount = calculateDailyView.calculateDailyAmount(
                accumulateView,
                todayView,
                new BigDecimal("1.0"),
                new BigDecimal("1.1"),
                new BigDecimal("1.3"),
                new BigDecimal("1.5")
        );

        return new VideoAdjustment(videoDailyAmount, LocalDate.now(), video);
    }
}
