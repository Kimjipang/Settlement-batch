package com.example.settlement_batch.advertisement.repository.write;

import com.example.settlement_batch.advertisement.entity.AdAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdAdjustmentWriteRepository extends JpaRepository<AdAdjustment, Long> {
}
