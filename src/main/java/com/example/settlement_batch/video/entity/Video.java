package com.example.settlement_batch.video.entity;

import com.example.settlement_batch.common.entity.BaseTimeEntity;
import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video")
public class Video extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private int playing_time; // 영상 총 시간

    @Column(nullable = false)
    private int view_count;

    @Column(nullable = false)
    private long total_playing_time; // 총 재생 시간

    @ManyToOne(fetch = FetchType.LAZY) // 실무에서 모든 연관관계는 지연 로딩으로 해두어야 함. 즉시 로딩이 걸려있으면 성능 최적화가 어려움
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "video")
    private List<VideoView> videoViewList = new ArrayList<>();

    @OneToMany(mappedBy = "video")
    private List<VideoAdjustment> videoAdjustmentList = new ArrayList<>();

    @OneToMany(mappedBy = "video")
    private List<VideoAd> videoAdList = new ArrayList<>();

    public Video(String title, int playing_time, int view_count, long total_playing_time, User user) {
        this.title = title;
        this.playing_time = playing_time;
        this.view_count = view_count;
        this.total_playing_time = total_playing_time;
        this.user = user;
    }
}
