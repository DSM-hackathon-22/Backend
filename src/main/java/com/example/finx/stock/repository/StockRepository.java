package com.example.finx.stock.repository;

import com.example.finx.Interests.entity.InterestedEntity;
import com.example.finx.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    List<StockEntity> findAllByInterestedEntity(InterestedEntity interestedEntity);
}
