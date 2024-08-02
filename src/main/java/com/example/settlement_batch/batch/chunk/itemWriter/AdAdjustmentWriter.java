package com.example.settlement_batch.batch.chunk.itemWriter;


import com.example.settlement_batch.advertisement.entity.AdAdjustment;
import com.example.settlement_batch.advertisement.repository.write.AdAdjustmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdAdjustmentWriter implements ItemWriter<AdAdjustment> {

    private final AdAdjustmentWriteRepository adAdjustmentWriteRepository;

    @Override
    public void write(Chunk<? extends AdAdjustment> chunk) throws Exception {
        adAdjustmentWriteRepository.saveAll(chunk.getItems());
    }
}
