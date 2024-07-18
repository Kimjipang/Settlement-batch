package com.example.settlement_batch.batch.chunk;

import com.example.settlement_batch.video.entity.VideoAdjustment;
import com.example.settlement_batch.video.repository.write.VideoAdjustmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoAdjustmentWriter implements ItemWriter<VideoAdjustment> {

    private final VideoAdjustmentWriteRepository videoAdjustmentWriteRepository;

    @Override
    public void write(Chunk<? extends VideoAdjustment> chunk) throws Exception {
        videoAdjustmentWriteRepository.saveAll(chunk.getItems());
    }
}

