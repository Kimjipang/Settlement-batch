package com.example.settlement_batch.batch.chunk.itemProcessor;

import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.entity.VideoStatistics;
import com.example.settlement_batch.video.repository.read.VideoViewReadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class VideoStatisticsProcessor implements ItemProcessor<Video, VideoStatistics> {

    private final VideoViewReadRepository videoViewReadRepository;


    @Override
    public VideoStatistics process(Video video) throws Exception {

        try {
            int daily_video_view = videoViewReadRepository.countAllVideoViewExcludingVideoOwner(video.getId(), video.getUser().getId(), LocalDate.now());

            return new VideoStatistics(
                    LocalDate.now(),
                    daily_video_view,
                    (long) daily_video_view * video.getPlaying_time(),
                    video
            );
        } catch (Exception e) {
            log.error("Error processing video: {}", video.getId(), e);
            throw e;
        }

//        int daily_video_view = videoViewReadRepository.countAllVideoViewExcludingVideoOwner(video.getId(), video.getUser().getId(), LocalDate.now());
//
//        log.info("동영상 잘 넘어왔는지 확인 " + video.getId() + "일일 동영상 조회 수 확인 " + daily_video_view);
//
//        return new VideoStatistics(
//                LocalDate.now(),
//                daily_video_view,
//                (long) daily_video_view * video.getPlaying_time(),
//                video
//        );
    }
}
