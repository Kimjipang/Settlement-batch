package com.example.settlement_batch.advertisement.repository.read;

import com.example.settlement_batch.advertisement.entity.AdView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AdViewReadRepository extends JpaRepository<AdView, Integer> {


    @Query(value = "SELECT COUNT(*) FROM ad_view av WHERE av.video_ad_id = :videoAdId AND av.user_id <> :userId AND av.date = :date", nativeQuery = true)
    Integer countAllAdViewExcludingOwnerByDate(@Param("videoAdId") Long videoAdId, @Param("userId") Long userId, @Param("date") LocalDate date);
}
