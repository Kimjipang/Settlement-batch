package com.example.settlement_batch.batch.chunk.itemReader;

import com.example.settlement_batch.advertisement.entity.VideoAd;
import com.example.settlement_batch.advertisement.repository.read.VideoAdReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdItemReader implements ItemReader<VideoAd> {

    private final VideoAdReadRepository videoAdReadRepository;
    private Iterator<VideoAd> videoAdIterator;

    @Override
    public VideoAd read() throws Exception {
        if (videoAdIterator == null) {
            List<VideoAd> videoAdList = videoAdReadRepository.findAll();
            videoAdIterator = videoAdList.iterator();
        }

        if (videoAdIterator.hasNext()) {
            return videoAdIterator.next();
        } else {
            return null;
        }
    }

}
