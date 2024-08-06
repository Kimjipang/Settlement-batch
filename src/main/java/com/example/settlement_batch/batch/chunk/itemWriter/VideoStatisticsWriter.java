package com.example.settlement_batch.batch.chunk.itemWriter;

import com.example.settlement_batch.video.entity.VideoStatistics;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoStatisticsWriter {

    @Bean
    public JpaItemWriter<VideoStatistics> videoStatisticsJpaItemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<VideoStatistics> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}
