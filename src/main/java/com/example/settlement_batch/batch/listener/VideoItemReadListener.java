package com.example.settlement_batch.batch.listener;

import com.example.settlement_batch.video.entity.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class VideoItemReadListener implements ItemReadListener<Video> {

    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(Video video) {
        // 데이터 읽은 후에 호출됩니다.
        if (video.getId() < 1 && video.getId() > 4000) {
            log.info("Thread: {}, 동영상의 id : {}", Thread.currentThread().getName(), video.getId());
        }
    }

    @Override
    public void onReadError(Exception ex) {
        // 읽기 오류 발생 시 호출됩니다.
        log.error("Thread: {}, Error reading data", Thread.currentThread().getName(), ex);
    }
}
