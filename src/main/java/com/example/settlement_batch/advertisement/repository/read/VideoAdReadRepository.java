package com.example.settlement_batch.advertisement.repository.read;

import com.example.settlement_batch.advertisement.entity.VideoAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoAdReadRepository extends JpaRepository<VideoAd, Long> {

    @Query("SELECT va FROM VideoAd va JOIN FETCH va.video v JOIN FETCH v.user")
    List<VideoAd> findAllWithVideoAndUser();
}
