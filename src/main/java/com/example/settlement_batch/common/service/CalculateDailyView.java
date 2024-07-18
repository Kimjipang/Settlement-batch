package com.example.settlement_batch.common.service;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculateDailyView {

    public BigDecimal calculateDailyAmount(
            int accumulate_count,
            int today_count,
            BigDecimal under_100K,
            BigDecimal under_500K,
            BigDecimal under_1M,
            BigDecimal over_1M
    ) {
        BigDecimal daily_amount = BigDecimal.ZERO;

        // 정산하려는 날짜가 첫 정산날인 경우
        if(accumulate_count - today_count == 0) {
            // 100만 이상인 경우
            if (today_count >= 1000000) {
                daily_amount = under_100K.multiply(new BigDecimal(99999))
                        .add(under_500K.multiply(new BigDecimal(400000)))
                        .add(under_1M.multiply(new BigDecimal(500000)))
                        .add(over_1M.multiply(new BigDecimal(today_count - 999999)));
            }
            // 50만 이상 100만 미만인 경우
            else if (today_count >= 500000 && today_count < 1000000) {
                daily_amount = under_100K.multiply(new BigDecimal(99999))
                        .add(under_500K.multiply(new BigDecimal(400000)))
                        .add(under_1M.multiply(new BigDecimal(today_count - 499999)));
            }
            // 10만 이상 50만 미만인 경우
            else if (today_count >= 100000 && today_count < 500000) {
                daily_amount = under_100K.multiply(new BigDecimal(99999))
                        .add(under_500K.multiply(new BigDecimal(today_count - 99999)));
            }
            // 10만 미만인 경우
            else {
                daily_amount = under_100K.multiply(new BigDecimal(today_count));
            }
        }
        // 누적 조회수가 존재하는 경우(정산하려는 날이 첫 정산 날이 아닌 경우)
        else {
            // 누적 조회수 - 당일 조회수가 100만 이상인 경우(당일을 제외한 조회수가 100만 이상)
            if (accumulate_count - today_count >= 1000000) {
                daily_amount = over_1M.multiply(new BigDecimal(today_count));
            }
            // 누적 조회수 - 당일 조회수가 50만 이상 100만 미만인 경우
            else if (accumulate_count - today_count >= 500000 && accumulate_count - today_count < 1000000) {
                // 당일 조회수를 포함한 조회수가 100만 미만인 경우
                if (accumulate_count < 1000000) {
                    daily_amount = under_1M.multiply(new BigDecimal(today_count));
                }
                // 당일 조회수를 포함한 조회수가 100만 이상인 경우
                else {
                    daily_amount = under_1M.multiply(new BigDecimal(1000000 - (accumulate_count - today_count) - 1))
                            .add(over_1M.multiply(new BigDecimal(today_count - (1000000 - (accumulate_count - today_count) - 1))));
                }
            }
            // 당일 제외 누적 조회수가 10만 이상 50만 미만인 경우
            else if (accumulate_count - today_count >= 100000 && accumulate_count - today_count < 500000) {
                // 당일 포함 조회수가 10만이상 50만인 경우
                if (accumulate_count >= 100000 && accumulate_count < 500000) {
                    daily_amount = under_500K.multiply(new BigDecimal(today_count));
                }
                // 당일 포함 조회수가 50만 이상 100만 미만인 경우
                else if (accumulate_count >= 500000 && accumulate_count < 1000000) {
                    daily_amount = under_500K.multiply(new BigDecimal(500000 - (accumulate_count - today_count) - 1))
                            .add(under_1M.multiply(new BigDecimal(today_count - (500000 - (accumulate_count - today_count) - 1))));
                }
                // 당일 포함 조회수가 100만 이상인 경우
                else {
                    daily_amount = under_500K.multiply(new BigDecimal(500000 - (accumulate_count - today_count) - 1))
                            .add(under_1M.multiply(new BigDecimal(500000)))
                            .add(over_1M.multiply(new BigDecimal(today_count - (1000000 - (accumulate_count - today_count) - 1))));
                }
            }
            // 당일 제외 조회수가 10만 미만인 경우
            else {
                // 당일 포함 조회수가 10만 미만인 경우
                if(accumulate_count < 100000) {
                    daily_amount = under_100K.multiply(new BigDecimal(today_count));
                }
                // 당일 포함 조회수가 10만 이상 50만 미만인 경우
                else if (accumulate_count >= 100000 && accumulate_count < 500000) {
                    daily_amount = under_100K.multiply(new BigDecimal(100000 - (accumulate_count - today_count) - 1))
                            .add(under_500K.multiply(new BigDecimal(today_count - (100000 - (accumulate_count - today_count) - 1))));
                }
                // 당일 포함 조회수가 50만 이상 100만 미만인 경우
                else if (accumulate_count >= 500000 && accumulate_count < 1000000) {
                    daily_amount = under_100K.multiply(new BigDecimal(100000 - (accumulate_count - today_count) - 1))
                            .add(under_500K.multiply(new BigDecimal(400000)))
                            .add(under_1M.multiply(new BigDecimal(today_count - (500000 - (accumulate_count - today_count) - 1))));
                }
                // 당일 포함 조회수가 100만 이상인 경우
                else {
                    daily_amount = under_100K.multiply(new BigDecimal(100000 - (accumulate_count - today_count) - 1))
                            .add(under_500K.multiply(new BigDecimal(400000)))
                            .add(under_1M.multiply(new BigDecimal(500000)))
                            .add(over_1M.multiply(new BigDecimal(today_count - (1000000 - (accumulate_count - today_count) - 1))));
                }
            }
        }
        return daily_amount.setScale(1, RoundingMode.HALF_UP);
    }
}
