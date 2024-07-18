package com.example.settlement_batch.advertisement.entity;


import com.example.settlement_batch.common.entity.BaseCreateTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ad_adjustment")
public class AdAdjustment extends BaseCreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_adjustment_id")
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal daily_amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_ad_id")
    private VideoAd videoAd;


    public AdAdjustment(BigDecimal daily_amount, LocalDate date, VideoAd videoAd) {
        this.daily_amount = daily_amount;
        this.date = date;
        this.videoAd = videoAd;
    }
}
