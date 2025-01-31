package com.example.settlement_batch.batch.chunk.itemReader;

import com.example.settlement_batch.video.entity.Video;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoItemReader  {
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    @StepScope
    public JpaPagingItemReader<Video> jpaPagingItemReader() {

        JpaPagingItemReader<Video> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT v FROM Video v JOIN FETCH v.user ORDER BY v.id");
//        reader.setQueryString("SELECT v FROM Video v ORDER BY v.id");
        reader.setPageSize(10);
        reader.setSaveState(false);
        return reader;
    }

}
