package com.example.settlement_batch.video.repository.read;

import com.example.settlement_batch.video.entity.VideoStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VideoStatisticsReadRepository extends JpaRepository<VideoStatistics, Integer> {

    @Query(value = "SELECT vs.daily_view FROM video_statistics vs " +
            "WHERE vs.video_id = :videoId " +
            "AND vs.date = :date ", nativeQuery = true)
    Integer getDailyVideoView(@Param("videoId") Long videoId, @Param("date") LocalDate date);
}
