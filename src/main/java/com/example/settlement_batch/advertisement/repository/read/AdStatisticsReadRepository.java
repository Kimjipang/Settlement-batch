package com.example.settlement_batch.advertisement.repository.read;

import com.example.settlement_batch.advertisement.entity.AdStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AdStatisticsReadRepository extends JpaRepository<AdStatistics, Integer> {


    @Query(value = "SELECT ads.daily_view FROM ad_statistics ads " +
            "WHERE ads.video_ad_id = :videoAdId " +
            "AND ads.date = :date ", nativeQuery = true)
    Integer getDailyAdView(@Param("videoAdId") Long videoAdId, @Param("date") LocalDate date);
}
