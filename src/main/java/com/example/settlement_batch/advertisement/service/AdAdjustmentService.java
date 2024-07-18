package com.example.settlement_batch.advertisement.service;

import com.example.settlement_batch.advertisement.entity.AdAdjustment;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.advertisement.repository.read.AdStatisticsReadRepository;
import com.example.settlement_batch.advertisement.repository.read.VideoAdReadRepository;
import com.example.settlement_batch.advertisement.repository.write.AdAdjustmentWriteRepository;
import com.example.settlement_batch.common.service.CalculateDailyView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class AdAdjustmentService {

    private final AdAdjustmentWriteRepository adAdjustmentRepository;
    private final AdStatisticsReadRepository adStatisticsReadRepository;
    private final VideoAdReadRepository videoAdReadRepository;
    private final CalculateDailyView calculateDailyView;

    public void calculateDailyAdAmount() {

        List<VideoAd> videoAdList = videoAdReadRepository.findAll();

        for (VideoAd videoAd : videoAdList) {

            int accumulate_view = videoAd.getView_count();
            int today_view = adStatisticsReadRepository.getDailyAdView(videoAd.getId(), LocalDate.now());

            BigDecimal videoAd_daily_amount = calculateDailyView.calculateDailyAmount(
                    accumulate_view,
                    today_view,
                    new BigDecimal("10"),
                    new BigDecimal("12"),
                    new BigDecimal("15"),
                    new BigDecimal("20")
            );

            AdAdjustment adAdjustment = new AdAdjustment(
                    videoAd_daily_amount,
                    LocalDate.now(),
                    videoAd
            );
            adAdjustmentRepository.save(adAdjustment);
        }
    }
}
