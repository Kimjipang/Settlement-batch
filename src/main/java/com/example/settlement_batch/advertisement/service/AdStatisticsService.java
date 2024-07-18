package com.example.settlement_batch.advertisement.service;

import com.example.settlement_batch.advertisement.entity.AdStatistics;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.advertisement.repository.read.AdViewReadRepository;
import com.example.settlement_batch.advertisement.repository.read.VideoAdReadRepository;
import com.example.settlement_batch.advertisement.repository.write.AdStatisticsWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdStatisticsService {

    private final AdStatisticsWriteRepository adStatisticsWriteRepository;
    private final VideoAdReadRepository videoAdReadRepository;
    private final AdViewReadRepository adViewReadRepository;

    public void calculateAdDailyStat() {

        List<VideoAd> videoAdList = videoAdReadRepository.findAll();

        for (VideoAd videoAd : videoAdList) {

            long user_id = videoAd.getVideo().getUser().getId(); // 광고가 붙어있는 영상의 업로더
            int daily_ad_view = adViewReadRepository.countAllAdViewExcludingOwnerByDate(videoAd.getId(), user_id, LocalDate.now().minusDays(1));

            AdStatistics adStatistics = new AdStatistics(LocalDate.now(), videoAd);

            adStatistics.setDaily_view(daily_ad_view);

            adStatisticsWriteRepository.save(adStatistics);
        }
    }
}