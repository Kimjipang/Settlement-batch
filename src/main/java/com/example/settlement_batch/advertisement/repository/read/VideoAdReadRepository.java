package com.example.settlement_batch.advertisement.repository.read;

import com.example.settlement_batch.advertisement.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoAdReadRepository extends JpaRepository<VideoAd, Long> {
}
