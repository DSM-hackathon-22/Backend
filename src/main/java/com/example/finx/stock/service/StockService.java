package com.example.finx.stock.service;

import com.example.finx.Interests.entity.InterestedEntity;
import com.example.finx.Interests.repository.InterestedRepository;
import com.example.finx.stock.api.StockClient;
import com.example.finx.stock.api.StockData;
import com.example.finx.stock.entity.StockEntity;
import com.example.finx.stock.repository.StockRepository;
import com.example.finx.user.entity.UserEntity;
import com.example.finx.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockClient stockClient;
    private final InterestedRepository interestedRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    public StockData getStock() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findById(Long.valueOf(id)).get();

        StockEntity randomStockEntity = getRandomStockEntity(userEntity.getId());

        Object stock = stockClient.getStock("TIME_SERIES_DAILY", randomStockEntity.getTickerSymbol(), "EEDV8XBV9EAXM3NQ");
        return new StockData(stock, randomStockEntity);
    }

    private StockEntity getRandomStockEntity(Long userId) {
        InterestedEntity interestedEntity = interestedRepository.findByUserId(userId);
        List<StockEntity> stockEntities = stockRepository.findAllByInterestedEntity(interestedEntity);

        Random random = new Random();
        int randomStockSequence = random.nextInt(stockEntities.size()) + 1;
        return stockEntities.get(randomStockSequence);
    }
}
