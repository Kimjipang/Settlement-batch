//package com.example.settlement_batch.advertisement;
//
//
//import com.example.settlement_batch.advertisement.service.AdAdjustmentService;
//import com.example.settlement_batch.advertisement.service.AdStatisticsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AdStatisticsController {
//
//    private final AdStatisticsService adStatisticsService;
//    private final AdAdjustmentService adAdjustmentService;
//
//    @GetMapping("/api/ad-stat")
//    public void calculateDailyAdViewStatistics() {
//
//        adStatisticsService.calculateAdDailyStat();
//    }
//
//    @GetMapping("/api/ad-adjustment")
//    public void calculateDailyAmount() {
//        adAdjustmentService.calculateDailyAdAmount();
//    }
//}
