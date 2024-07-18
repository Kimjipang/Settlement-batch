package com.example.settlement_batch.advertisement.repository.write;

import com.example.settlement_batch.advertisement.entity.AdView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdViewWriteRepository extends JpaRepository<AdView, Integer> {
}
