package com.example.settlement_batch.video.repository.write;

import com.example.settlement_batch.video.entity.VideoStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoStatisticsWriteRepository extends JpaRepository<VideoStatistics, Long> {
}
