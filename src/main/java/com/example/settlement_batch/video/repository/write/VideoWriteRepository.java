package com.example.settlement_batch.video.repository.write;

import com.example.settlement_batch.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoWriteRepository extends JpaRepository<Video, Long> {
}
