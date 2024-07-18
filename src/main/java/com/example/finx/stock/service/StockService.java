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
import java.util.Collections;
import java.util.List;

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

        InterestedEntity interestedEntity = interestedRepository.findByUserId(userEntity.getId());
        List<StockEntity> stockEntities = stockRepository.findAllByInterestedEntity(interestedEntity);
        Collections.shuffle(stockEntities);

        List<StockEntity> randomStockEntities = stockEntities.subList(0, Math.min(2, stockEntities.size()));
        List<StockElement> stockElements = new ArrayList<>();
        randomStockEntities.forEach(stock -> {
            Object result = stockClient.getStock("TIME_SERIES_DAILY", stock.getTickerSymbol(), finxProperties.getStockKey());
            stockElements.add(new StockElement(result, stock));
        });

        return new StockData(stockElements);
    }
}
