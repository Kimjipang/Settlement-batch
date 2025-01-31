package com.example.settlement_batch.advertisement.entity;


import com.example.settlement_batch.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "advertisement")
public class Advertisement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "advertisement")
    private List<VideoAd> videoAdList = new ArrayList<>();


    public Advertisement(String title) {
        this.title = title;
    }
}
