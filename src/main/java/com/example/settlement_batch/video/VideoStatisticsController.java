//package com.example.settlement_batch.video;
//
//import com.example.settlement_batch.video.service.VideoAdjustmentService;
//import com.example.settlement_batch.video.service.VideoStatisticsService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//public class VideoStatisticsController {
//
//    private final VideoStatisticsService videoStatisticsService;
//    private final VideoAdjustmentService videoAdjustmentService;
//
//    @GetMapping("/api/stat")
//    public void calculateVideoDailyStatistics() {
//        videoStatisticsService.calculateVideoDailyStat();
//    }
//
//    @GetMapping("/api/adjustment")
//    public void calculateVideoDailyAdjustment() {
//        videoAdjustmentService.calculateVideoDailyAdjustment();
//    }
//}
