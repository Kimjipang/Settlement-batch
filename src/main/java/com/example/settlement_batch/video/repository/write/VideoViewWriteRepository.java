package com.example.settlement_batch.video.repository.write;

import com.example.settlement_batch.video.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoViewWriteRepository extends JpaRepository<VideoView, Long> {
}
