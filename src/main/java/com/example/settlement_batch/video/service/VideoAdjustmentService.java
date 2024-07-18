package com.example.settlement_batch.video.service;

import com.example.settlement_batch.common.service.CalculateDailyView;
import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.entity.VideoAdjustment;
import com.example.settlement_batch.video.repository.read.VideoAdjustmentReadRepository;
import com.example.settlement_batch.video.repository.read.VideoReadRepository;
import com.example.settlement_batch.video.repository.read.VideoStatisticsReadRepository;
import com.example.settlement_batch.video.repository.write.VideoAdjustmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoAdjustmentService {

    private final VideoAdjustmentWriteRepository videoAdjustmentWriteRepository;
    private final VideoStatisticsReadRepository videoStatisticsReadRepository;
    private final VideoReadRepository videoReadRepository;
    private final CalculateDailyView calculateDailyView;

    public void calculateVideoDailyAdjustment() {

        List<Video> videoList = videoReadRepository.findAll();

        for(Video video : videoList) {

            int accumulate_view = video.getView_count();
            int today_view = videoStatisticsReadRepository.getDailyVideoView(video.getId(), LocalDate.now());

            BigDecimal video_daily_amount = calculateDailyView.calculateDailyAmount(
                    accumulate_view,
                    today_view,
                    new BigDecimal("1.0"),
                    new BigDecimal("1.1"),
                    new BigDecimal("1.3"),
                    new BigDecimal("1.5")
            );

            System.out.println(video_daily_amount);

            VideoAdjustment videoAdjustment = new VideoAdjustment(
                    video_daily_amount,
                    LocalDate.now(),
                    video
            );
            videoAdjustmentWriteRepository.save(videoAdjustment);
        }
    }
}
