package com.example.settlement_batch.advertisement.repository.write;

import com.example.settlement_batch.advertisement.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAdWriteRepository extends JpaRepository<VideoAd, Long> {
}
