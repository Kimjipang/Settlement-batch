package com.example.settlement_batch.batch.chunk.itemWriter;

import com.example.settlement_batch.video.entity.VideoStatistics;
import com.example.settlement_batch.video.repository.write.VideoStatisticsWriteRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoStatisticsWriter {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaItemWriter<VideoStatistics> videoStatisticsJpaItemWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<VideoStatistics> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
//public class VideoStatisticsWriter implements ItemWriter<VideoStatistics> {

//    private final VideoStatisticsWriteRepository videoStatisticsWriteRepository;
//
//    @Override
//    public void write(Chunk<? extends VideoStatistics> chunk) throws Exception {
//        videoStatisticsWriteRepository.saveAll(chunk.getItems());
//    }

}
