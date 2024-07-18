package com.example.settlement_batch.advertisement.repository.write;

import com.example.settlement_batch.advertisement.entity.AdStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdStatisticsWriteRepository extends JpaRepository<AdStatistics, Long> {
}
