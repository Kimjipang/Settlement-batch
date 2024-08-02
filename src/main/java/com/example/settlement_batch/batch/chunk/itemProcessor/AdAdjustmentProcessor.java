package com.example.settlement_batch.batch.chunk.itemProcessor;

import com.example.settlement_batch.advertisement.entity.AdAdjustment;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.advertisement.repository.read.AdStatisticsReadRepository;
import com.example.settlement_batch.common.service.CalculateDailyView;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AdAdjustmentProcessor implements ItemProcessor<VideoAd, AdAdjustment> {

    private final AdStatisticsReadRepository adStatisticsReadRepository;
    private final CalculateDailyView calculateDailyView;

    @Override
    public AdAdjustment process(VideoAd videoAd) throws Exception {
        int accumulateView = videoAd.getView_count();
        Integer getTodayView = adStatisticsReadRepository.getDailyAdView(videoAd.getId(), LocalDate.now());
        int todayView = getTodayView == null ? 0 : getTodayView;

        BigDecimal adDailyAmount = calculateDailyView.calculateDailyAmount(
                accumulateView,
                todayView,
                new BigDecimal("10"),
                new BigDecimal("12"),
                new BigDecimal("15"),
                new BigDecimal("20")
        );

        return new AdAdjustment(adDailyAmount, LocalDate.now(), videoAd);
    }
}
