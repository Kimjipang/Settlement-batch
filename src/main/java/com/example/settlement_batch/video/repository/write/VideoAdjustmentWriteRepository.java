package com.example.settlement_batch.video.repository.write;

import com.example.settlement_batch.video.entity.VideoAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAdjustmentWriteRepository extends JpaRepository<VideoAdjustment, Long> {
}
