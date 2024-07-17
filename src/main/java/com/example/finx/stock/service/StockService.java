package com.example.finx.stock.service;

import com.example.finx.Interests.entity.InterestedEntity;
import com.example.finx.Interests.repository.InterestedRepository;
import com.example.finx.config.FinxProperties;
import com.example.finx.stock.api.StockClient;
import com.example.finx.stock.api.StockData;
import com.example.finx.stock.api.StockData.StockElement;
import com.example.finx.stock.entity.StockEntity;
import com.example.finx.stock.repository.StockRepository;
import com.example.finx.user.entity.UserEntity;
import com.example.finx.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockClient stockClient;
    private final InterestedRepository interestedRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final FinxProperties finxProperties;

    public StockData getStock() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findById(Long.valueOf(id)).get();

        List<StockElement> stockElements = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            StockEntity randomStockEntity = getRandomStockEntity(userEntity.getId());
            Object stock = stockClient.getStock("TIME_SERIES_DAILY", randomStockEntity.getTickerSymbol(), finxProperties.getStockKey());

            stockElements.add(new StockElement(stock, randomStockEntity));
        }
        return new StockData(stockElements);
    }

    private StockEntity getRandomStockEntity(Long userId) {
        InterestedEntity interestedEntity = interestedRepository.findByUserId(userId);
        List<StockEntity> stockEntities = stockRepository.findAllByInterestedEntity(interestedEntity);

        Random random = new Random();
        int randomStockSequence = random.nextInt(stockEntities.size()) + 1;
        return stockEntities.get(randomStockSequence);
    }
}
