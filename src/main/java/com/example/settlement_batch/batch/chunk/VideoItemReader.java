package com.example.settlement_batch.batch.chunk;


import com.example.settlement_batch.video.entity.Video;
import com.example.settlement_batch.video.repository.read.VideoReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoItemReader implements ItemReader<Video> {

    private final VideoReadRepository videoReadRepository;
    private Iterator<Video> videoIterator;

    @Override
    public Video read() throws Exception {
        if (videoIterator == null) {
            List<Video> videoList = videoReadRepository.findAll();
            videoIterator = videoList.iterator();
        }

        if (videoIterator.hasNext()) {
            return videoIterator.next();
        } else {
            return null;
        }
    }
}
