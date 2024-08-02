package com.example.settlement_batch.advertisement.service;

import com.example.settlement_batch.advertisement.entity.AdStatistics;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.advertisement.repository.read.AdViewReadRepository;
import com.example.settlement_batch.advertisement.repository.read.VideoAdReadRepository;
import com.example.settlement_batch.advertisement.repository.write.AdStatisticsWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class AdStatisticsService {

    private final AdStatisticsWriteRepository adStatisticsWriteRepository;
    private final VideoAdReadRepository videoAdReadRepository;
    private final AdViewReadRepository adViewReadRepository;

    public void calculateAdDailyStat() {

        List<VideoAd> videoAdList = videoAdReadRepository.findAllWithVideoAndUser();

        for (VideoAd videoAd : videoAdList) {

            long user_id = videoAd.getVideo().getUser().getId();
            int daily_ad_view = adViewReadRepository.countAllAdViewExcludingOwnerByDate(videoAd.getId(), user_id, LocalDate.now());

            AdStatistics adStatistics = new AdStatistics(LocalDate.now(), videoAd);

            adStatistics.setDaily_view(daily_ad_view);

            adStatisticsWriteRepository.save(adStatistics);
        }
    }
}