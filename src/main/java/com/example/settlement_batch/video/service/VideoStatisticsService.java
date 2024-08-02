package com.example.settlement_batch.video.service;

import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.entity.VideoStatistics;
import com.example.settlement_batch.video.repository.read.VideoReadRepository;
import com.example.settlement_batch.video.repository.read.VideoStatisticsReadRepository;
import com.example.settlement_batch.video.repository.read.VideoViewReadRepository;
import com.example.settlement_batch.video.repository.write.VideoStatisticsWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class VideoStatisticsService {

    private final VideoStatisticsWriteRepository videoStatisticsWriteRepository;
    private final VideoViewReadRepository videoViewReadRepository;
    private final VideoReadRepository videoReadRepository;

    public void calculateVideoDailyStat() {

        List<Video> videoList = videoReadRepository.findAll();

        for(Video video : videoList) {
            int daily_video_view = videoViewReadRepository.countAllVideoViewExcludingVideoOwner(video.getId(), video.getUser().getId(), LocalDate.now());

            VideoStatistics videoStatistics = new VideoStatistics(
                    LocalDate.now(),
                    daily_video_view,
                    (long) daily_video_view * video.getPlaying_time(),
                    video
            );

            videoStatisticsWriteRepository.save(videoStatistics);
        }
    }
}