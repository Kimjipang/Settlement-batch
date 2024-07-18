package com.example.settlement_batch.advertisement.entity;


import com.example.settlement_batch.common.entity.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ad_statistics")
public class AdStatistics extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_stat_id")
    private Long id;

    @Column(nullable = false)
    private int daily_view;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_ad_id")
    private VideoAd videoAd;


    public AdStatistics(LocalDate date, VideoAd videoAd) {
        this.date = date;
        this.videoAd = videoAd;
    }

    public void setDaily_view(int daily_view) {
        this.daily_view = daily_view;
    }
}
