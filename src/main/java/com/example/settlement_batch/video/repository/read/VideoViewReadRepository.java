package com.example.settlement_batch.video.repository.read;

import com.example.settlement_batch.video.entity.VideoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface VideoViewReadRepository extends JpaRepository<VideoView, Long> {


    @Query(value = "SELECT COUNT(*) FROM video_view vw " +
            "WHERE vw.user_id <> :userId AND vw.video_id = :videoId AND vw.date = :date", nativeQuery = true)
    int countAllVideoViewExcludingVideoOwner(@Param("videoId") Long videoId, @Param("userId") Long userId, @Param("date") LocalDate date);


}